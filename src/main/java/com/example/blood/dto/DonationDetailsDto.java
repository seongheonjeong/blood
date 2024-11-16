package com.example.blood.dto;

public class DonationDetailsDto {
    private Long donationCertificateNumber;
    private String memberName;
    private String memberId;
    private String patientId;
    private String patientName;

    public DonationDetailsDto() {
    }

    public DonationDetailsDto(Long donationCertificateNumber, String memberName, String memberId, String patientId, String patientName) {

        this.donationCertificateNumber = donationCertificateNumber;
        this.memberName = memberName;
        this.memberId = memberId;
        this.patientId = patientId;
        this.patientName = patientName;
    }

    public Long getDonationCertificateNumber() {
        return donationCertificateNumber;
    }

    public void setDonationCertificateNumber(Long donationCertificateNumber) {
        this.donationCertificateNumber = donationCertificateNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
