package com.example.project2.repository.imp;

import com.example.project2.builder.BuildingSearchBuilder;
import com.example.project2.repository.BuildingRepository;
import com.example.project2.repository.entity.BuildingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        String sql  = "FROM BuildingEntity b";
        Query query = entityManager.createQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }
}
