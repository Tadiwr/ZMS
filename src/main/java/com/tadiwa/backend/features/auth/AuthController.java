package com.tadiwa.backend.features.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadiwa.backend.features.user.AddUserDto;
import com.tadiwa.backend.shared.exceptions.EmailTakenException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticateResult> registerUser(@RequestBody AddUserDto addUserDto) {
        try {
            AuthenticateResult res = authService.register(addUserDto);

            return ResponseEntity.ok(res);
        } catch(EmailTakenException err) {
            AuthenticateResult errResult = new AuthenticateResult(null, "Email Already Taken");
            return ResponseEntity.badRequest().body(errResult);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateResult> login(@RequestBody LoginDto loginDto) {
        try {

            AuthenticateResult res = authService.authenticate(
                loginDto.email(),
                loginDto.password()
            );

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public Object getAuthUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    
    

}
