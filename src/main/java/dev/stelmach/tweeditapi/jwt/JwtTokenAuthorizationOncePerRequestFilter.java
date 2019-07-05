package dev.stelmach.tweeditapi.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserDetailsService jwtInMemoryUserDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationExceptionController authenticationExceptionController;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    public JwtTokenAuthorizationOncePerRequestFilter(UserDetailsService jwtInMemoryUserDetailsService, JwtTokenUtil jwtTokenUtil, AuthenticationExceptionController authenticationExceptionController) {
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationExceptionController = authenticationExceptionController;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.debug("Authentication Request For '{}'", request.getRequestURL());
        final String requestTokenHeader = request.getHeader(this.tokenHeader);
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
            } catch (ExpiredJwtException e) {
                log.warn("JWT_TOKEN_EXPIRED");
                ResponseDTO responseDTO = authenticationExceptionController.handleExpiredException(e).getBody();
                prepareResponse(response, responseDTO);
                return;
            } catch (SignatureException e) {
                log.warn("JWT_TOKEN_SIGNATURE_ERROR");
                ResponseDTO responseDTO = authenticationExceptionController.handleSignatureException(e).getBody();
                prepareResponse(response, responseDTO);
                return;
            } catch (MalformedJwtException e) {
                log.warn("MALFORMED_JWT_TOKEN");
                ResponseDTO responseDTO = authenticationExceptionController.handleMalformedJsonException(e).getBody();
                prepareResponse(response, responseDTO);
                return;
            }
        } else {
            log.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
        }
        log.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        chain.doFilter(request, response);
    }

    private void prepareResponse(HttpServletResponse response, ResponseDTO responseDTO) throws IOException {
        response.setStatus(responseDTO != null ? responseDTO.getCode() : 500);
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(responseDTO));
        out.flush();
    }
}
