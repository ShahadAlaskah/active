package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.MyUser;
import com.example.active.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/MyUser")
public class MyUserController {
    private final MyUserService myUserService;
    //--------------------------------------------------role non
    @GetMapping("/get")
    public ResponseEntity<List<MyUser>> getUsers(){
        List<MyUser> usersList =myUserService.getUsers();
        return ResponseEntity.status(200).body(usersList);
    }
    //--------------------------------------------------role user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("user added",200));
    }
    //--------------------------------------------------role member user
    @PutMapping("/update/{userID}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody @Valid MyUser myUser,@PathVariable Integer userID){
        myUserService.updateUser(myUser,userID);
        return ResponseEntity.status(200).body(new ApiResponse("user updated",200));
    }
    //--------------------------------------------------role member user
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userID){
        myUserService.deleteUser(userID);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted",200));
    }
    //--------------------------------------------------role member user
    @GetMapping("/getUserByID/{userID}")
    public ResponseEntity<MyUser> getUserByID(@PathVariable Integer userID){
        MyUser myUser =myUserService.getUserByID(userID);
        return ResponseEntity.status(200).body(myUser);
    }

}
