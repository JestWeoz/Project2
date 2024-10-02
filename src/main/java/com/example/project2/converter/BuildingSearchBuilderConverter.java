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
                .setFloorArea(MapUtil.getObject(params, "floorarea", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setManagerName(MapUtil.getObject(params, "managername",String.class))
                .setDistrictCode(MapUtil.getObject(params, "districtcide", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
                .setTypeCode(typeCode)
                .setStaffId(MapUtil.getObject(params, "staffid", Long.class))
                .setAreaFrom(MapUtil.getObject(params, "areafrom", Long.class))
                .setAreaTo(MapUtil.getObject(params, "areato", Long.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerphonenumber", String.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentpricefrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentpriceto", Long.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .build();
        return buildingSearchBuilder;
    }
}
