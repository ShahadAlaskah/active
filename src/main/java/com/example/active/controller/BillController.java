package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.dto.UpdateBillStatus;
import com.example.active.model.Bill;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.repository.MembershipRepository;
import com.example.active.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Bill")
public class BillController {
    private final BillService billService;
    private final MembershipRepository membershipRepository;

    //--------------------------------------------------role admin
    @GetMapping("/get")
    public ResponseEntity<List<Bill>> getBill(){
        List<Bill> billList =billService.getBill();
        return ResponseEntity.status(200).body(billList);
    }
    //--------------------------------------------------role member(FINANCE)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBill(@RequestBody @Valid Bill bill,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("FINANCE"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        billService.addBill(bill,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill added",200));
    }
    //--------------------------------------------------role member(FINANCE)
    @PutMapping("/update/{billID}")
    public ResponseEntity<ApiResponse> updateBill(@RequestBody @Valid Bill bill,@PathVariable Integer billID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("FINANCE"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        billService.updateBill(bill,billID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill updated",200));
    }
    //--------------------------------------------------role member(FINANCE)
    @DeleteMapping("/delete/{billID}")
    public ResponseEntity<ApiResponse> deleteBill(@PathVariable Integer billID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("FINANCE"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        billService.deleteBill(billID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill deleted",200));
    }
    //--------------------------------------------------role member(LEADER)
    @PutMapping("/updateBillStatus")
    public ResponseEntity<ApiResponse> updateBillStatus(@RequestBody @Valid UpdateBillStatus updateBillStatus,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        billService.updateBillStatus(updateBillStatus,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill Status updated",200));
    }
    //--------------------------------------------------role member(LEADER,FINANCE) admin
    @GetMapping("/findBillByID/{billID}")
    public ResponseEntity<?> findBillByID(@PathVariable Integer billID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("FINANCE"))&&!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        Bill bill = billService.findBillByID(billID,myUser);
        return ResponseEntity.status(200).body(bill);
    }
    //--------------------------------------------------role member(LEADER,FINANCE)
    @GetMapping("/findAllByClubID")
    public ResponseEntity<?> findAllByClubID(@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("FINANCE"))&&!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        List<Bill> billList = billService.findAllByClubID(myUser);
        return ResponseEntity.status(200).body(billList);
    }

}
