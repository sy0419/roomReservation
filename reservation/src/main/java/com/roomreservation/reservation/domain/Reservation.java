package com.roomreservation.reservation.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    // 고유 에약 ID (자동 생성됨) Unique ID for each user (auto-generated)
    @Id
    @GeneratedValue
    private Long id;

    // 예약한 사용자 (외래키 관계) reservated user (Foreign key)
    @ManyToOne
    private User user;

    // 예약한 방 (외래키 관계) reservated room (Foreign key)
    @ManyToOne
    private Room room;

    // 체크인 날짜 Check in date
    private LocalDate checkInDate;

    // 체크아웃 날짜 Check out date
    private LocalDate checkOutDate;

    // 예약 인원 수 number of people
    private int numberOfPeople;

    // 승인 여부 check to accept or not
    private boolean isApproved;

    // 기본 생성자 (Spring JPA가 내부적으로 사용함) Default constructor (used internally by Spring JPA)
    public Reservation() {}

    // --- Getter & Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}