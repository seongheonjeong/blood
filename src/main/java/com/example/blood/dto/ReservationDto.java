package com.example.blood.dto;


import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//클라이언트에게 응답 객체

public class ReservationDto {
    private Long reservationId; //예약번호
    private LocalDateTime reservationDateTime; //예약일시
    private String reservationStatus; //예약상태
    private String memberName;
    private String employeeName;

    public ReservationDto() {
    }

    public ReservationDto(Long reservationId, LocalDateTime reservationDateTime, String reservationStatus, String memberName, String employeeName) {
        this.reservationId = reservationId;
        this.reservationDateTime = reservationDateTime;
        this.reservationStatus = reservationStatus;
        this.memberName = memberName;
        this.employeeName = employeeName;
    }
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }


    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }


}
