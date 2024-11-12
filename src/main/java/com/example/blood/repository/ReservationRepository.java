package com.example.blood.repository;

import com.example.blood.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findByMemberName(String memberName);
    List<Reservation> findAll();
    List<Reservation> findByEmployeeName(String employeeName);
}
