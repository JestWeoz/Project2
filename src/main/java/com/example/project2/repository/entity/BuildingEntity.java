package com.example.project2.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

//    @Column(name = "districtid")
//    private int districtId;

    @Column(name = "ward")
    private String ward;

    @Column(name = "brokeragefee")
    private String brokerageFee;

    @Column(name = "servicefeet")
    private String serviceFee;

    @Column(name = "numberofbasement")
    private long numberOfBasement;

    @Column(name = "floorarea")
    private long floorArea;

    @Column(name = "rentprice")
    private long rentPrice;


    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphonenumber")
    private String managerPhoneNumber;


    @ManyToOne
    @JoinColumn(name = "districtid")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreaEntity = new ArrayList<RentAreaEntity>();

    public List<RentAreaEntity> getRentAreaEntity() {
        return rentAreaEntity;
    }

    public void setRentAreaEntity(List<RentAreaEntity> items) {
        this.rentAreaEntity = items;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }
    public long getId() {
        return id;
    }

//    public int getDistrictId() {
//        return districtId;
//    }
//
//    public void setDistrictId(int districtId) {
//        this.districtId = districtId;
//    }

    public void setId(long id) {
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

    public void setName(String buildingName) {
        this.name = buildingName;
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

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}