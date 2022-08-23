package com.example.active.repository;

import com.example.active.model.Collage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollageRepository extends JpaRepository<Collage, Integer> {
    Collage findByID(Integer collageID);
}
