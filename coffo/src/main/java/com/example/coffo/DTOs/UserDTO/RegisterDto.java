package com.example.coffo.DTOs.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotEmpty(message = "Ad sahesi bos ola bilmez")
    private String  firstName;
    @NotEmpty(message = "Soyad sahesi bos ola bilmez")
    private String lastName;
    @NotEmpty(message = "Email sahesi bos ola bilmez")
    @Email(message = "Duzgun email formati daxil edin")
    private String email;
    @NotEmpty(message = "Sifre sahesi bos ola bilmez")
    @Size(min=6, message = "Sifre en az 6 simvol olmalidir")
    private String password;
    @NotEmpty(message = "Sifre sahesi bos ola bilmez")
    private String configPassword;
    @NotEmpty(message = "Nomre sahesi bos ola bilmez")
    private String phone;
}
