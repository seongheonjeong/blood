package com.example.blood.dto;

public class DonationAmountDto {

    private String donationType;
    private String giveaway;
    private int totalAmount;
    public DonationAmountDto(String donationType, String giveaway, int totalAmount) {
        this.donationType = donationType;
        this.giveaway = giveaway;
        this.totalAmount = totalAmount;
    }
    public DonationAmountDto() {
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(String giveaway) {
        this.giveaway = giveaway;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
