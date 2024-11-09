package com.example.blood.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "헌혈기록")
public class BloodDonationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "헌혈기록번호")
    private Long donationRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원ID")
    private Member member;

    @Column(name = "헌혈증서번호")
    private Long donationCertificateNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "담당직원ID")
    private Employee employeeId;

    @Column(name = "헌혈일자")
    private LocalDate donationDate;

    @Column(name = "헌혈종류")
    private String donationType;

    @Column(name = "헌혈량")
    private int donationAmount;

    @Column(name = "유효기간")
    private LocalDate expirationDate;

    @Column(name = "증정품종류")
    private String giveaway;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "헌혈릴레이ID")
    private BloodDonationRecord bloodDonationRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "기부환자ID")
    private Patient patient;
}
