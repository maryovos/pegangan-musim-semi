package com.indocyber.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data object yang digunakan untuk me-request JWT.")
public class RequestTokenDto {

    private String username;

    private String password;

    private String subject;

    private String secretKey;

    private String audience;

    public RequestTokenDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }


}
