package com.example.project2.repository.imp;

import com.example.project2.repository.RentAreaRepository;
import com.example.project2.repository.entity.RentAreaEntity;
import com.example.project2.utils.ConnectionJDBCUtil;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

    @Override
    public List<RentAreaEntity> getValueById(int id) {
        String sql = "select * from rentarea where rentarea.buildingid = " + id + "; ";
        List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                RentAreaEntity rentArea = new RentAreaEntity();
                rentArea.setValue(rs.getString("value"));
                rentAreas.add(rentArea);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentAreas;
    }
}
