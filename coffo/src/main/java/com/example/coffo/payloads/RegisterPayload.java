package com.example.coffo.payloads;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPayload {
    private String token;
    private String email;
    private int status;
    private String message;
}
