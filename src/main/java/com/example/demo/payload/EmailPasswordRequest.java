package com.example.demo.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailPasswordRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public EmailPasswordRequest(String emailParam, String passworParam){
        this.email = emailParam;
        this.password = passworParam;
    }
}
