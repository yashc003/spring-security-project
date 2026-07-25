package org.example.springsecurityproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class LoginResponse {
    private String token;
    private String tokenType;
    private String username;
    private String role;

}

