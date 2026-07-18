package org.example.springsecurityproject.controller;

import org.example.springsecurityproject.dto.LoginRequest;
import org.example.springsecurityproject.dto.LoginResponse;
import org.example.springsecurityproject.dto.RegisterRequest;
import org.example.springsecurityproject.entity.AppUser;
import org.example.springsecurityproject.repository.AppUserRepository;
import org.example.springsecurityproject.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
private final AppUserRepository appUserRepository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;

public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
    this.appUserRepository=appUserRepository;
    this.passwordEncoder=passwordEncoder;
    this.jwtService = jwtService;
}

@PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
    if(appUserRepository.findByUsername(request.getUsername()).isPresent()){
        return "username already exists";
    }

    AppUser user= new AppUser();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole("USER");

    appUserRepository.save(user);
    return "user registered successfully";
}

@GetMapping("/login-success")
    public String loginSuccess(Authentication authentication){
    return "login succesfull for"+ authentication.getName();
}

@GetMapping("/login-failed")
    public String loginFailed(){
    return "invalid username or password";
}


@PostMapping("/api/login")
    public ResponseEntity<?> apiLogin(@RequestBody LoginRequest loginRequest){
AppUser user= appUserRepository.findByUsername(loginRequest.getUsername())
        .orElse(null);
if (user==null){
    return ResponseEntity.status(401).body("invalid username or password");
}
if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
    return ResponseEntity.status(401).body("invalid password");
    }
String token = jwtService.generateToken(user.getUsername(),user.getRole());
    LoginResponse response= new LoginResponse(token,"Bearer",user.getUsername(),user.getRole());
return ResponseEntity.ok(response);
}
}
