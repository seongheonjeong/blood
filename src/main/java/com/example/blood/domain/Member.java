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
@Table(name = "헌혈자")
public class Member {

    @Id
    @Column(name = "회원ID", nullable = false)
    private String memberId;

    @Column(name = "이름", nullable = false)
    private String name;

    @Column(name = "생년월일")
    private LocalDate birth;

    @Column(name = "성별")
    private String gender;

    @Column(name = "혈액형")
    private String bloodType;

    @Column(name = "휴대폰번호")
    private String phoneNumber;

    @Column(name = "주소")
    private String address;

    @Column(name = "헌혈횟수")
    private int donationCount;

    @Column(name = "최초헌혈일")
    private LocalDate firstDonationDate;

    @Column(name = "마지막헌혈일")
    private LocalDate lastDonationDate;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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

    public int getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(int donationCount) {
        this.donationCount = donationCount;
    }

    public LocalDate getFirstDonationDate() {
        return firstDonationDate;
    }

    public void setFirstDonationDate(LocalDate firstDonationDate) {
        this.firstDonationDate = firstDonationDate;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BloodDonationRecord> bloodDonationRecords = new ArrayList<>();
    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<BloodDonationRecord> getBloodDonationRecords() {
        return bloodDonationRecords;
    }

    public void setBloodDonationRecords(List<BloodDonationRecord> bloodDonationRecords) {
        this.bloodDonationRecords = bloodDonationRecords;
    }

}