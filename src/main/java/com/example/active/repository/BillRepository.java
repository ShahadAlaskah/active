package com.example.active.repository;

import com.example.active.model.Bill;
import com.example.active.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill findBillByID(Integer ID);
    Bill findByIDAndClubID(Integer ID,Integer clubID);
    List<Bill> findAllByClubID(Integer clubID);
}
