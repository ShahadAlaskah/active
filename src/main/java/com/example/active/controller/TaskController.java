package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.Membership;
import com.example.active.model.MyUser;
import com.example.active.model.Task;
import com.example.active.repository.MembershipRepository;
import com.example.active.service.MyUserService;
import com.example.active.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Task")
public class TaskController {
    private final TaskService taskService;
    private final MembershipRepository membershipRepository;

    //--------------------------------------------------role non
//    @GetMapping("/get")
//    public ResponseEntity<List<Task>> getTask(){
//        List<Task> taskList =taskService.getTask();
//        return ResponseEntity.status(200).body(taskList);
//    }
    //--------------------------------------------------role member(Leader)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTask(@RequestBody @Valid Task task,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        taskService.addTask(task);
        return ResponseEntity.status(200).body(new ApiResponse("task added",200));
    }
    //--------------------------------------------------role member(Leader)
    @PutMapping("/update/{taskID}")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody @Valid Task task,@PathVariable Integer taskID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        taskService.updateTask(task,taskID);
        return ResponseEntity.status(200).body(new ApiResponse("task updated",200));
    }
    //--------------------------------------------------role member(Leader)
    @DeleteMapping("/delete/{taskID}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer taskID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        taskService.deleteTask(taskID);
        return ResponseEntity.status(200).body(new ApiResponse("task deleted",200));
    }
    //--------------------------------------------------role member ممكن استبعد الليدير
    @GetMapping("/findAllByUserID")
    public ResponseEntity<List<Task>> findAllByUserID(@AuthenticationPrincipal MyUser myUser){
        List<Task> taskList =taskService.findAllByUserID(myUser.getID());
        return ResponseEntity.status(200).body(taskList);
    }
    //--------------------------------------------------role member(Leader)
    @GetMapping("/findAllByClubID/{clubID}")
    public ResponseEntity<?> findAllByClubID(@PathVariable Integer clubID,@AuthenticationPrincipal MyUser myUser){
        Membership membership=membershipRepository.findByUserID(myUser.getID());
        if(membership!=null) {
            if (!(membership.getName().equals("LEADER"))) {
                return ResponseEntity.status(403).body(new ApiResponse("Forbidden", 403));
            }
        }
        List<Task> taskList =taskService.findAllByClubID(clubID);
        return ResponseEntity.status(200).body(taskList);
    }

    //--------------------------------------------------role member ممكن استبعد الليدير
    @PutMapping("/Reject/{taskID}")
    public ResponseEntity<ApiResponse> reject(@PathVariable Integer taskID, @AuthenticationPrincipal MyUser myUser){

        taskService.reject(taskID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("reject task",200));
    }
    //--------------------------------------------------role member
    @PutMapping("/Completed/{taskID}")
    public ResponseEntity<ApiResponse> completed(@PathVariable Integer taskID, @AuthenticationPrincipal MyUser myUser){

        taskService.completed(taskID,myUser);
        return ResponseEntity.status(200).body(new ApiResponse("complete task",200));
    }


}
