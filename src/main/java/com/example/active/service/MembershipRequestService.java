package com.example.active.service;

import com.example.active.dto.FindAllByClubIDAndStatus;
import com.example.active.exception.ApiException;
import com.example.active.model.Membership;
import com.example.active.model.MembershipRequest;
import com.example.active.model.MyUser;
import com.example.active.repository.MembershipRepository;
import com.example.active.repository.MembershipRequestRepository;
import com.example.active.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipRequestService {
    private final MembershipRequestRepository membershipRequestRepository;
    private final MyUserRepository myUserRepository;

    private final MembershipRepository membershipRepository;


    public List<MembershipRequest> getMembershipRequest() {

        return membershipRequestRepository.findAll();
    }

    public void addMembershipRequest(MembershipRequest membershipRequest) {
        membershipRequest.setStatus("IN PROGRESS");
        membershipRequestRepository.save(membershipRequest);
    }

    public void updateMembershipRequest(MembershipRequest membershipRequest, Integer membershipRequestID) {
        MembershipRequest oldMembershipRequest=membershipRequestRepository.findByID(membershipRequestID);
        if (oldMembershipRequest == null) {
            throw new ApiException("membershipRequestID not found");
        }
        oldMembershipRequest.setStatus(membershipRequest.getStatus());
        oldMembershipRequest.setName(membershipRequest.getName());
        oldMembershipRequest.setUserID(membershipRequest.getUserID());
        oldMembershipRequest.setClubID(membershipRequest.getClubID());
        membershipRequestRepository.save(oldMembershipRequest);

    }

    public void deleteMembershipRequest(Integer membershipRequestID) {
        MembershipRequest membershipRequest=membershipRequestRepository.findByID(membershipRequestID);
        if (membershipRequest == null) {
            throw new ApiException("membershipRequestID not found");
        }
        membershipRequestRepository.delete(membershipRequest);
    }


    public List<MembershipRequest> findAllByClubIDAndStatus(FindAllByClubIDAndStatus findAllByClubIDAndStatus,MyUser myUser) {

        return membershipRequestRepository.findAllByClubIDAndStatus(findAllByClubIDAndStatus.getClubID(),findAllByClubIDAndStatus.getStatus());
    }

    public List<MembershipRequest> findAllByClubIDAndStatusInProgress(Integer clubID,MyUser myUser) {

        return membershipRequestRepository.findAllByClubIDAndStatus(clubID,"IN PROGRESS");
    }

    public void rejectMembershipRequest(Integer membershipRequestID,MyUser myUser) {

        MembershipRequest membershipRequest=membershipRequestRepository.findByID(membershipRequestID);
        membershipRequest.setStatus("REJECT");
        membershipRequestRepository.save(membershipRequest);
    }

    public void acceptMembershipRequest(Integer membershipRequestID,MyUser myUser) {

        MembershipRequest membershipRequest = membershipRequestRepository.findByID(membershipRequestID);
        membershipRequest.setStatus("ACCEPT");
        membershipRequestRepository.save(membershipRequest);
        //--------------------------------------------------
        MyUser user = myUserRepository.findByID(membershipRequest.getUserID());
        user.setRole("MEMBER");
        myUserRepository.save(user);
        //--------------------------------------------------
        Membership membership1=new Membership(null,membershipRequest.getUserID(),membershipRequest.getClubID(),membershipRequest.getName());
        membershipRepository.save(membership1);

    }
}
