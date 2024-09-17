package com.example.project2.service;

import com.example.project2.model.BuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;



public interface BuildingService {
    List<BuildingDTO> findAll(String name, Long districtID, String street, String ward, Long numberOfBasement, Long floorArea, Long area1, Long area2, Long rent1, Long rent2, List<Long> rentTypeId);
}
