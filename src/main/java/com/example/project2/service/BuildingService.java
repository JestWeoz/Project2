package com.example.project2.service;

import com.example.project2.model.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public interface BuildingService {
    List<BuildingDTO> findAll(Map<String, Object> params, List<String> rentTypeCode);
}
