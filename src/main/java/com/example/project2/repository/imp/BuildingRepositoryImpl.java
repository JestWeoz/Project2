package com.example.project2.repository.imp;

import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import com.example.project2.utils.NumberUtil;
import com.example.project2.utils.StringUtil;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";


    public static void joinTable (Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
        String staffId = String.valueOf(params.get("staffId"));
        if(StringUtil.checkString(staffId)) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if (typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN buildingrenttype on b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype on renttype.id = buildingrenttype.renttypeid ");
        }
        String rentAreaTo = String.valueOf(params.get("areaTo"));
        String rentAreaFrom = String.valueOf(params.get("areaFrom"));
        if (StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
            sql.append(" INNER JOIN rentarea on rentarea.buildingid = b.id ");
        }
    }
    public static void queryNormal(Map<String, Object> params, StringBuilder where) {
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            if(!entry.equals("staffId") && !entry.equals("typeCode") && !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice")) {
                String value = String.valueOf(entry.getValue());
                if (NumberUtil.isNumber(value)) {
                    where.append(" AND b.").append(entry.getKey()).append(" = ").append(value).append(" ");
                } else {
                    where.append(" AND b." + entry.getKey() + " LIKE '%" + value + "%' " );
                }
            }
        }
    }
    public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
        String staffId = String.valueOf(params.get("staffId"));
        if(StringUtil.checkString(staffId)) {
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
            where.append(" AND rentprice.value >=" + rentPriceFrom + " ");
        }
        if (StringUtil.checkString(rentPriceTo)) {
            where.append(" AND rentprice.value <=" + rentPriceTo + " ");
        }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
        joinTable(params, typeCode, sql);
        System.out.println(sql);
        List<BuildingEntity> results = new ArrayList<BuildingEntity>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                BuildingEntity dto = new BuildingEntity();
                dto.setName(rs.getString("name"));
                dto.setWard(rs.getString("ward"));
                dto.setStreet(rs.getString("street"));
                dto.setNumberOfBasement(rs.getInt("numberOfBasement"));
                dto.setDistrict(rs.getString("district.name"));
                dto.setFloorArea(rs.getLong("floorArea"));
                dto.setNameManager(rs.getString("managerName"));
                dto.setPhoneNumberManager(rs.getString("managerPhoneNumber"));
                dto.setRentPrice(rs.getLong(("rentPrice")));
                results.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }
        return results;
    }
}
