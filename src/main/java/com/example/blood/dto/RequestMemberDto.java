package com.example.blood.dto;

import java.time.LocalDate;

//이름, 생년월일, 성별, 혈액형, 휴대폰번호, 주소
public class RequestMemberDto {
    private String name;
    private LocalDate birth;
    private String gender;
    private String bloodType;
    private String phoneNumber;
    private String address;

    public RequestMemberDto() {
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public RequestMemberDto(String name, LocalDate birth, String gender, String bloodType, String phoneNumber, String address) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public void setDate(LocalDate birth) {
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
}
