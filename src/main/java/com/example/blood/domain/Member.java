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
    private Integer donationCount;

    @Column(name = "최초헌혈일")
    private LocalDate firstDonationDate;

    @Column(name = "마지막헌혈일")
    private LocalDate lastDonationDate;

    public Member() {
    }

    public Member(String memberId, String name, LocalDate birth, String gender, String bloodType, String phoneNumber, String address, Integer donationCount, LocalDate firstDonationDate, LocalDate lastDonationDate) {
        this.memberId = memberId;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.donationCount = donationCount;
        this.firstDonationDate = firstDonationDate;
        this.lastDonationDate = lastDonationDate;
    }

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

    public Integer getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(Integer donationCount) {
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
}