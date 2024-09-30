package com.example.project2.repository;

import com.example.project2.repository.entity.DistrictEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository {
    DistrictEntity findNameById(int id);
}
