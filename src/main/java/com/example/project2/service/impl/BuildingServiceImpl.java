package com.example.project2.service.impl;

import com.example.project2.model.BuildingDTO;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public List<BuildingDTO> findAll(String name, Long districtID, String street, String ward, Long numberOfBasement, Long floorArea, Long area1, Long area2, Long rent1, Long rent2, List<Long> rentTypeId) {

        List<BuildingEntity> buildingEntities = buildingRepository.findAll(name,  districtID,  street,  ward,  numberOfBasement,  floorArea,  area1,  area2,  rent1,  rent2,  rentTypeId);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = getBuildingDTO(item);
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }

    private static BuildingDTO getBuildingDTO(BuildingEntity item) {
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setBuildingName(item.getName());
        buildingDTO.setAddress(item.getStreet() + ", " + item.getWard() + ", " + "districtId = " + item.getDistrict());
        buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
        buildingDTO.setFloorArea(item.getFloorArea());
        buildingDTO.setManagerName(item.getNameManager());
        buildingDTO.setPhoneNumber(item.getPhoneNumberManager());
        buildingDTO.setRentPrice(item.getRentPrice());
        buildingDTO.setFloorArea(item.getFloorArea());
        return buildingDTO;
    }
}
