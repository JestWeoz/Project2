package com.example.project2.repository.imp;

import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";


    void joinTable (Map<String, Object> params, List<String> rentTypeCode) {
        
    }
    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, List<String> rentTypeCode) {
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
