package com.tadiwa.backend.features.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadiwa.backend.features.user.AddUserDto;
import com.tadiwa.backend.features.user.User;
import com.tadiwa.backend.features.user.UserService;
import com.tadiwa.backend.shared.exceptions.EmailTakenException;
import com.tadiwa.backend.shared.exceptions.InvalidJwtToken;
import com.tadiwa.backend.shared.exceptions.NotFound;
import com.tadiwa.backend.shared.utilities.JwtUtil;

import io.jsonwebtoken.Claims;

@Service
public class AuthService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwt;

    public User getTokenUser(String token) throws InvalidJwtToken {
        Claims claims = jwt.verifyToken(token);

        String email = claims.getSubject();
        Optional<User> userOptional = userService.getUser(email);

        if (userOptional.isEmpty()) {
            throw new InvalidJwtToken();
        }

        return userOptional.get();
    }

    public AuthenticateResult authenticate(String email, String password) throws NotFound {
        Optional<User> userOptional = userService.getUser(email, password);

        if (userOptional.isEmpty()) {
            throw new NotFound();
        }

        User user = userOptional.get();
        String token = jwt.issueToken(user);

        AuthenticateResult result = new AuthenticateResult(token, "Authenticated Successfully");

        return result;
    }

    public AuthenticateResult register(AddUserDto addUserDto) throws EmailTakenException {
        User user = userService.createUser(addUserDto);

        String token = jwt.issueToken(user);

        return new AuthenticateResult(token, "User resgistered successfully");
    }

}
