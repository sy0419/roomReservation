package com.roomreservation.reservation.service;

import java.util.List;

import com.roomreservation.reservation.domain.Reservation;

public interface ReservationService {

    /**
     * 새로운 예약을 추가합니다.
     * Adds a new reservation.
     *
     * @param reservation 예약 정보 / reservation information
     * @return 저장된 예약 객체 / the saved reservation object
     * @throws IllegalArgumentException 예약이 유효하지 않은 경우 발생 / thrown when reservation is invalid
     */
    Reservation addReservation(Reservation reservation);

    /**
     * 모든 예약 목록을 조회합니다.
     * Retrieves the list of all reservations.
     *
     * @return 예약 리스트 / list of reservations
     */
    List<Reservation> getAllReservations();

    /**
     * 예약 ID로 예약을 조회합니다.
     * Retrieves a reservation by its ID.
     *
     * @param id 예약 ID / reservation ID
     * @return 해당 예약 객체 / the reservation object
     * @throws IllegalArgumentException 해당 예약이 없을 경우 발생 / thrown when reservation not found
     */
    Reservation getReservationById(Long id);

    /**
     * 예약 정보를 수정합니다.
     * Updates an existing reservation.
     *
     * @param id 수정할 예약 ID / ID of the reservation to update
     * @param reservation 수정할 예약 정보 / updated reservation information
     * @return 수정된 예약 객체 / updated reservation object
     * @throws IllegalArgumentException 예약이 없거나 유효하지 않은 경우 발생 / thrown when reservation not found or invalid
     */
    Reservation updateReservation(Long id, Reservation reservation);

    /**
     * 예약을 취소합니다.
     * Cancels a reservation.
     *
     * @param id 취소할 예약 ID / ID of the reservation to cancel
     * @return 취소된 예약 객체 / cancelled reservation object
     * @throws IllegalArgumentException 예약이 없을 경우 발생 / thrown when reservation not found
     */
    Reservation cancelReservation(Long id);
}