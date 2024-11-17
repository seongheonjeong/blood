package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity

@Table(name = "예약내역")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "예약번호")
    private Long reservationId;

    @Column(name = "예약일시")
    private LocalDateTime reservationDateTime;

    @Column(name = "예약상태")
    private String reservationStatus;

    public Reservation() {
    }

    public Reservation(Long reservationId, LocalDateTime reservationDateTime, String reservationStatus, Member member, Employee employee) {
        this.reservationId = reservationId;
        this.reservationDateTime = reservationDateTime;
        this.reservationStatus = reservationStatus;
        this.member = member;
        this.employee = employee;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    //지연로딩(필요한 시점에 연관된 객체의 데이터 불러옴)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "담당예약직원ID")
    private Employee employee;

}

