package com.roomreservation.reservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.roomreservation.reservation.domain.Room;
import com.roomreservation.reservation.repository.RoomRepository;

public class RoomServiceTest {
    private RoomServiceImpl roomService;
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        // Mockito의 @Mock 필드를 초기화합니다.
        // Initialize @Mock fields of Mockito
        MockitoAnnotations.openMocks(this);

        // RoomRepository를 Mock 객체로 생성합니다.
        // Create a mock object for RoomRepository
        roomRepository = mock(RoomRepository.class);

        // RoomServiceImpl에 Mock된 RoomRepository를 주입합니다.
        // Inject the mocked RoomRepository into RoomServiceImpl
        roomService = new RoomServiceImpl(roomRepository);
    }

    @Test
    void addRoom_ShouldSaveRoom() {
        // 테스트용 Room 객체 생성
        // Create a test Room object
        Room room = new Room();
        room.setName("Room");
        room.setPrice(100);
        room.setMaxPeople(2);

        // RoomRepository.save() 호출 시 room 객체 반환하도록 설정
        // Stub roomRepository.save() to return the test room
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // 서비스 메서드 호출
        // Call the service method
        Room savedRoom = roomService.addRoom(room);

        // 결과 검증
        // Verify the result
        assertNotNull(savedRoom);                  // 저장된 방이 null이 아님
        assertEquals("Room", savedRoom.getName()); // 이름 확인
        assertEquals(100, savedRoom.getPrice());   // 가격 확인
        assertEquals(2, savedRoom.getMaxPeople()); // 최대 인원 확인
    }

    @Test
    // 가격이 음수일 때 IllegalArgumentException이 발생하는지 테스트합니다.
    // Tests if IllegalArgumentException is thrown when the price is negative.
    void addRoom_ShouldThrowException_WhenPriceIsNegative() {
        Room room = new Room();
        room.setName("Room");
        room.setPrice(-100);
        room.setMaxPeople(3);

        // addRoom 호출 시 IllegalArgumentException이 발생하는지 확인
        // Verify that IllegalArgumentException is thrown when addRoom is called
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            roomService.addRoom(room);
        });
        assertEquals("Price must be non-negative.", exception.getMessage());
    }

    @Test
    // 최대 인원이 1 미만일 때 IllegalArgumentException이 발생하는지 테스트합니다.
    // Tests if IllegalArgumentException is thrown when maxPeople is less than one.
    void addRoom_ShouldThrowException_WhenMaxPeopleLessThanOne() {
        Room room = new Room();
        room.setName("Room");
        room.setPrice(100);
        room.setMaxPeople(-2);

        // addRoom 호출 시 IllegalArgumentException이 발생하는지 확인
        // Verify that IllegalArgumentException is thrown when addRoom is called
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            roomService.addRoom(room);
        });
        assertEquals("MaxPeople must be non-negative.", exception.getMessage());
    }

    @Test
    void getAllRooms_ShouldReturnRoom() {
        // 테스트용 방 리스트 생성
        // Create a test list of rooms
        List<Room> roomList = new ArrayList<>();
        Room room1 = new Room();
        room1.setName("Room");
        room1.setPrice(100);
        room1.setMaxPeople(2);
        roomList.add(room1);

        // findAll() 호출 시 roomList 반환
        // Stub findAll() to return the test list
        when(roomRepository.findAll()).thenReturn(roomList);

        // 서비스 메서드 호출
        // Call the service method
        List<Room> results = roomService.getAllRooms();

        // 첫 번째 방의 속성 검증
        // Verify attributes of the first room
        Room firstRoom = results.get(0);
        assertEquals("Room", firstRoom.getName());
        assertEquals(100, firstRoom.getPrice());
        assertEquals(2, firstRoom.getMaxPeople());
    }

    @Test
    void getRoomById_ShouldReturnRoom() {
        // 테스트용 Room 객체 생성
        // Create a test Room object
        Room room = new Room();
        room.setName("Room");
        room.setPrice(100);
        room.setMaxPeople(2);

        // findById() 호출 시 Optional.of(room) 반환
        // Stub findById() to return the test room
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        // 서비스 메서드 호출
        // Call the service method
        Room results = roomService.getRoomById(1L);

        // 결과 검증
        // Verify the result
        assertEquals("Room", results.getName());
        assertEquals(100, results.getPrice());
        assertEquals(2, results.getMaxPeople());
    }

    @Test
    void updatedRoom_ShouldSaveRoom() {
        // 기존 방 객체
        // Existing room object
        Room room = new Room();
        room.setName("Room");
        room.setPrice(100);
        room.setMaxPeople(2);

        // 수정된 방 정보
        // Updated room object
        Room updateRoom = new Room();
        updateRoom.setName("Room");
        updateRoom.setPrice(200);
        updateRoom.setMaxPeople(4);

        // findById() → 기존 방 반환 / save() → 수정된 방 반환
        // Stub findById() and save()
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(updateRoom);

        // 서비스 메서드 호출
        // Call the service method
        Room results = roomService.updateRoom(1L, updateRoom);

        // 결과 검증
        // Verify the result
        assertEquals("Room", results.getName());
        assertEquals(200, results.getPrice());
        assertEquals(4, results.getMaxPeople());
    }

    @Test
    void deleteRoom_ShouldRemoveRoom() {
        // 삭제 대상 방 객체 생성
        // Create a room to be deleted
        Room room = new Room();
        room.setName("Room");
        room.setPrice(100);
        room.setMaxPeople(2);

        // findById() 호출 시 해당 방 반환
        // Stub findById() to return the room
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        // deleteRoom() 호출
        // Call deleteRoom()
        roomService.deleteRoom(1L);

        // deleteById()가 호출되었는지 검증
        // Verify that deleteById() was called
        verify(roomRepository).deleteById(1L);
    }
}