package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Club;
import com.example.active.model.MyUser;
import com.example.active.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Club")
public class ClubController {
    private final ClubService clubService;
    //--------------------------------------------------role user member admin
    @GetMapping("/get")
    public ResponseEntity<List<Club>> getClub(){
        List<Club> clubList =clubService.getClub();
        return ResponseEntity.status(200).body(clubList);
    }
    //--------------------------------------------------role admin
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addClub(@RequestBody @Valid Club club){
        clubService.addClub(club);
        return ResponseEntity.status(200).body(new ApiResponse("club added",200));
    }
    //--------------------------------------------------role admin
    @PutMapping("/update/{clubID}")
    public ResponseEntity<ApiResponse> updateClub(@RequestBody @Valid Club club,@PathVariable Integer clubID){
        clubService.updateClub(club,clubID);
        return ResponseEntity.status(200).body(new ApiResponse("club updated",200));
    }
    //--------------------------------------------------role admin
    @DeleteMapping("/delete/{clubID}")
    public ResponseEntity<ApiResponse> deleteClub(@PathVariable Integer clubID){
        clubService.deleteClub(clubID);
        return ResponseEntity.status(200).body(new ApiResponse("club deleted",200));
    }
    //--------------------------------------------------role user member admin
    @GetMapping("/findAllByCollageID/{collageID}")
    public ResponseEntity<List<Club>> findAllByCollageID(@PathVariable Integer collageID){
        List<Club> clubList =clubService.findAllByCollageID(collageID);
        return ResponseEntity.status(200).body(clubList);
    }
    //--------------------------------------------------role user member admin
    @GetMapping("/findClubByID/{clubID}")
    public ResponseEntity<Club> findClubByID(@PathVariable Integer clubID){
        Club club =clubService.findClubByID(clubID);
        return ResponseEntity.status(200).body(club);
    }

}
