package com.roomreservation.reservation.service;

import java.util.List;

import com.roomreservation.reservation.domain.Room;

public interface RoomService {

    /**
     * 방을 새로 등록한다.
     * Register a new room.
     * 
     * @param room 등록할 방 객체 / Room to be registered
     * @return 저장된 방 객체 / Saved Room object
     */
    Room addRoom(Room room);

    /**
     * 모든 방 정보를 조회한다.
     * Retrieve a list of all rooms.
     * 
     * @return 방 리스트 / List of rooms
     */
    List<Room> getAllRooms();

    /**
     * ID에 해당하는 방 정보를 조회한다.
     * Retrieve room information by ID.
     * 
     * @param id 조회할 방 ID / Room ID to retrieve
     * @return 조회된 방 객체 / Retrieved Room object
     */
    Room getRoomById(Long id);

    /**
     * 기존 방 정보를 수정한다.
     * Update existing room information.
     * 
     * @param id 수정할 방 ID / Room ID to update
     * @param updatedRoom 수정할 방 정보 / Updated Room data
     * @return 수정된 방 객체 / Updated Room object
     */
    Room updateRoom(Long id, Room updatedRoom);

    /**
     * ID에 해당하는 방 정보를 삭제한다.
     * Delete room information by ID.
     * 
     * @param id 삭제할 방 ID / Room ID to delete
     */
    void deleteRoom(Long id);
}