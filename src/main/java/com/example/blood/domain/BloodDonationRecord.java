package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "헌혈기록")
@SequenceGenerator(
        name = "donationRecordSeq",
        sequenceName = "donation_record_sequence", //오라클 시퀀스명
        initialValue = 10000170,
        allocationSize = 1
)
public class BloodDonationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donationRecordSeq")
    @Column(name = "헌혈기록번호")
    private Long donationRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원ID")
    private Member member;

    @Column(name = "헌혈증서번호")
    private Long donationCertificateNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "담당직원ID")
    private Employee employee;

    @Column(name = "헌혈일자")
    private LocalDate donationDate;

    @Column(name = "헌혈종류")
    private String donationType;

    @Column(name = "헌혈량")
    private Integer donationAmount;

    @Column(name = "유효기간")
    private LocalDate expirationDate;

    @Column(name = "증정품종류")
    private String giveaway;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "헌혈릴레이ID")
    private BloodDonationRelay bloodDonationRelay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "기부환자ID")
    private Patient patient;


    public BloodDonationRecord() {
    }

    public BloodDonationRecord(Long donationRecordId, Member member, Long donationCertificateNumber, Employee employee, LocalDate donationDate, String donationType, Integer donationAmount, LocalDate expirationDate, String giveaway, BloodDonationRelay bloodDonationRelay, Patient patient) {
        this.donationRecordId = donationRecordId;
        this.member = member;
        this.donationCertificateNumber = donationCertificateNumber;
        this.employee = employee;
        this.donationDate = donationDate;
        this.donationType = donationType;
        this.donationAmount = donationAmount;
        this.expirationDate = expirationDate;
        this.giveaway = giveaway;
        this.bloodDonationRelay = bloodDonationRelay;
        this.patient = patient;
    }

    public Long getDonationRecordId() {
        return donationRecordId;
    }

    public void setDonationRecordId(Long donationRecordId) {
        this.donationRecordId = donationRecordId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getDonationCertificateNumber() {
        return donationCertificateNumber;
    }

    public void setDonationCertificateNumber(Long donationCertificateNumber) {
        this.donationCertificateNumber = donationCertificateNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public BloodDonationRelay getBloodDonationRelay() {
        return bloodDonationRelay;
    }

    public void setBloodDonationRelay(BloodDonationRelay bloodDonationRelay) {
        this.bloodDonationRelay = bloodDonationRelay;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
