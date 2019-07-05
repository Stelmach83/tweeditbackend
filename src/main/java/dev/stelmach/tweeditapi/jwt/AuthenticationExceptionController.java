package dev.stelmach.tweeditapi.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionController {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleForbiddenException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO(403, e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseDTO> handleExpiredException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseDTO> handleSignatureException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ResponseDTO> handleMalformedJsonException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDTO> handleAuthException(Exception e) {
        return new ResponseEntity<>(new ResponseDTO(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
