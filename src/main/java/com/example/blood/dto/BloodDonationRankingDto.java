package com.example.blood.dto;

public class BloodDonationRankingDto {

    private String memberId;
    private String memberName;
    private Integer donationCount;
    private Integer donationRanking;

    public BloodDonationRankingDto() {
    }

    public BloodDonationRankingDto(String memberId, String memberName, Integer donationCount, Integer donationRanking) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.donationCount = donationCount;
        this.donationRanking = donationRanking;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(Integer donationCount) {
        this.donationCount = donationCount;
    }

    public Integer getDonationRanking() {
        return donationRanking;
    }

    public void setDonationRanking(Integer donationRanking) {
        this.donationRanking = donationRanking;
    }
}
