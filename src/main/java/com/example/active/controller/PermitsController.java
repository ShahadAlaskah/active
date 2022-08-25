package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.MyUser;
import com.example.active.model.Permits;
import com.example.active.service.MyUserService;
import com.example.active.service.PermitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Permits")
public class PermitsController {
    private final PermitsService permitsService;
    //--------------------------------------------------role admin
    @GetMapping("/get")
    public ResponseEntity<List<Permits>> getPermits(){
        List<Permits> usersList = permitsService.getPermits();
        return ResponseEntity.status(200).body(usersList);
    }
    //--------------------------------------------------role member
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPermits(@RequestBody @Valid Permits permits){
        permitsService.addPermits(permits);
        return ResponseEntity.status(200).body(new ApiResponse("permits added",200));
    }
    //--------------------------------------------------role member
    @PutMapping("/update/{permitsID}")
    public ResponseEntity<ApiResponse> updatePermits(@RequestBody @Valid Permits permits,@PathVariable Integer permitsID){
        permitsService.updatePermits(permits,permitsID);
        return ResponseEntity.status(200).body(new ApiResponse("permits updated",200));
    }
    //--------------------------------------------------role member
    @DeleteMapping("/delete/{permitsID}")
    public ResponseEntity<ApiResponse> deletePermits(@PathVariable Integer permitsID){
        permitsService.deletePermits(permitsID);
        return ResponseEntity.status(200).body(new ApiResponse("permits deleted",200));
    }
    //--------------------------------------------------role member(لجنه التصاريح)
//    @PutMapping("/update/{permitsID}")
//    public ResponseEntity<ApiResponse> updateStatusPermits(@RequestBody @Valid Permits permits,@PathVariable Integer permitsID){
//        permitsService.updatePermits(permits,permitsID);
//        return ResponseEntity.status(200).body(new ApiResponse("user updated",200));
//    }
}
