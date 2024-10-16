package com.example.project2.model;

public class BuildingRequestDTO {
    private String name;
    private String street;
    private String ward;
    private long rentPrice;
    private long districtId;

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

    public long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }
}
