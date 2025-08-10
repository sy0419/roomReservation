package com.roomreservation.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    // 유저 고유 ID (자동 생성됨) Unique ID for each user (auto-generated)
    @Id
    @GeneratedValue
    private Long id;

    // 유저 이름 User name
    private String name;
    
    // 유저 이메일 User email
    private String email;

    // 유저 패스워드 User password
    private String password;

    // 유저 전화번호 User phone number
    private String phoneNumber;

    // 기본 생성자 (Spring JPA가 내부적으로 사용함) Default constructor (used internally by Spring JPA)
    public User() {}

    // --- Getter & Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
