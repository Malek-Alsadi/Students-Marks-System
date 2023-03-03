package com.example.Authentication;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements iValidation {
    static final String[] users = {"admin", "malek", "mustafa"};
    static final String[] passwords = {"admin@123", "malek@123", "mustafa@123"};

    protected AuthenticationController() {
    }

    @Override
    public boolean isValid(String username,String password) {
        for (int i = 0; i < 3; i++) {
            if (username.equals(users[i]) && password.equals(passwords[i]))
                return true;
        }
        return false;
    }
}
