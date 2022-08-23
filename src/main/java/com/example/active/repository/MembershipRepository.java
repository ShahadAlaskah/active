package com.example.active.repository;

import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    List<Membership> findAllByClubID(Integer clubID);
    Membership findByID(Integer membershipID);
    Membership findByUserID(Integer userID);
}
