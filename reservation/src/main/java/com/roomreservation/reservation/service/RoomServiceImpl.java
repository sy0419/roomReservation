package com.roomreservation.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.roomreservation.reservation.domain.Room;
import com.roomreservation.reservation.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * 방 정보를 새로 등록한다.
     * Register a new room.
     * 
     * @param room 등록할 방 객체 / Room to be registered
     * @return 저장된 방 객체 / Saved Room object
     * @throws IllegalArgumentException 가격이 음수이거나 최대 인원이 1 미만일 경우 발생
     *                                  Throws when price is negative or maxPeople is less than 1
     */
    @Override
    public Room addRoom(Room room) {
        if (room.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        if (room.getMaxPeople() < 1) {
            throw new IllegalArgumentException("MaxPeople must be non-negative.");
        }
        return roomRepository.save(room);
    }

    /**
     * 모든 방 정보를 조회한다.
     * Retrieve all room information.
     * 
     * @return 방 리스트 / List of rooms
     */
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * ID에 해당하는 방 정보를 조회한다.
     * Retrieve room information by ID.
     * 
     * @param id 조회할 방 ID / Room ID to retrieve
     * @return 조회된 방 객체 / Retrieved Room object
     * @throws IllegalArgumentException 해당 ID의 방이 없으면 발생
     *                                  Throws when room with given ID does not exist
     */
    @Override
    public Room getRoomById(Long id) {
        Optional<Room> registeredId = roomRepository.findById(id);
        if (registeredId.isPresent()) {
            return registeredId.get();
        } else {
            throw new IllegalArgumentException("The id is not existed.");
        }
    }

    /**
     * 기존 방 정보를 수정한다.
     * Update existing room information.
     * 
     * @param id          수정할 방 ID / Room ID to update
     * @param updatedRoom 수정할 방 정보 / Updated Room data
     * @return 수정된 방 객체 / Updated Room object
     * @throws IllegalArgumentException 해당 ID의 방이 없으면 발생
     *                                  Throws when room with given ID does not exist
     */
    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        Optional<Room> checkId = roomRepository.findById(id);
        if (checkId.isPresent()) {
            Room existingRoom = checkId.get();
            existingRoom.setName(updatedRoom.getName());
            existingRoom.setPrice(updatedRoom.getPrice());
            existingRoom.setDescription(updatedRoom.getDescription());
            existingRoom.setMaxPeople(updatedRoom.getMaxPeople());
            return roomRepository.save(existingRoom);
        } else {
            throw new IllegalArgumentException("Room not found.");
        }
    }

    /**
     * ID에 해당하는 방 정보를 삭제한다.
     * Delete room information by ID.
     * 
     * @param id 삭제할 방 ID / Room ID to delete
     * @throws IllegalArgumentException 해당 ID의 방이 없으면 발생
     *                                  Throws when room with given ID does not exist
     */
    @Override
    public void deleteRoom(Long id) {
        Optional<Room> existingId = roomRepository.findById(id);
        if (existingId.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Room not found.");
        }
    }
}