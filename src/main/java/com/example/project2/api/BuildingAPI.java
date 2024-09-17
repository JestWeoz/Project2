package com.example.project2.api;

import com.example.project2.model.BuildingDTO;
import com.example.project2.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value="/api/building/")
    public List<BuildingDTO> building(@RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "districtid", required = false) Long districtid,
                                      @RequestParam(name = "street", required = false) String street,
                                      @RequestParam(name = "ward", required = false) String ward,
                                      @RequestParam(name = "numberofbasement", required = false) Long numberofbasement,
                                      @RequestParam(name = "floorarea", required = false) Long floorArea,
                                      @RequestParam(name = "area1", required = false) Long area1,
                                      @RequestParam(name = "area2", required = false) Long area2,
                                      @RequestParam(name = "rent1", required = false) Long rent1,
                                      @RequestParam(name = "rent2", required = false) Long rent2,
                                      @RequestParam(name = "renttypeid", required = false) List<Long> rentTypeId
                                      ) {
        return buildingService.findAll(name, districtid, street, ward, numberofbasement, floorArea, area1, area2, rent1, rent2, rentTypeId);
    }
}
