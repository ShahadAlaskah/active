package com.example.active.repository;

import com.example.active.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    Ads findByClubIDAndID(Integer clubID,Integer adsID);
    Ads findByID(Integer adsID);
}
