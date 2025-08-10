package com.roomreservation.reservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Room {
    // 고유 사용자 ID (자동 생성됨) Unique ID for each user (auto-generated)
    @Id
    @GeneratedValue
    private Long id;

    // 방 이름 Room's name
    private String name;
    
    // 방의 대한 설명 Room's description
    private String description;

    // 방 가격 Room's price
    private int price;

    // 방 최대 수용 인원 Room's max people number
    private int maxPeople;

    // 기본 생성자 (Spring JPA가 내부적으로 사용함) Default constructor (used internally by Spring JPA)
    public Room() {}

    // --- Getter & Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }
}