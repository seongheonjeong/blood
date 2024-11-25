package com.example.blood.dto;

import java.time.LocalDate;

public class BloodDonationRecordDto {
    private Long donationRecordId;       // 헌혈기록번호
    private String memberName;           // 회원 이름
    private Long donationCertificateNumber; // 헌혈증서번호
    private String employeeName;         // 담당 직원 이름
    private LocalDate donationDate;      // 헌혈일자
    private String donationType;         // 헌혈종류
    private String donationAmount;      // 헌혈량
    private LocalDate expirationDate;    // 유효기간
    private String giveaway;         // 증정품
    private String bloodDonationRelaySession;          // 헌혈 릴레이 회차
    private String donationPatientName;  // 기부 환자 이름

    public BloodDonationRecordDto() {
    }

    public BloodDonationRecordDto(Long donationRecordId, String memberName, Long donationCertificateNumber, String employeeName, LocalDate donationDate, String donationType, String donationAmount, LocalDate expirationDate, String giveaway, String bloodDonationRelaySession, String donationPatientName) {
        this.donationRecordId = donationRecordId;
        this.memberName = memberName;
        this.donationCertificateNumber = donationCertificateNumber;
        this.employeeName = employeeName;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.donationAmount = donationAmount;
        this.expirationDate = expirationDate;
        this.giveaway = giveaway;
        this.bloodDonationRelaySession = bloodDonationRelaySession;
        this.donationPatientName = donationPatientName;
    }

    public Long getDonationRecordId() {
        return donationRecordId;
    }

    public void setDonationRecordId(Long donationRecordId) {
        this.donationRecordId = donationRecordId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getDonationCertificateNumber() {
        return donationCertificateNumber;
    }

    public void setDonationCertificateNumber(Long donationCertificateNumber) {
        this.donationCertificateNumber = donationCertificateNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount = donationAmount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(String giveaway) {
        this.giveaway = giveaway;
    }

    public String getBloodDonationRelaySession() {
        return bloodDonationRelaySession;
    }

    public void setBloodDonationRelaySession(String bloodDonationRelaySession) {
        this.bloodDonationRelaySession = bloodDonationRelaySession;
    }

    public String getDonationPatientName() {
        return donationPatientName;
    }

    public void setDonationPatientName(String donationPatientName) {
        this.donationPatientName = donationPatientName;
    }
}
