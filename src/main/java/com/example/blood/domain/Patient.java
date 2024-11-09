package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "환우")
public class Patient {
    @Id
    @Column(name = "환자ID")
    private String patientId;

    @Column(name = "이름")
    private String name;

    @Column(name = "생년월일")
    private LocalDate birthDate;

    @Column(name = "휴대폰번호")
    private String phoneNumber;

    @Column(name = "성별")
    private String gender;

    @Column(name = "병원이름")
    private String hospitalName;

    @Column(name = "병명")
    private String diseaseName;

    @OneToMany(mappedBy = "Patient", cascade = CascadeType.ALL)
    private List<BloodDonationRecord> bloodDonationRecords = new ArrayList<>();

    public List<BloodDonationRecord> getBloodDonationRecords() {
        return bloodDonationRecords;
    }

    public void setBloodDonationRecords(List<BloodDonationRecord> bloodDonationRecords) {
        this.bloodDonationRecords = bloodDonationRecords;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
