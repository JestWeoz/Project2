package com.example.project2.repository.imp;

import com.example.project2.repository.DistrictRepository;
import com.example.project2.repository.entity.DistrictEntity;
import com.example.project2.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    @Override
    public DistrictEntity findNameById(int id) {
        StringBuilder sql = new StringBuilder(" select * from district where id = " + id + ";");
        DistrictEntity d = new DistrictEntity();
        try(Connection conn = ConnectionJDBCUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return d;
    }
}
