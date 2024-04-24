package com.minispotify.gateway.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private enum Role {
        REGULAR,
        ADMIN
    }
    private String email;
    private String username;
    private String password;
    private Role role;
}
