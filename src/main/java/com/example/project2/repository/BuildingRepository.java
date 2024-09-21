package com.example.project2.repository;

import com.example.project2.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface BuildingRepository {
    List<BuildingEntity> findAll(Map<String, Object> params, List<String> rentTypeCode);



}
