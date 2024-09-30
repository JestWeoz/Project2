package com.example.project2.repository.entity;

public class BuildingEntity {
    private int id;
    private String name;
    private String street;
    private int districtId;
    private String ward;
    private long numberOfBasement;
    private long floorArea, rentPrice;
    private String managerName, managerPhoneNumber;

    public int getId() {
        return id;
    }
    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(long floorArea) {
        this.floorArea = floorArea;
    }

    public long getRentPrice() {
        return rentPrice;
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

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }
}