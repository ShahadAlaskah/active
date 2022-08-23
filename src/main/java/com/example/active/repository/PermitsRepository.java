package com.example.active.repository;

import com.example.active.model.Permits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitsRepository extends JpaRepository<Permits, Integer> {
    Permits findByID(Integer permitsID );
}
