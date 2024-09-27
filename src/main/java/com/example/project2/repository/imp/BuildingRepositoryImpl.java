package com.example.project2.repository.imp;

import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
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
        if(staffId == null || staffId.equals("")) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if (typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN buildingrenttype on b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype on renttype.id = buildingrenttype.renttypeid ");
        }
        String rentAreaTo = String.valueOf(params.get("areaTo"));
        String rentAreaFrom = String.valueOf(params.get("areaFrom"));
        if (rentAreaTo != null && !rentAreaTo.equals("") || rentAreaFrom != null && !rentAreaFrom.equals("")) {
            sql.append(" INNER JOIN rentarea on rentarea.buildingid = b.id ");
        }
    }
    public static void query(Map<String, Object> params, List<String> TypeCode, StringBuilder sql) {

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
