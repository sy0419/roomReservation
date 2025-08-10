package com.roomreservation.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roomreservation.reservation.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
