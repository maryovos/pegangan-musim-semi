package com.indocyber.dto;

public class RegisterDto {

    private String username;

    private String password;

    private String passwordConfirmation;

    private String role;

    public RegisterDto() {}

//    public RegisterDto(
//            String username,
//            String password,
//            String passwordConfirmation,
//            String role) {
//        this.username = username;
//        this.password = password;
//        this.passwordConfirmation = passwordConfirmation;
//        this.role = role;
//    }

    public RegisterDto(
            String username,
            String password,
            String passwordConfirmation,
            String role) {
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.role = role;
    }

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



}
