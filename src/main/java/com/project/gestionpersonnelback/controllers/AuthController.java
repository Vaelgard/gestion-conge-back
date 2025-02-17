package com.project.gestionpersonnelback.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/auth")
    public RedirectView loginPage() {
        System.out.println("Redirecting to Keycloak login page...");
        return new RedirectView("/oauth2/authorization/keycloak");
    }
}