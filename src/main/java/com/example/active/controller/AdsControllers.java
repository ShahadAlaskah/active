package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.exception.ApiException;
import com.example.active.model.Ads;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.repository.MembershipRepository;
import com.example.active.service.AdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Ads")
public class AdsControllers {
    private final AdsService adsService;
    private final MembershipRepository membershipRepository;
    //--------------------------------------------------role user member admin
    @GetMapping("/get")
    public ResponseEntity<List<Ads>> getAds(){
        List<Ads> adsList =adsService.getAds();
        return ResponseEntity.status(200).body(adsList);
    }
    //--------------------------------------------------role member(MARKETING)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAds(@RequestBody @Valid Ads ads, @AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("MARKETING"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        adsService.addAds(ads,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("ads added",200));
    }
    //--------------------------------------------------role member(MARKETING)
    @PutMapping("/update/{adsID}")
    public ResponseEntity<ApiResponse> updateAds(@RequestBody @Valid Ads ads,@PathVariable Integer adsID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("MARKETING"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        adsService.updateAds(ads,adsID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("ads updated",200));
    }
    //--------------------------------------------------role member(MARKETING)
    @DeleteMapping("/delete/{adsID}")
    public ResponseEntity<ApiResponse> deleteAds(@PathVariable Integer adsID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("MARKETING"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        adsService.deleteAds(adsID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("ads deleted",200));
    }

}
