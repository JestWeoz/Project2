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

    private String name;
    private String street;
    private String ward;
    private String brokerageFee;
    private String serviceFee;
    private long numberOfBasement;
    private long floorArea;
    private long rentPrice;
    private String managerName;
    private String managerPhoneNumber;


    @ManyToOne
    @JoinColumn(name = "districtid")
    private DistrictEntity district;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreaEntity = new ArrayList<RentAreaEntity>();

    public List<RentAreaEntity> getRentAreaEntity() {
        return rentAreaEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
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

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public void setRentAreaEntity(List<RentAreaEntity> rentAreaEntity) {
        this.rentAreaEntity = rentAreaEntity;
    }
}