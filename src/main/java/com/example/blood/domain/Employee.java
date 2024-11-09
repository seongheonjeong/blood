package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "직원")
public class Employee {

    @Id
    @Column(name = "직원ID", nullable = false)
    private String employeeId;

    @Column(name = "이름", nullable = false)
    private String name;

    @Column(name = "생년월일")
    private LocalDate birthDate;

    @Column(name = "성별")
    private String gender;

    @Column(name = "휴대폰번호")
    private String phoneNumber;

    @Column(name = "주소")
    private String address;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    //양방향 매핑
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
}