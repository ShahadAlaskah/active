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
        bill.setClubID(myUser.getClubID());
        bill.setStatus("IN PROGRESS");
        billRepository.save(bill);
    }

    public void updateBill(Bill bill, Integer billID,MyUser myUser) {

        Bill oldBill=billRepository.findByIDAndClubID(billID, myUser.getClubID());
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

        Bill bill=billRepository.findByIDAndClubID(billID, myUser.getClubID());
        if (bill == null) {
            throw new ApiException("billID not found");
        }
        billRepository.delete(bill);
    }

    public void updateBillStatus(UpdateBillStatus updateBillStatus,MyUser myUser) {

        Bill bill=billRepository.findByIDAndClubID(updateBillStatus.getBillID(), myUser.getClubID());
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
        return billRepository.findByIDAndClubID(billID,myUser.getClubID());
    }

    public List<Bill> findAllByClubID(MyUser myUser) {
        return billRepository.findAllByClubID(myUser.getClubID());
    }
}
