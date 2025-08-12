package com.roomreservation.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.roomreservation.reservation.domain.Room;
import com.roomreservation.reservation.repository.RoomRepository;

public class RoomServiceTest {
    private RoomServiceImpl roomService;
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
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
        room.setName("Room1");
        room.setPrice(100);
        room.setMaxPeople(2);

        // RoomRepository.save()가 호출되면 위에서 만든 room을 반환하도록 설정
        // When save() is called, return the predefined room
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // 서비스 메서드 호출
        // Call the service method
        Room savedRoom = roomService.addRoom(room);

        // 결과 검증
        // Verify the result
        assertNotNull(savedRoom); // 저장된 방이 null이 아님
        assertEquals("Room1", savedRoom.getName()); // 이름 확인
        assertEquals(100, savedRoom.getPrice());    // 가격 확인
        assertEquals(2, savedRoom.getMaxPeople());  // 최대 인원 확인
    }
}