package com.roomreservation.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.roomreservation.reservation.domain.User;
import com.roomreservation.reservation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 생성자 주입 Constructor Injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     /**
     * 회원가입 메서드
     * Register a new user if email does not already exist.
     * @param user 회원 정보 / User info
     * @return 저장된 User 객체 / Saved User object
     * @throws IllegalArgumentException 중복 이메일인 경우 / If email already exists
     */
    @Override
    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    /**
     * 로그인 메서드
     * Authenticate user by email and password.
     * @param email 사용자 이메일 / User email
     * @param password 사용자 비밀번호 / User password
     * @return 로그인 성공 시 User 객체 반환 / Returns User object if login successful
     * @throws IllegalArgumentException 이메일이 존재하지 않거나 비밀번호가 틀린 경우 / If email not found or password incorrect
     */
    @Override
    public User login(String email, String password) {
        Optional<User> registeredUser = userRepository.findByEmail(email);
        if (registeredUser.isPresent()) {
            User user = registeredUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new IllegalArgumentException("Password is not correct.");
            }
        } else {
            throw new IllegalArgumentException("Email is not exist. Please, Create an account.");
        }
    }

    /**
     * 모든 사용자 조회 메서드 (Admin 용)
     * Retrieve all users.
     * @return 모든 사용자 리스트 / List of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 특정 사용자 조회 메서드 (Admin 용)
     * Retrieve user by ID.
     * @param id 조회할 사용자 ID / User ID to find
     * @return 조회된 User 객체 / Found User object
     * @throws IllegalArgumentException 존재하지 않는 ID일 경우 / If user ID does not exist
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> registeredId = userRepository.findById(id);
        if (registeredId.isPresent()) {
            return registeredId.get();
        } else {
            throw new IllegalArgumentException("The id is not existed.");
        }
    }
}