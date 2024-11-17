package com.example.blood.dto;

import lombok.NoArgsConstructor;


public class ReservationCountDto {
    private String employeeId;

    private String name;

    private int reservationCount;

    public ReservationCountDto() {
    }

    public ReservationCountDto(String employeeId, String name, int reservationCount) {
        this.employeeId = employeeId;
        this.name = name;
        this.reservationCount = reservationCount;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }
}
