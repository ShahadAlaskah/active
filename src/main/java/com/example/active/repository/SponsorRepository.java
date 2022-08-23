package com.example.active.repository;

import com.example.active.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
    Sponsor findByID(Integer sponsorID);
}
