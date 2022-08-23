package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.MyUser;
import com.example.active.model.Sponsor;
import com.example.active.service.MyUserService;
import com.example.active.service.SponsorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Sponsor")
public class SponsorController {
    private final SponsorService sponsorService;
    //--------------------------------------------------role admin member
    @GetMapping("/get")
    public ResponseEntity<List<Sponsor>> getSponsors(){
        List<Sponsor> sponsorList =sponsorService.getSponsors();
        return ResponseEntity.status(200).body(sponsorList);
    }
    //--------------------------------------------------role admin
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSponsor(@RequestBody @Valid Sponsor sponsor){
        sponsorService.addSponsor(sponsor);
        return ResponseEntity.status(200).body(new ApiResponse("sponsor added",200));
    }
    //--------------------------------------------------role admin
    @PutMapping("/update/{sponsorID}")
    public ResponseEntity<ApiResponse> updateSponsor(@RequestBody @Valid Sponsor sponsor,@PathVariable Integer sponsorID){
        sponsorService.updateSponsor(sponsor,sponsorID);
        return ResponseEntity.status(200).body(new ApiResponse("sponsor updated",200));
    }
    //--------------------------------------------------role admin
    @DeleteMapping("/delete/{sponsorID}")
    public ResponseEntity<ApiResponse> deleteSponsor(@PathVariable Integer sponsorID){
        sponsorService.deleteSponsor(sponsorID);
        return ResponseEntity.status(200).body(new ApiResponse("sponsor deleted",200));
    }
}
