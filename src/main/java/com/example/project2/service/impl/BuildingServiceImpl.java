package com.example.project2.service.impl;

import com.example.project2.model.BuildingDTO;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.DistrictRepository;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.repository.entity.DistrictEntity;
import com.example.project2.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service

public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {

        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setBuildingName(item.getName());
            buildingDTO.setPhoneNumber(item.getManagerPhoneNumber());
            DistrictEntity district = districtRepository.findNameById(item.getDistrictId());
            buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + district.getDistrictName());
            buildingDTO.setFloorArea(item.getFloorArea());
            buildingDTO.setManagerName(item.getManagerName());
            buildingDTO.setRentPrice(item.getRentPrice());
            buildingDTO.getNumberOfBasement(item.getNumberOfBasement());
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }
}
