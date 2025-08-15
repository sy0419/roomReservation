package com.roomreservation.reservation.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.roomreservation.reservation.domain.Reservation;
import com.roomreservation.reservation.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    // 생성자 주입(Constructor injection)
    // Constructor injection of ReservationRepository
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 새로운 예약을 추가합니다.
     * Adds a new reservation.
     */
    @Override
    public Reservation addReservation(Reservation reservation) {
        if (reservation == null
            || reservation.getCheckInDate() == null
            || reservation.getCheckOutDate() == null
            || reservation.getCheckInDate().isBefore(LocalDate.now())
            || reservation.getCheckOutDate().isBefore(reservation.getCheckInDate())
            || reservation.getNumberOfPeople() < 1) {
            throw new IllegalArgumentException("Check your reservation.");
        }
        return reservationRepository.save(reservation);
    }

    /**
     * 저장된 모든 예약 목록을 조회합니다.
     * Retrieves all saved reservations.
     */
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll(); 
    }

    /**
     * ID로 예약을 조회합니다.
     * Retrieves a reservation by ID.
     */
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("The reservation does not exist."));
    }

    /**
     * 특정 ID 예약 정보를 수정합니다.
     * Updates reservation information by ID.
     */
    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("The reservation does not exist."));
        if (reservation == null
            || reservation.getCheckInDate() == null
            || reservation.getCheckOutDate() == null
            || reservation.getCheckInDate().isBefore(LocalDate.now())
            || reservation.getCheckOutDate().isBefore(reservation.getCheckInDate())
            || reservation.getNumberOfPeople() < 1) {
            throw new IllegalArgumentException("The reservation does not exist.");
        }
        existingReservation.setCheckInDate(reservation.getCheckInDate());
        existingReservation.setCheckOutDate(reservation.getCheckOutDate());
        existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());
        existingReservation.setIsApproved(reservation.getIsApproved());
        return reservationRepository.save(existingReservation);
    }

    /**
     * 예약을 취소합니다.
     * Cancels a reservation.
     *
     * @param id 취소할 예약 ID / ID of the reservation to cancel
     * @return 취소된 예약 객체 / the cancelled reservation object
     * @throws IllegalArgumentException 예약이 없을 경우 발생 / thrown when reservation not found
     */
    @Override
    public Reservation cancelReservation(Long id) {
        // 예약을 ID로 조회, 없으면 예외 발생
        // Find reservation by ID, throw exception if not found
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("The reservation does not exist."));
        
        // 예약 삭제 (취소 처리)
        // Delete the reservation (cancel it)
        reservationRepository.delete(reservation);
        
        // 삭제한 예약 객체 반환
        // Return the deleted reservation object
        return reservation;
    }
}