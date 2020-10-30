package com.example.demo3.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Peko.Lai
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
