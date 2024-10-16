package com.example.project2.api;

import com.example.project2.model.BuildingDTO;
import com.example.project2.model.BuildingRequestDTO;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.repository.entity.DistrictEntity;
import com.example.project2.service.BuildingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class BuildingAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BuildingService buildingService;
    @GetMapping(value="/api/building/")
    public List<BuildingDTO> building(@RequestParam Map<String, Object> params,
                                      @RequestParam(value = "typeCode", required = false) List<String> typeCode) {
        return buildingService.findAll(params, typeCode);
    }
    @PostMapping(value = "/api/building/")
    @Transactional
    public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setName(buildingRequestDTO.getName());
        buildingEntity.setWard(buildingRequestDTO.getWard());
        DistrictEntity districtEntity = new DistrictEntity();
        districtEntity.setId(buildingRequestDTO.getDistrictId());
        buildingEntity.setDistrict(districtEntity);
        buildingEntity.setStreet(buildingRequestDTO.getStreet());
        entityManager.persist(buildingEntity);
        System.out.println("oke");
    }
    @DeleteMapping(value = "/api/building/{id}")
    @Transactional
    public void deleteBuilding(@PathVariable Long id) {
        BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
        entityManager.remove(buildingEntity);
    }
}
