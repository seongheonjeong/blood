package com.example.blood.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class MemberDto {

    private String memberId;
    private String name;
    private LocalDate birth;
    private String gender;

    private String bloodType;
    private String phoneNumber;
    private String address;
    private Integer donationCount;
    private LocalDate firstDonationDate;
    private LocalDate lastDonationDate;

    public MemberDto() {
    }

    public MemberDto(String memberId, String name, LocalDate birth, String gender, String bloodType, String phoneNumber, String address, Integer donationCount, LocalDate firstDonationDate, LocalDate lastDonationDate) {
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
