package com.example.blood.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class RequestBloodDonationRecordDto {

    private String memberId; //회원 아이디
    private String employeeId; //담당 직원 아이디

    private LocalDate donationDate;      // 헌혈일자
    private String donationType;         // 헌혈종류
    private Integer donationAmount;      // 헌혈량
    private String giveaway;         // 증정품

    private String donationPatientId;  // 기부 환자 이름

    public RequestBloodDonationRecordDto() {
    }

    public RequestBloodDonationRecordDto(String memberId, String employeeId, LocalDate donationDate, String donationType, Integer donationAmount, String giveaway, String donationPatientId) {
        this.memberId = memberId;
        this.employeeId = employeeId;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.donationAmount = donationAmount;
        this.giveaway = giveaway;
        this.donationPatientId = donationPatientId;
    }
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public Integer getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(Integer donationAmount) {
        this.donationAmount = donationAmount;
    }


    public String getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(String giveaway) {
        this.giveaway = giveaway;
    }


    public String getDonationPatientId() {
        return donationPatientId;
    }

    public void setDonationPatientId(String donationPatientId) {
        this.donationPatientId = donationPatientId;
    }

}
