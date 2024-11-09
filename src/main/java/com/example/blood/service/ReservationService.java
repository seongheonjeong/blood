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

    //dto변환
    private ReservationDto convertDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getReservationId(),
                reservation.getReservationDateTime(),
                reservation.getReservationStatus(),
                reservation.getMember().getName(),
                reservation.getEmployee().getName()
        );
    }
    //List를 Stream형으로 변경 -> Dto 변환 -> 다시 Stream에서 List로
    public List<ReservationDto> getReservationsByMemberName(String memberName) {
        return jpaReservationRepository.findByMemberName(memberName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public List<ReservationDto> getReservationsByEmployeeName(String employeeName) {
        return jpaReservationRepository.findByEmployeeName(employeeName).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
}
