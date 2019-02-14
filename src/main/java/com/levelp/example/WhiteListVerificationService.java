package com.levelp.example;

import org.springframework.stereotype.Service;

@Service
public class WhiteListVerificationService implements PassportVerificationServise {
    @Override
    public boolean isValid(String name, String passport) {

        if (name.equals("Rasputin")) return true;

        return false;
    }
}
