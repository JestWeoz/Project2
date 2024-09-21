package com.example.project2.api;

import com.example.project2.model.BuildingDTO;
import com.example.project2.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value="/api/building/")
    public List<BuildingDTO> building(@RequestParam Map<String, Object> params,
                                      @RequestParam List<String> rentTypeCode) {
        return buildingService.findAll(params, rentTypeCode);
    }
}
