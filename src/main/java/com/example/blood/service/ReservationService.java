package com.example.blood.service;

import com.example.blood.domain.Reservation;
import com.example.blood.dto.ReservationDto;
import com.example.blood.repository.JpaReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final JpaReservationRepository jpaReservationRepository;

    @Autowired
    public ReservationService(JpaReservationRepository jpaReservationRepository) {
        this.jpaReservationRepository = jpaReservationRepository;
    }
    public List<ReservationDto> getAllReservations() {

        return jpaReservationRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    private ReservationDto convertDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getReservationId(),
                reservation.getReservationDateTime(),
                reservation.getReservationStatus(),
                reservation.getMember().getName(),
                reservation.getEmployee().getName()
        );
    }
    public List<ReservationDto> getResercationsfByMemberName(String memberName) {
        return jpaReservationRepository.findByMemberName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getReservationsfByEmployeeName(String employeeName) {
        return jpaReservationRepository.findByEmployeeName(employeeName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
}
