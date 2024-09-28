package com.example.project2.repository.entity;

public class BuildingEntity {
    private String name;
    private String street;
    private String ward;
    private Integer numberOfBasement;
    private String district;
    private long floorArea;
    private String direction;
    private String rank;
    private long area1;
    private long area2;



    private long rent1;
    private long rent2;
    private String nameManager;
    private String phoneNumberManager;
    private long staffId;
    private long rentTypeId;
    private long rentPrice;

    public long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public long getArea1() {
        return area1;
    }

    public void setArea1(long area1) {
        this.area1 = area1;
    }

    public long getArea2() {
        return area2;
    }

    public void setArea2(long area2) {
        this.area2 = area2;
    }

    public long getRent1() {
        return rent1;
    }

    public void setRent1(long rent1) {
        this.rent1 = rent1;
    }

    public long getRent2() {
        return rent2;
    }

    public void setRent2(long rent2) {
        this.rent2 = rent2;
    }

    public String getNameManager() {
        return nameManager;
    }

    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    public String getPhoneNumberManager() {
        return phoneNumberManager;
    }

    public void setPhoneNumberManager(String phoneNumberManager) {
        this.phoneNumberManager = phoneNumberManager;
    }

    public long getRentTypeId() {
        return rentTypeId;
    }

    public void setRentTypeId(long rentTypeId) {
        this.rentTypeId = rentTypeId;
    }

    public long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(long floorArea) {
        this.floorArea = floorArea;
    }
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public void setDistrictid(long aLong) {
    }
}
