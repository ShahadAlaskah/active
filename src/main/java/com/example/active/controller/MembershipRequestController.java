package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.dto.FindAllByClubIDAndStatus;
import com.example.active.model.MembershipRequest;
import com.example.active.model.MyUser;
import com.example.active.service.MembershipRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/MembershipRequest")
public class MembershipRequestController {
    private final MembershipRequestService membershipRequestService;
    //--------------------------------------------------role non
    @GetMapping("/get")
    public ResponseEntity<List<MembershipRequest>> getMembershipRequest(){
        List<MembershipRequest> membershipRequestList =membershipRequestService.getMembershipRequest();
        return ResponseEntity.status(200).body(membershipRequestList);
    }
    //--------------------------------------------------role user
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMembershipRequest(@RequestBody @Valid MembershipRequest membershipRequest){
        membershipRequestService.addMembershipRequest(membershipRequest);
        return ResponseEntity.status(200).body(new ApiResponse("user added",200));
    }
    //--------------------------------------------------role user
    @PutMapping("/update/{membershipRequestID}")
    public ResponseEntity<ApiResponse> updateMembershipRequest(@RequestBody @Valid MembershipRequest membershipRequest,@PathVariable Integer membershipRequestID){
        membershipRequestService.updateMembershipRequest(membershipRequest,membershipRequestID);
        return ResponseEntity.status(200).body(new ApiResponse("user updated",200));
    }
    //--------------------------------------------------role user
    @DeleteMapping("/delete/{membershipRequestID}")
    public ResponseEntity<ApiResponse> deleteMembershipRequest(@PathVariable Integer membershipRequestID){
        membershipRequestService.deleteMembershipRequest(membershipRequestID);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted",200));
    }
    //--------------------------------------------------role member(HR)
    @GetMapping("/findAllByClubIDAndStatus")
    public ResponseEntity<List<MembershipRequest>> findAllByClubIDAndStatus(@RequestBody @Valid FindAllByClubIDAndStatus findAllByClubIDAndStatus, @AuthenticationPrincipal MyUser myUser){
        List<MembershipRequest> membershipRequestList =membershipRequestService.findAllByClubIDAndStatus(findAllByClubIDAndStatus,myUser);
        return ResponseEntity.status(200).body(membershipRequestList);
    }
    //--------------------------------------------------role member(HR)
    @GetMapping("/findAllByClubIDAndStatusInProgress/{clubID}")
    public ResponseEntity<List<MembershipRequest>> findAllByClubIDAndStatusInProgress(@PathVariable Integer clubID, @AuthenticationPrincipal MyUser myUser){
        List<MembershipRequest> membershipRequestList =membershipRequestService.findAllByClubIDAndStatusInProgress(clubID,myUser);
        return ResponseEntity.status(200).body(membershipRequestList);
    }
    //--------------------------------------------------role member(HR)
    @PutMapping("/RejectMembershipRequest/{membershipRequestID}")
    public ResponseEntity<ApiResponse> rejectMembershipRequest(@PathVariable Integer membershipRequestID, @AuthenticationPrincipal MyUser myUser){
        membershipRequestService.rejectMembershipRequest(membershipRequestID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("rejectMembershipRequest",200));
    }
    //--------------------------------------------------role member(HR)
    @PutMapping("/acceptMembershipRequest/{membershipRequestID}")
    public ResponseEntity<ApiResponse> acceptMembershipRequest(@PathVariable Integer membershipRequestID, @AuthenticationPrincipal MyUser myUser){
        membershipRequestService.acceptMembershipRequest(membershipRequestID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("acceptMembershipRequest",200));
    }
}
