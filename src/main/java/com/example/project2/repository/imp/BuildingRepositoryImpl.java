package com.example.project2.repository.imp;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.utils.NumberUtil;
import com.example.project2.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";


    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN buildingrenttype on b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype on renttype.id = buildingrenttype.renttypeid ");
        }
    }

    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//        for(Map.Entry<String, Object> entry : params.entrySet()) {
//            if(!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode") && !entry.getKey().startsWith("rentArea") && !entry.getKey().startsWith("rentPrice")) {
//                String value = String.valueOf(entry.getValue());
//                if (NumberUtil.isNumber(value)) {
//                    where.append(" AND b.").append(entry.getKey()).append(" = ").append(value).append(" ");
//                } else {
//                    where.append(" AND b." + entry.getKey() + " LIKE '%" + value + "%' " );
//                }
//            }
//        }
        try {
            Field[] fields = buildingSearchBuilder.getClass().getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.equals("rentPrice")) {
                    String value = item.get(buildingSearchBuilder).toString();
                    if (StringUtil.checkString(value)) {
                        if (NumberUtil.isNumber(value)) {
                            where.append(" AND b. " + fieldName + " = " + value);
                        } else {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        String staffId = String.valueOf(params.get("staffId"));
        if (StringUtil.checkString(staffId)) {
            where.append(" AND assignmentbuilding.staffid = ").append(staffId).append(" ");
        }
        String rentAreaTo = String.valueOf(params.get("rentAreaTo"));
        String rentAreaFrom = String.valueOf(params.get("rentAreaFrom"));
        if (StringUtil.checkString(rentAreaFrom)) {
            where.append(" AND rentarea.value >=" + rentAreaFrom + " ");
        }
        if (StringUtil.checkString(rentAreaTo)) {
            where.append(" AND rentarea.value <=" + rentAreaTo + " ");
        }
        String rentPriceTo = String.valueOf(params.get("rentPriceTo"));
        String rentPriceFrom = String.valueOf(params.get("rentPriceFrom"));
        if (StringUtil.checkString(rentPriceFrom)) {
            where.append(" AND b.rentprice >=" + rentPriceFrom + " ");
        }
        if (StringUtil.checkString(rentPriceTo)) {
            where.append(" AND b.rentprice <=" + rentPriceTo + " ");
        }
        if (typeCode != null && !typeCode.isEmpty()) {
            List<String> code = new ArrayList<>();
            for (String type : typeCode) {
                code.add("'" + type + "'");
            }
            ;
            where.append(" AND renttype.code in (" + String.join(",", code) + ")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(" SELECT distinct b.* FROM building b ");
        joinTable(params, typeCode, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNormal(params, where);
        querySpecial(params, typeCode, where);
        where.append(" ORDER BY b.id ");
        sql.append(where.toString());
        System.out.println(sql.toString());
        List<BuildingEntity> results = new ArrayList<BuildingEntity>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                BuildingEntity dto = new BuildingEntity();
                dto.setBuildingName(rs.getString("b.name"));
                dto.setId(rs.getInt("b.id"));
                dto.setDistrictId(rs.getInt("b.districtid"));
                dto.setStreet(rs.getString("b.street"));
                dto.setWard(rs.getString("b.ward"));
                dto.setRentPrice(rs.getLong("b.rentPrice"));
                dto.setNumberOfBasement(rs.getInt("b.numberofbasement"));
                dto.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
                dto.setManagerName(rs.getString("b.managername"));
                dto.setFloorArea(rs.getLong("b.floorarea"));
                dto.setServiceFee(rs.getString("b.servicefee"));
                dto.setBrokerageFee(rs.getString("b.brokeragefee"));
                results.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }
        return results;
    }
}

