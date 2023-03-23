package com.university.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class SecurityController {

    @GetMapping
    public String index(Principal auth) {
        return String.format("Hello, %s!", auth.getName());
    }
}
