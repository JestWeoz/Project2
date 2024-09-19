package com.example.project2.repository.imp;

import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";


    void joinTable (List<Long> rentTypeId, StringBuilder sql) {
        if (rentTypeId != null && !rentTypeId.isEmpty()) {
            sql.append(" join buildingrenttype brt on brt.buildingid = building.id");
            sql.append(" join rentType rt on rt.id = brt.rentTypeID");
        }
    }
    @Override
    public List<BuildingEntity> findAll(String name, Long districtID, String street, String ward, Long numberOfBasement, Long floorArea, Long area1, Long area2, Long rent1, Long rent2, List<Long> rentTypeId) {

        StringBuilder sql = new StringBuilder("select building.*, district.name from building join district on district.id = building.districtID");
        if(rentTypeId != null && !rentTypeId.isEmpty()) {
            joinTable(rentTypeId, sql);
            sql.append (" where 1=1 and renttypeid in (");
            for (int i = 1; i <= rentTypeId.size(); i++) {
                if (i == rentTypeId.size()) {
                    sql.append (rentTypeId.get(i - 1) + ")");
                }
                else {
                    sql.append (rentTypeId.get(i - 1) + ",");
                }
            }
            sql.append (" group by building.id having COUNT(DISTINCT renttypeid) = " + rentTypeId.size());
        } else {
            sql.append(" where 1=1");
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" and building.name like '%" + name + "%'");
        }
        if (districtID != null) {
            sql.append(" and districtId = " + districtID);
        }
        if (street != null && !street.isEmpty()) {
            sql.append(" and street like '%" + street + "%'");
        }
        if (ward != null && !ward.isEmpty()) {
            sql.append(" and ward like '%" + ward + "%'");
        }
        if (numberOfBasement != null) {
            sql.append(" and numberOfBasement = " + numberOfBasement);
        }
        if (floorArea != null) {
            sql.append(" and floorArea = " + floorArea);
        }
        if (area1 != null) {
            sql.append(" and floorArea >= " + area1);
        }
        if (area2 != null) {
            sql.append(" and floorArea <= " + area2);
        }
        if (rent1 != null) {
            sql.append(" and rentprice >= " + rent1);
        }
        if (rent2 != null) {
            sql.append(" and rentprice <= " + rent2);
        }
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
