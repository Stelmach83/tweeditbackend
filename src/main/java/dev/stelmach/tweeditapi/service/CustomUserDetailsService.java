package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.database.UserRepository;
import dev.stelmach.tweeditapi.entity.CustomUserDetails;
import dev.stelmach.tweeditapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
