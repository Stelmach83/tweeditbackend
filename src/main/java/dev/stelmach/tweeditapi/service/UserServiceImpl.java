package dev.stelmach.tweeditapi.service;

import dev.stelmach.tweeditapi.database.UserRepository;
import dev.stelmach.tweeditapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersOtherThanLoggedIn(User user) {
        return getAllUsers().stream()
                .filter(u -> !u.equals(user))
                .collect(Collectors.toList());
//        return result;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getTop10Users() {
        return userRepository.getAllByOrderByPointsDesc();
    }

    @Override
    public List<User> getTop10UserByFollowers() {
        return userRepository.getAllByOrderByFollowersDesc();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Long isUserUnique(String username) {
        return userRepository.countUsersByUsername(username);
    }

    @Override
    public Long isEmailUnique(String email) {
        return userRepository.countUsersByEmail(email);
    }
}
