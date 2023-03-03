package com.example.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface iValidation{
    @PostMapping("/auth")
    public boolean isValid(@RequestParam("UserName") String username,@RequestParam("Password") String password);
}
