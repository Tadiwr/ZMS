package com.tadiwa.backend.features.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.shared.exceptions.EmailTakenException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(AddUserDto userDto) throws EmailTakenException {

        if (isEmailTaken(userDto.email())) {
            throw new EmailTakenException();
        }

        User user = new User();

        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password()));

        return userRepo.save(user);
    }

    private boolean isEmailTaken(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        return userOptional.isPresent();
    }

    public Optional<User> getUser(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        
        return Optional.empty();

    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll()
            .stream()
            .toList();
    }
    
}