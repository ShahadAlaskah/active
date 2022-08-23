package com.example.active.service;

import com.example.active.dto.UpdateBillStatus;
import com.example.active.exception.ApiException;
import com.example.active.model.*;
import com.example.active.repository.BillRepository;
import com.example.active.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final MembershipRepository membershipRepository;


    public List<Bill> getBill() {
        return billRepository.findAll();
    }

    public void addBill(Bill bill, MyUser myUser ) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("FINANCE"))) {
            throw new ApiException("un");
        }
        bill.setStatus("IN PROGRESS");
        billRepository.save(bill);
    }

    public void updateBill(Bill bill, Integer billID,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("FINANCE"))) {
            throw new ApiException("un");
        }
        Bill oldBill=billRepository.findByIDAndClubID(billID, membership.getClubID());
        if (oldBill == null) {
            throw new ApiException("billID not found");
        }
        oldBill.setName(bill.getName());
        oldBill.setAmount(bill.getAmount());
        oldBill.setSeller(bill.getSeller());
        oldBill.setPicURL(bill.getPicURL());
        billRepository.save(oldBill);

    }

    public void deleteBill(Integer billID,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("FINANCE"))) {
            throw new ApiException("un");
        }
        Bill bill=billRepository.findByIDAndClubID(billID, membership.getClubID());
        if (bill == null) {
            throw new ApiException("billID not found");
        }
        billRepository.delete(bill);
    }

    public void updateBillStatus(UpdateBillStatus updateBillStatus,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("LEADER"))) {
            throw new ApiException("un");
        }
        Bill bill=billRepository.findByIDAndClubID(updateBillStatus.getBillID(), membership.getClubID());
        if (bill == null) {
            throw new ApiException("billID not found");
        }
        if (!(updateBillStatus.getStatus().equals("ACCEPT"))  && !(updateBillStatus.getStatus().equals("REJECT"))) {
            throw new ApiException("billStatus not acceptable");
        }
        bill.setStatus(updateBillStatus.getStatus());
        billRepository.save(bill);

    }

    public Bill findBillByID(Integer billID,MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("FINANCE"))||!(membership.getName().equals("Leader"))) {
            throw new ApiException("un");
        }
        return billRepository.findBillByID(billID);
    }

    public List<Bill> findAllByClubID(MyUser myUser) {
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if (!(membership.getName().equals("FINANCE"))&&!(membership.getName().equals("Leader"))) {
            throw new ApiException("un");
        }
        return billRepository.findAllByClubID(myUser.getClubID());
    }
}
