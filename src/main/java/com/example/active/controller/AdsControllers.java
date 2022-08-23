package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Ads;
import com.example.active.model.Bill;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.service.AdsService;
import com.example.active.service.BillService;
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
    //--------------------------------------------------role user member admin
    @GetMapping("/get")
    public ResponseEntity<List<Ads>> getAds(){
        List<Ads> adsList =adsService.getAds();
        return ResponseEntity.status(200).body(adsList);
    }
    //--------------------------------------------------role member(MARKETING)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAds(@RequestBody @Valid Ads ads, @AuthenticationPrincipal MyUser myUser){
        adsService.addAds(ads,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill added",200));
    }
    //--------------------------------------------------role member(MARKETING)
    @PutMapping("/update/{adsID}")
    public ResponseEntity<ApiResponse> updateAds(@RequestBody @Valid Ads ads,@PathVariable Integer adsID,@AuthenticationPrincipal MyUser myUser){
        adsService.updateAds(ads,adsID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill updated",200));
    }
    //--------------------------------------------------role member(MARKETING)
    @DeleteMapping("/delete/{adsID}")
    public ResponseEntity<ApiResponse> deleteAds(@PathVariable Integer adsID,@AuthenticationPrincipal MyUser myUser){
        adsService.deleteAds(adsID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("bill deleted",200));
    }

}
