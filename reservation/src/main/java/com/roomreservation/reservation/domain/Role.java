package com.roomreservation.reservation.domain;

/**
 * Enum defining roles for users in the reservation system.
 * - USER: Regular user who can browse and make reservations.
 * - ADMIN: Administrator who can manage rooms and view all reservations.
 * 
 * 예약 시스템에서 사용자의 역할을 정의하는 열거형입니다.
 * - USER: 방 조회 및 예약을 할 수 있는 일반 사용자입니다.
 * - ADMIN: 방을 관리하고 모든 예약을 볼 수 있는 관리자입니다.
 */
public enum Role {
    USER,   // Regular user / 일반 사용자
    ADMIN   // Administrator with management privileges / 관리자
}
