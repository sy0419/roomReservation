package com.roomreservation.reservation.service;

import java.util.List;

import com.roomreservation.reservation.domain.User;

public interface UserService {
    // 회원가입 (User만 가능) Create account (Only User)
    User registerUser(User user);

    // 로그인 (User, Admin 모두 가능) Sign in (User and Admin both)
    User login(String email, String password);

    // 모든 사용자 조회 (Admin만 가능) All users inquiry (Only Admin)
    List<User> getAllUsers();

    // 단건 사용자 조회 (Admin만 가능) A users inquiry (Only Admin)
    User getUserById(Long id);
}