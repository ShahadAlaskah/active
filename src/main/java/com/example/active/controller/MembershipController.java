package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.service.MembershipService;
import com.example.active.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Membership")
public class MembershipController {
    private final MembershipService membershipService;

    //--------------------------------------------------role non
//    @GetMapping("/get")
//    public ResponseEntity<List<Membership>> getMembership(){
//        List<Membership> membershipList =membershipService.getMembership();
//        return ResponseEntity.status(200).body(membershipList);
//    }
    //--------------------------------------------------role non لان المسول بيضيف مباشره
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addMembership(@RequestBody @Valid Membership membership){
//        membershipService.addMembership(membership);
//        return ResponseEntity.status(200).body(new ApiResponse("membership added",200));
//    }
    //--------------------------------------------------role non
//    @PutMapping("/update/{userID}")
//    public ResponseEntity<ApiResponse> updateMembership(@RequestBody @Valid Membership membership,@PathVariable Integer membershipID){
//        membershipService.updateMembership(membership,membershipID);
//        return ResponseEntity.status(200).body(new ApiResponse("membership updated",200));
//    }
    //--------------------------------------------------role non
//    @DeleteMapping("/delete/{userID}")
//    public ResponseEntity<ApiResponse> deleteMembership(@PathVariable Integer membershipID){
//        membershipService.deleteMembership(membershipID);
//        return ResponseEntity.status(200).body(new ApiResponse("membership deleted",200));
//    }
    //-------------------------chan-------------------------role user admin member
    @GetMapping("/findAllByClubID/{clubID}")
    public ResponseEntity<List<Membership>> findAllByClubID(@PathVariable Integer clubID){
        List<Membership> membershipList = membershipService.findAllByClubID(clubID);
        return ResponseEntity.status(200).body(membershipList);
    }

}
