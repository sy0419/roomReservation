package com.roomreservation.reservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.roomreservation.reservation.domain.User;
import com.roomreservation.reservation.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        // Mockito 초기화 / Initialize Mockito mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegiserUser_Success() {
        // 회원가입 성공 테스트 / Test for successful user registration
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.registerUser(user);

        assertNotNull(result); // 저장된 유저가 null이 아님 / User is not null
        assertEquals("test@example.com", result.getEmail());

        verify(userRepository).findByEmail("test@example.com");
        verify(userRepository).save(user);
    }

    @Test
    void testLoginUser_Success() {
        // 로그인 성공 테스트 / Test for successful login
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User login = userService.login("test@example.com", "1234");

        assertNotNull(login);
        assertEquals("test@example.com", login.getEmail());
        assertEquals("1234", login.getPassword());

        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testLoginUserNotFind_failure() {
        // 로그인 실패 - 이메일 없음 / Login failure - email not found
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.login("test@example.com", "1234");
        });

        assertEquals("Email is not exist. Please, Create an account.", exception.getMessage());

        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testLoginUserUncorrectPassword_failure() {
        // 로그인 실패 - 비밀번호 틀림 / Login failure - incorrect password
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.login("test@example.com", "12345"); // 잘못된 비밀번호 / Wrong password
        });

        assertEquals("Password is not correct.", exception.getMessage());

        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testGetAllUsers() {
        // 전체 사용자 조회 테스트 / Test for retrieving all users
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("test@example.com");
        user1.setPassword("1234");
        userList.add(user1);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> results = userService.getAllUsers();
        User firstUser = results.get(0);

        assertEquals("test@example.com", firstUser.getEmail());
        assertEquals("1234", firstUser.getPassword());

        verify(userRepository).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser_WhenIdExists() {
        // ID로 유저 조회 성공 테스트 / Test for retrieving user by ID
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("1234");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User results = userService.getUserById(1L);

        assertEquals("test@example.com", results.getEmail());
        assertEquals("1234", results.getPassword());

        verify(userRepository).findById(1L);
    }
}