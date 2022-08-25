package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Collage;
import com.example.active.model.Membership;
import com.example.active.service.CollageService;
import com.example.active.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Collage")
public class CollageController {
    private final CollageService collageService;
    //--------------------------------------------------role user member admin
    @GetMapping("/get")
    public ResponseEntity<List<Collage>> getCollage(){
        List<Collage> membershipList =collageService.getCollage();
        return ResponseEntity.status(200).body(membershipList);
    }
    //--------------------------------------------------role admin
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCollage(@RequestBody @Valid Collage collage){
        collageService.addCollage(collage);
        return ResponseEntity.status(200).body(new ApiResponse("Collage added",200));
    }
    //--------------------------------------------------role admin
    @PutMapping("/update/{collageID}")
    public ResponseEntity<ApiResponse> updateCollage(@RequestBody @Valid Collage collage,@PathVariable Integer collageID){
        collageService.updateCollage(collage,collageID);
        return ResponseEntity.status(200).body(new ApiResponse("Collage updated",200));
    }
    //--------------------------------------------------role admin
    @DeleteMapping("/delete/{collageID}")
    public ResponseEntity<ApiResponse> deleteCollage(@PathVariable Integer collageID){
        collageService.deleteCollage(collageID);
        return ResponseEntity.status(200).body(new ApiResponse("Collage deleted",200));
    }
}
