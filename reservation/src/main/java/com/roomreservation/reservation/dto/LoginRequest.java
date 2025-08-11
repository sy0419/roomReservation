package com.roomreservation.reservation.dto;

/**
 * 로그인 요청 데이터를 담는 DTO 클래스
 * Data Transfer Object for login request data
 */
public class LoginRequest {
    // 사용자 이메일 User's email
    private String email;
    
    // 사용자 비밀번호 User's password
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}