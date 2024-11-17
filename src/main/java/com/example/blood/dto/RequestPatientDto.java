package com.example.blood.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class RequestPatientDto {
    //이름, 생년월일, 휴대폰번호, 성별, 병원이름, 병명
    private String name;
    private LocalDate birth;
    private String phoneNumber;
    private String gender;
    private String hospitalName;
    private String diseaseName;

    public RequestPatientDto() {
    }

    public RequestPatientDto(String name, LocalDate birth, String phoneNumber, String gender, String hospitalName, String diseaseName) {
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hospitalName = hospitalName;
        this.diseaseName = diseaseName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
