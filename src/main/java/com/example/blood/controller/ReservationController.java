package com.example.blood.controller;

import com.example.blood.dto.ReservationCountDto;
import com.example.blood.dto.ReservationDto;
import com.example.blood.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservationDtoList = reservationService.getAllReservations();
        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);
    }

    //예약상태별 검색
    @GetMapping(params = "reservationStatus")
    public ResponseEntity<List<ReservationDto>> getReservationsByReservationStatus(@RequestParam String reservationStatus) {
        List<ReservationDto> reservationDtoList = reservationService.getReservationsByReservationStatus(reservationStatus);
        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<List<ReservationCountDto>> getReservationsByEmployeeName() {
        List<ReservationCountDto> reservationCountDtoList = reservationService.getReservationCount();
        if (reservationCountDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservationCountDtoList, HttpStatus.OK);
    }
}
