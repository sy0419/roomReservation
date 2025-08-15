package com.roomreservation.reservation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roomreservation.reservation.domain.Reservation;
import com.roomreservation.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    // 생성자 주입 - ReservationService를 의존성 주입받음
    // Constructor-based dependency injection
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * 예약 추가
     * Adds a new reservation
     *
     * @param reservation 예약 정보 / reservation info
     * @return 저장된 예약 객체 / saved reservation
     */
    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    /**
     * 모든 예약 조회
     * Retrieves all reservations
     *
     * @return 예약 리스트 / list of reservations
     */
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * 특정 예약 조회 (ID 기준)
     * Retrieves a reservation by its ID
     *
     * @param id 예약 ID / reservation ID
     * @return 해당 예약 객체 / reservation object
     */
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    /**
     * 예약 정보 수정
     * Updates reservation details
     *
     * @param id 수정할 예약 ID / ID of the reservation to update
     * @param reservation 수정할 정보 / updated data
     * @return 수정된 예약 객체 / updated reservation
     */
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    /**
     * 예약 취소
     * Cancels a reservation by ID
     *
     * @param id 예약 ID / reservation ID to cancel
     * @return 취소된 예약 객체 / cancelled reservation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id); 
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }
}