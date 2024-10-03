package com.example.project2.converter;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setManagerName(MapUtil.getObject(params, "managerName",String.class))
                .setDistrictCode(MapUtil.getObject(params, "districtId", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
                .setTypeCode(typeCode)
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
                .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .build();
        return buildingSearchBuilder;
    }
}
