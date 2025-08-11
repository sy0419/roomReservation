package com.roomreservation.reservation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roomreservation.reservation.domain.User;
import com.roomreservation.reservation.dto.LoginRequest;
import com.roomreservation.reservation.service.UserService;

/**
 * User 관련 API 요청을 처리하는 컨트롤러 클래스
 * Handles API requests related to User operations such as registration, login, and fetching user info.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입 요청 처리
     * Handles user registration request.
     * @param user 회원 가입 정보 / User registration info
     * @return 등록된 User 객체 / Registered User object
     */
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    /**
     * 로그인 요청 처리
     * Handles user login request.
     * @param loginRequest 로그인 요청 DTO (이메일, 비밀번호) / Login request DTO (email, password)
     * @return 로그인 성공 시 User 객체 반환 / Returns User object if login successful
     */
    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    /**
     * 모든 사용자 조회 (관리자용)
     * Retrieves all users (Admin only).
     * @return 사용자 리스트 / List of all users
     */
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    /**
     * 특정 사용자 조회 (관리자용)
     * Retrieves a user by ID (Admin only).
     * @param id 조회할 사용자 ID / User ID to retrieve
     * @return 조회된 User 객체 / Retrieved User object
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}