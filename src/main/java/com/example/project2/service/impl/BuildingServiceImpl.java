package com.example.project2.service.impl;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.converter.BuildingSearchBuilderConverter;
import com.example.project2.model.BuildingDTO;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.DistrictRepository;
import com.example.project2.repository.RentAreaRepository;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.repository.entity.DistrictEntity;
import com.example.project2.repository.entity.RentAreaEntity;
import com.example.project2.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {


        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);
            List<RentAreaEntity> rentAreaEntities = rentAreaRepository.getValueById(item.getId());
            String rentAreaValue = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
            buildingDTO.setRentArea(rentAreaValue);
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }
}
