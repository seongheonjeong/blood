package com.example.blood.controller;

import com.example.blood.domain.Reservation;
import com.example.blood.dto.ReservationDto;
import com.example.blood.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    //전체 조회
    @GetMapping("/reservation")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }
    //멤버(헌혈자)명별 조회
    @GetMapping("/reservation/member")
    public List<ReservationDto> getReservationsByMemberName(@RequestParam String memberName) {
        return reservationService.getResercationsfByMemberName(memberName);
    }
    //직원명별 조회
    @GetMapping("/reservation/employee")
    public List<ReservationDto> getReservationsByByEmployeeName(@RequestParam String employeeName) {
        return reservationService.getReservationsfByEmployeeName(employeeName);
    }
}
