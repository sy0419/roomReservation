package com.roomreservation.reservation.service;

import java.util.List;

import com.roomreservation.reservation.domain.Reservation;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    Reservation cancelReservation(Long id);
}
