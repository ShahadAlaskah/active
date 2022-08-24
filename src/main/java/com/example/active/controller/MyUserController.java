package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.MyUser;
import com.example.active.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/MyUser")
public class MyUserController {
    private final MyUserService myUserService;
    //--------------------------------------------------role non
//    @GetMapping("/get")
//    public ResponseEntity<List<MyUser>> getUsers(){
//        List<MyUser> usersList =myUserService.getUsers();
//        return ResponseEntity.status(200).body(usersList);
//    }
    //--------------------------------------------------role user
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("user added",200));
    }
    //--------------------------------------------------role member user
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody @Valid MyUser newUser, @AuthenticationPrincipal MyUser myUser){
        myUserService.updateUser(newUser,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("user updated",200));
    }
    //--------------------------------------------------role member user
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteUser( @AuthenticationPrincipal MyUser myUser){
        myUserService.deleteUser(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted",200));
    }
    //--------------------------------------------------role member user
    @GetMapping("/getUserByID")
    public ResponseEntity<MyUser> getUserByID( @AuthenticationPrincipal MyUser myUser){
        MyUser user =myUserService.getUserByID(myUser);
        return ResponseEntity.status(200).body(user);
    }

}
