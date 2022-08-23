package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Membership;
import com.example.active.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final MembershipRepository membershipRepository;

    public List<Membership> getMembership() {
        return membershipRepository.findAll();
    }

    public void addMembership(Membership membership) {
        membershipRepository.save(membership);
    }

    public void updateMembership(Membership membership, Integer membershipID) {
        Membership oldMembership=membershipRepository.findByID(membershipID);
        if (oldMembership == null) {
            throw new ApiException("membershipID not found");
        }
        oldMembership.setName(membership.getName());
        oldMembership.setClubID(membership.getClubID());
        oldMembership.setUserID(membership.getUserID());
        membershipRepository.save(oldMembership);

    }

    public void deleteMembership(Integer membershipID) {
        Membership membership=membershipRepository.findByID(membershipID);
        if (membership == null) {
            throw new ApiException("membershipID not found");
        }
        membershipRepository.delete(membership);
    }

    public List<Membership> findAllByClubID(Integer clubID) {
        return membershipRepository.findAllByClubID(clubID);
    }
}
