package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Certificate;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.repository.MembershipRepository;
import com.example.active.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Certificate")
public class CertificateController {
    private final CertificateService certificateService;
    private final MembershipRepository membershipRepository;

    //--------------------------------------------------role non
//    @GetMapping("/get")
//    public ResponseEntity<List<Certificate>> getCertificate(){
//        List<Certificate> certificateList =certificateService.getCertificate();
//        return ResponseEntity.status(200).body(certificateList);
//    }
    //--------------------------------------------------role member(HR)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCertificate(@RequestBody @Valid Certificate certificate,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("HR"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        certificateService.addCertificate(certificate,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("certificate added",200));
    }
    //--------------------------------------------------role member(HR)
    @PutMapping("/update/{certificateID}")
    public ResponseEntity<ApiResponse> updateCertificate(@RequestBody @Valid Certificate certificate,@PathVariable Integer certificateID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("HR"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        certificateService.updateCertificate(certificate,certificateID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("certificate updated",200));
    }
    //--------------------------------------------------role member(HR)
    @DeleteMapping("/delete/{certificateID}")
    public ResponseEntity<ApiResponse> deleteCertificate(@PathVariable Integer certificateID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("HR"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        certificateService.deleteCertificate(certificateID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("certificate deleted",200));
    }
    //--------------------------------------------------role user member(HR)
    @GetMapping("/findAllByUserID")
    public ResponseEntity<?> findAllByUserID(@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("HR"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        List<Certificate> certificateList =certificateService.findAllByUserID(myUser.getID());
        return ResponseEntity.status(200).body(certificateList);
    }


}
