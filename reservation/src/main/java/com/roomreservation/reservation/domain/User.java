package com.roomreservation.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    // 고유 사용자 ID (자동 생성됨) Unique ID for each user (auto-generated)
    @Id
    @GeneratedValue
    private Long id;
    
    // User role (e.g., USER or ADMIN)
    // 사용자의 역할 (예: 일반 사용자 USER, 관리자 ADMIN)
    @Enumerated(EnumType.STRING)
    private Role role;

    // 사용자의 이름 User's name
    private String name;
    
    // 사용자의 이메일 User's email
    private String email;

    // 사용자의 패스워드 User's password
    private String password;

    // 사용자의 전화번호 User's phone number
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}