package com.example.project2.repository.imp;

import com.example.project2.repository.DistrictRepository;
import com.example.project2.repository.entity.DistrictEntity;

import java.sql.*;
import java.util.List;

public class DistrictRepositoryImpl implements DistrictRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";
    @Override
    public DistrictEntity findNameById(int id) {
        StringBuilder sql = new StringBuilder(" select * from district where id = " + id + ";");
        DistrictEntity d = new DistrictEntity();
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                d.setDistrictName(rs.getString("name"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return d;
    }
}
