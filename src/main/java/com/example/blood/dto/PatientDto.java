package com.example.blood.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PatientDto {

    private String patientId;

    private String name;

    private LocalDate birth;

    private String phoneNumber;

    private String gender;

    private String hospitalName;

    private String diseaseName;

    public PatientDto() {
    }

    public PatientDto(String patientId, String name, LocalDate birth, String phoneNumber, String gender, String hospitalName, String diseaseName) {
        this.patientId = patientId;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.hospitalName = hospitalName;
        this.diseaseName = diseaseName;
    }
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
