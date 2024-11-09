package com.example.blood.controller;

import com.example.blood.domain.Reservation;
import com.example.blood.dto.ReservationDto;
import com.example.blood.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    //전체 조회
    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }
    //멤버명별 조회
    @GetMapping(params = "memberName")
    public List<ReservationDto> getReservationsByMemberName(@RequestParam String memberName) {
        return reservationService.getReservationsByMemberName(memberName);
    }
    //직원명별 조회
    @GetMapping(params = "employeeName")
    public List<ReservationDto> getReservationsByByEmployeeName(@RequestParam String employeeName) {
        return reservationService.getReservationsByEmployeeName(employeeName);
    }
}
