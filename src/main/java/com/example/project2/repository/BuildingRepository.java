package com.example.project2.repository;

import com.example.project2.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BuildingRepository {
    List<BuildingEntity> findAll(String name, Long districtID, String street, String ward, Long numberOfBasement, Long floorArea, Long area1, Long area2, Long rent1, Long rent2, List<Long> rentTypeId);



}
