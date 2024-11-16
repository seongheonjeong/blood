package com.example.blood.dto;

public class BloodDonationRankingDto {

    private String memberId;
    private String memberName;
    private int donationCount;
    private int donationRanking;

    public BloodDonationRankingDto() {
    }

    public BloodDonationRankingDto(String memberId, String memberName, int donationCount, int donationRanking) {
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

    public int getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(int donationCount) {
        this.donationCount = donationCount;
    }

    public int getDonationRanking() {
        return donationRanking;
    }

    public void setDonationRanking(int donationRanking) {
        this.donationRanking = donationRanking;
    }
}
