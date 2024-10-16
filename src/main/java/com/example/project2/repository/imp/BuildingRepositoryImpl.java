package com.example.project2.repository.imp;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void query(StringBuilder where, BuildingSearchBuilder buildingSearchBuilders) {
        try {
            Field[] fields = buildingSearchBuilders.getClass().getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffid") && !fieldName.equals("typecode") && !fieldName.startsWith("rentprice")  && !fieldName.startsWith("area")) {
                    Object value = item.get(buildingSearchBuilders);
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


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        String sql  = "SELECT * FROM building b";
        StringBuilder where = new StringBuilder(sql + " WHERE 1=1 ");
        query(where, buildingSearchBuilder);
        Query query = entityManager.createNativeQuery(where.toString(), BuildingEntity.class);
        return query.getResultList();
    }
}
