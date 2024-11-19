package com.example.blood.dto;

public class DonationAmountDto {

    private String donationType;
    private String giveaway;
    private Integer totalAmount;

    public DonationAmountDto() {
    }

    public DonationAmountDto(String donationType, String giveaway, Integer totalAmount) {
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

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
