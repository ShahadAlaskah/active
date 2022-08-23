package com.example.active.repository;

import com.example.active.model.MembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRequestRepository extends JpaRepository<MembershipRequest, Integer> {
    List<MembershipRequest> findAllByClubIDAndStatus(Integer clubID,String status);
    MembershipRequest findByID(Integer membershipRequestID);
}
