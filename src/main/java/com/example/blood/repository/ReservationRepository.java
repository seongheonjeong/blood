package com.example.blood.repository;

import com.example.blood.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findAll();
    //예약상태가 정상인 직원별 예약횟수 검색
    List<Object[]> findReservationCount();
    public List<Reservation> findByReservationStatus(String reservationStatus);

}
