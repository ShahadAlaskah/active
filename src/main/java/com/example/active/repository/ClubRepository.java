package com.example.active.repository;

import com.example.active.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {

    List<Club> findAllByCollageID(Integer collageID);
    Club findClubByID(Integer ID);
}
