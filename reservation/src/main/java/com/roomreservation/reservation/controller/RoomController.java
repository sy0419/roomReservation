package com.roomreservation.reservation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roomreservation.reservation.domain.Room;
import com.roomreservation.reservation.service.RoomService;

@RestController
@RequestMapping("/api/rooms") // 모든 요청의 기본 경로를 /api/rooms로 설정
public class RoomController {
    private final RoomService roomService;

    // 생성자 주입(Constructor Injection)으로 RoomService를 주입받음
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 새로운 방 정보를 등록한다.
     * Register a new room.
     * @param room 요청 본문으로 전달되는 방 정보 (JSON 등)
     * @return 저장된 방 객체
     */
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    /**
     * 모든 방 목록을 조회한다.
     * Retrieve all rooms.
     * @return 방 목록 리스트
     */
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * 특정 ID에 해당하는 방 정보를 조회한다.
     * Retrieve room by ID.
     * @param id URL 경로 변수로 전달된 방 ID
     * @return 조회된 방 객체
     */
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    /**
     * 특정 ID에 해당하는 방 정보를 수정한다.
     * Update room by ID.
     * @param id URL 경로 변수로 전달된 방 ID
     * @param room 요청 본문으로 전달된 수정할 방 정보
     * @return 수정된 방 객체
     */
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    /**
     * 특정 ID에 해당하는 방 정보를 삭제한다.
     * Delete room by ID.
     * @param id URL 경로 변수로 전달된 방 ID
     */
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}