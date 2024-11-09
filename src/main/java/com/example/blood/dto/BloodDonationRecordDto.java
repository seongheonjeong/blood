package com.example.blood.dto;

import java.time.LocalDate;

public class BloodDonationRecordDto {
    private Long donationRecordId;       // 헌혈기록번호
    private String memberName;           // 회원 이름
    private Long donationCertificateNumber; // 헌혈증서번호
    private String employeeName;         // 담당 직원 이름
    private LocalDate donationDate;      // 헌혈일자
    private String donationType;         // 헌혈종류
    private int donationAmount;      // 헌혈량
    private LocalDate expirationDate;    // 유효기간
    private String giveaway;         // 증정품
    private String relayRound;          // 헌혈 릴레이 회차
    private String donationPatientName;  // 기부 환자 이름
}
