package com.levelp.example;

import org.springframework.stereotype.Service;

@Service
public interface PassportVerificationServise {

    boolean isValid(String name, String passport);

}
