package com.tie.foodfeed.Model;

public class DonateModel {
    private String donorName;
    private String donatedBy;
    private String typeofFood;
    private String quantityofFood;
    private String donorMobileNo;
    private String Address;

    private String donationId;

    public DonateModel() {
    }

    public DonateModel(String donorName, String donatedBy, String typeofFood, String quantityofFood, String donorMobileNo, String address) {
        this.donorName = donorName;
        this.donatedBy = donatedBy;
        this.typeofFood = typeofFood;
        this.quantityofFood = quantityofFood;
        this.donorMobileNo = donorMobileNo;
        Address = address;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonatedBy() {
        return donatedBy;
    }

    public void setDonatedBy(String donatedBy) {
        this.donatedBy = donatedBy;
    }

    public String getTypeofFood() {
        return typeofFood;
    }

    public void setTypeofFood(String typeofFood) {
        this.typeofFood = typeofFood;
    }

    public String getQuantityofFood() {
        return quantityofFood;
    }

    public void setQuantityofFood(String quantityofFood) {
        this.quantityofFood = quantityofFood;
    }

    public String getDonorMobileNo() {
        return donorMobileNo;
    }

    public void setDonorMobileNo(String donorMobileNo) {
        this.donorMobileNo = donorMobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }
}
