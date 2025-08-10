package com.roomreservation.reservation.service;

import java.util.List;

import com.roomreservation.reservation.domain.User;

public interface UserService {

    /**
     * 회원가입을 진행한다. 일반 사용자만 가능하다.
     * Register a new user account. Only regular users can register.
     * 
     * @param user 회원 정보 / User information
     * @return 등록된 사용자 객체 / Registered User object
     */
    User registerUser(User user);

    /**
     * 이메일과 비밀번호로 로그인한다. 일반 사용자와 관리자 모두 가능하다.
     * Authenticate user by email and password. Both User and Admin can log in.
     * 
     * @param email 로그인 이메일 / Email for login
     * @param password 로그인 비밀번호 / Password for login
     * @return 로그인된 사용자 객체 / Logged-in User object
     */
    User login(String email, String password);

    /**
     * 모든 사용자를 조회한다. 관리자만 접근 가능하다.
     * Retrieve all users. Only accessible by Admin.
     * 
     * @return 사용자 리스트 / List of users
     */
    List<User> getAllUsers();

    /**
     * 특정 사용자를 ID로 조회한다. 관리자만 접근 가능하다.
     * Retrieve a user by ID. Only accessible by Admin.
     * 
     * @param id 조회할 사용자 ID / User ID to retrieve
     * @return 조회된 사용자 객체 / Retrieved User object
     */
    User getUserById(Long id);
}