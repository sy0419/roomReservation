package com.roomreservation.reservation.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.roomreservation.reservation.domain.Reservation;
import com.roomreservation.reservation.domain.Room;
import com.roomreservation.reservation.domain.User;
import com.roomreservation.reservation.repository.ReservationRepository;

public class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository; // ReservationRepository 목 객체 생성 / Mock ReservationRepository

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl; // ReservationServiceImpl에 목 객체 주입 / Inject mocks into ReservationServiceImpl

    @BeforeEach
    public void setup() {
        // Mockito 초기화 / Initialize Mockito mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addReservation_ShouldSaveReservation_WhenInputIsValid() {
        // 사용자, 방, 예약 객체 생성 및 세팅 / Create and set User, Room, Reservation
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(1)); // 체크인 날짜: 내일 / Check-in date: tomorrow
        reservation.setCheckOutDate(LocalDate.now().plusDays(2)); // 체크아웃 날짜: 모레 / Check-out date: day after tomorrow
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        // 저장 시 목 동작 설정 / Mock behavior on save
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // 서비스 메서드 호출 / Call service method
        Reservation results = reservationServiceImpl.addReservation(reservation);

        // 결과 검증 / Verify results
        assertNotNull(results);
        assertEquals(LocalDate.now().plusDays(1), results.getCheckInDate());
        assertEquals(LocalDate.now().plusDays(2), results.getCheckOutDate());

        // 저장 메서드가 호출되었는지 검증 / Verify save method called
        verify(reservationRepository).save(reservation);

        // 결과 출력 (테스트 시) / Print result for debug
        System.out.println("예약된 check-in 날짜: " + results.getCheckInDate());
        System.out.println("예약된 check-out 날짜: " + results.getCheckOutDate());
    }

    @Test
    void addReservation_ShouldThrowException_WhenInputIsInvalid() {
        // 예약 객체의 checkInDate가 null인 경우 예외 발생 테스트 / Test exception when checkInDate is null
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(null);  // null 체크인 날짜 / null checkInDate
        reservation.setCheckOutDate(LocalDate.now().plusDays(2));
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        // IllegalArgumentException 예외 발생 검증 / Verify IllegalArgumentException thrown
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.addReservation(reservation);
        });

        assertEquals("Check your reservation.", exception.getMessage());
    }

    @Test
    void addReservation_ShouldThrowException_WhenOutputIsInvalid() {
        // 예약 객체의 checkOutDate가 null인 경우 예외 발생 테스트 / Test exception when checkOutDate is null
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(1));
        reservation.setCheckOutDate(null); // null 체크아웃 날짜 / null checkOutDate
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.addReservation(reservation);
        });

        assertEquals("Check your reservation.", exception.getMessage());
    }

    @Test
    void addReservation_ShouldThrowException_WhenCheckOutDateIsBeforeCheckInDate() {
        // 체크아웃 날짜가 체크인 날짜보다 이전일 때 예외 테스트 / Test exception when checkOutDate is before checkInDate
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(3));
        reservation.setCheckOutDate(LocalDate.now().plusDays(2)); // checkOutDate < checkInDate
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.addReservation(reservation);
        });

        assertEquals("Check your reservation.", exception.getMessage());
    }

    @Test
    void addReservation_ShouldThrowException_WhenNumberOfPeopleIsZeroOrNegative() {
        // 예약 인원이 0 또는 음수일 때 예외 테스트 / Test exception when numberOfPeople is zero or negative
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(2));
        reservation.setCheckOutDate(LocalDate.now().plusDays(3));
        reservation.setNumberOfPeople(-4); // 음수 인원 / negative people count
        reservation.setIsApproved(true);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.addReservation(reservation);
        });

        assertEquals("Check your reservation.", exception.getMessage());
    }

    @Test
    void getAllReservations_ShouldReturnAllReservations() {
        // 모든 예약 조회 테스트 / Test get all reservations
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(1));
        reservation.setCheckOutDate(LocalDate.now().plusDays(2));
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        when(reservationRepository.findAll()).thenReturn(List.of(reservation));

        List<Reservation> results = reservationServiceImpl.getAllReservations();
        Reservation firstReservation = results.get(0);
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(4, firstReservation.getNumberOfPeople());
        assertEquals(user.getName(), firstReservation.getUser().getName());
    }

    @Test
    void getReservationById_ShouldReturnReservation_WhenIdExists() {
        // ID로 예약 조회 성공 테스트 / Test get reservation by ID when exists
        User user = new User();
        user.setName("Harry");
        user.setEmail("test@example.com");
        user.setPassword("1234");

        Room room = new Room();
        room.setName("Room1");
        room.setDescription("Cozy");
        room.setMaxPeople(4);
        room.setPrice(100);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(LocalDate.now().plusDays(1));
        reservation.setCheckOutDate(LocalDate.now().plusDays(2));
        reservation.setNumberOfPeople(4);
        reservation.setIsApproved(true);

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Reservation result = reservationServiceImpl.getReservationById(1L);

        assertNotNull(result);
        assertEquals("Harry", result.getUser().getName());
        assertEquals("Room1", result.getRoom().getName());
    }

    @Test
    void getReservationById_ShouldThrowException_WhenIdDoesNotExist() {
        // ID로 예약 조회 실패 테스트 / Test exception thrown when reservation ID does not exist
        Long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.getReservationById(reservationId);
        });

        assertEquals("The reservation does not exist.", exception.getMessage());
    }

    @Test
    void updateReservation_ShouldUpdateFields_WhenInputIsValid() {
        // 예약 수정 성공 테스트 / Test update reservation successfully
        Long reservationId = 1L;

        Reservation existing = new Reservation();
        existing.setId(reservationId);
        existing.setCheckInDate(LocalDate.now().plusDays(1));
        existing.setCheckOutDate(LocalDate.now().plusDays(2));
        existing.setNumberOfPeople(2);
        existing.setIsApproved(false);

        Reservation updated = new Reservation();
        updated.setCheckInDate(LocalDate.now().plusDays(2));
        updated.setCheckOutDate(LocalDate.now().plusDays(3));
        updated.setNumberOfPeople(3);
        updated.setIsApproved(true);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existing));
        when(reservationRepository.save(any())).thenReturn(updated);

        Reservation result = reservationServiceImpl.updateReservation(reservationId, updated);

        assertEquals(updated.getCheckInDate(), result.getCheckInDate());
        assertEquals(updated.getCheckOutDate(), result.getCheckOutDate());
        assertEquals(updated.getNumberOfPeople(), result.getNumberOfPeople());
        assertEquals(updated.getIsApproved(), result.getIsApproved());
    }

    @Test
    void updateReservation_ShouldThrowException_WhenReservationDoesNotExist() {
        // 없는 예약 수정 시 예외 테스트 / Test exception when updating non-existent reservation
        Long reservationId = 999L;

        Reservation updated = new Reservation();
        updated.setCheckInDate(LocalDate.now().plusDays(2));
        updated.setCheckOutDate(LocalDate.now().plusDays(3));
        updated.setNumberOfPeople(3);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.updateReservation(reservationId, updated);
        });

        assertEquals("The reservation does not exist.", exception.getMessage());
    }

    @Test
    void updateReservation_ShouldThrowException_WhenCheckInDateIsAfterCheckOutDate() {
        // 수정 시 checkInDate가 checkOutDate보다 늦을 경우 예외 테스트 / Test exception when checkInDate is after checkOutDate on update
        Long reservationId = 1L;
        Reservation existing = new Reservation();
        existing.setId(reservationId);
        existing.setCheckInDate(LocalDate.now().plusDays(1));
        existing.setCheckOutDate(LocalDate.now().plusDays(3));
        existing.setNumberOfPeople(2);

        Reservation updated = new Reservation();
        updated.setCheckInDate(LocalDate.now().plusDays(4));  // checkOutDate 이후 날짜 설정 / After checkOutDate
        updated.setCheckOutDate(LocalDate.now().plusDays(3));
        updated.setNumberOfPeople(3);

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existing));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.updateReservation(reservationId, updated);
        });

        // 메시지 오류 있을 수 있으니, 서비스 코드 확인 권장 / Suggest checking service message if this fails
        assertEquals("The reservation does not exist.", exception.getMessage());
    }

    @Test
    void deleteReservation_ShouldDeleteReservation_WhenIdExists() {
        // 예약 삭제 성공 테스트 / Test delete reservation when ID exists
        Long id = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(id);

        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        reservationServiceImpl.cancelReservation(id);

        verify(reservationRepository).delete(reservation);
    }

    @Test
    void deleteReservation_ShouldThrowException_WhenIdDoesNotExist() {
        // 존재하지 않는 예약 삭제 시 예외 테스트 / Test exception when deleting non-existent reservation
        Long reservationId = 1L;
        when(reservationRepository.existsById(reservationId)).thenReturn(false);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationServiceImpl.cancelReservation(reservationId);
        });
        assertEquals("The reservation does not exist.", exception.getMessage());
    }
}