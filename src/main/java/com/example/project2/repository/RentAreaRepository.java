package com.example.project2.repository;

import com.example.project2.repository.entity.RentAreaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RentAreaRepository {
    List<RentAreaEntity> getValueById(long id);
}
