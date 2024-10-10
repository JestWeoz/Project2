package com.example.project2.repository.imp;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class JDBCBuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
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
        try {
            Field[] fields = buildingSearchBuilder.getClass().getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.equals("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else {
                            where.append(" AND b." + fieldName + " like '%" + value + "%'");

                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentbuilding.staffid = ").append(staffId).append(" ");
        }
        Long rentAreaTo = buildingSearchBuilder.getRentPriceTo();
        Long rentAreaFrom = buildingSearchBuilder.getRentPriceFrom();
        if (rentAreaFrom != null) {
            where.append(" AND rentarea.value >=" + rentAreaFrom + " ");
        }
        if (rentAreaTo != null) {
            where.append(" AND rentarea.value <=" + rentAreaTo + " ");
        }
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if (rentPriceFrom != null)  {
            where.append(" AND b.rentprice >=" + rentPriceFrom + " ");
        }
        if (rentPriceTo != null) {
            where.append(" AND b.rentprice <=" + rentPriceTo + " ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            List<String> code = new ArrayList<>();
            for (String type : typeCode) {
                code.add("'" + type + "'");
            }
            where.append(" AND renttype.code in (" + String.join(",", code) + ")");
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(" SELECT distinct b.* FROM building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" ORDER BY b.id ");
        sql.append(where);
        List<BuildingEntity> results = new ArrayList<BuildingEntity>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                BuildingEntity dto = new BuildingEntity();
                dto.setName(rs.getString("b.name"));
                dto.setId(rs.getInt("b.id"));
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

