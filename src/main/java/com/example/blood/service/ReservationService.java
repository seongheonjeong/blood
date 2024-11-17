package com.example.blood.service;

import com.example.blood.domain.Reservation;
import com.example.blood.dto.ReservationCountDto;
import com.example.blood.dto.ReservationDto;
import com.example.blood.repository.JpaReservationRepository;
import com.example.blood.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public List<ReservationDto> getAllReservations() {

        return reservationRepository.findAll().stream()
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
    public List<ReservationDto> getReservationsByReservationStatus(String reservationStatus) {
        return reservationRepository.findByReservationStatus(reservationStatus).stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }
    public List<ReservationCountDto> getReservationCount() {
        return reservationRepository.findReservationCount().stream()
                .map(reservationCount -> new ReservationCountDto(
                        (String) reservationCount[0],
                        (String) reservationCount[1],
                        ((Number) reservationCount[2]).intValue()
                ))
                .collect(Collectors.toList());
    }
}
