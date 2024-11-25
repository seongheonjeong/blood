package com.example.blood.dto;

public class DonationAmountDto {

    private String donationType;
    private String giveaway;
    private String totalAmount;

    public DonationAmountDto() {
    }

    public DonationAmountDto(String donationType, String giveaway, String totalAmount) {
        this.donationType = donationType;
        this.giveaway = giveaway;
        this.totalAmount = totalAmount;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
