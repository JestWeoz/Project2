package com.example.project2.repository.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
