package com.project.gestionpersonnelback.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public String getUserDetails(@AuthenticationPrincipal OidcUser user) {
        // Access user details from the OidcUser object
        String username = user.getPreferredUsername();
        String email = user.getEmail();
        String roles = user.getAuthorities().toString();

        return String.format("Username: %s, Email: %s, Roles: %s", username, email, roles);
    }
}