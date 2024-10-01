package com.example.project2.model;

public class BuildingDTO {
    private String buildingName;
    private long numberOfBasement;
    private String managerName;
    private String phoneNumber;
    private String address;
    private long floorArea;
    private long emptyArea;
    private long rentPrice;
    private String serviceFee;
    private String brokerageFee;

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    private String rentArea;

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public long getRentPrice() {
        return rentPrice;
    }

    public void setRent(long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public long getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(long emptyArea) {
        this.emptyArea = emptyArea;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(long floorArea) {
        this.floorArea = floorArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public long getNumberOfBasement(long numberOfBasement) {
        return this.numberOfBasement;
    }

    public void setNumberOfBasement(long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
}