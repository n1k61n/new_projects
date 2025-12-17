package com.example.fruitables.services;

import java.util.StringTokenizer;

public interface EmailService {
    void sendEmail(String to, String token);
}
