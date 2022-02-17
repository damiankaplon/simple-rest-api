package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.config.security.LoginCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @PostMapping("/login")
    public void loginAttempt(@RequestBody LoginCredentials loginCredentials) {

    }
}
