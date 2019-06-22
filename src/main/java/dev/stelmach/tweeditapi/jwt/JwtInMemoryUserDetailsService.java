package dev.stelmach.tweeditapi.jwt;

import dev.stelmach.tweeditapi.entity.User;
import dev.stelmach.tweeditapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    private Optional<User> getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    private Optional<User> getUser(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userFromEmail = getUserByEmail(username);
        Optional<User> userFromUsername = getUser(username);
        User user;
        JwtUserDetails jwtUser;
        if (userFromEmail.isPresent()) {
            user = userFromEmail.get();
            jwtUser = new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword());
        } else if (userFromUsername.isPresent()) {
            user = userFromUsername.get();
            jwtUser = new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword());
        } else {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        Collection<? extends GrantedAuthority> userRoles = user.getRoles();
        jwtUser.setAuthorities(userRoles);
        return jwtUser;
    }

}
