package com.example.active.controller;

import com.example.active.dto.ApiResponse;
import com.example.active.model.MyUser;
import com.example.active.model.Task;
import com.example.active.service.MyUserService;
import com.example.active.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Task")
public class TaskController {
    private final TaskService taskService;
    //--------------------------------------------------role non
    @GetMapping("/get")
    public ResponseEntity<List<Task>> getTask(){
        List<Task> taskList =taskService.getTask();
        return ResponseEntity.status(200).body(taskList);
    }
    //--------------------------------------------------role member(Leader)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTask(@RequestBody @Valid Task task){
        taskService.addTask(task);
        return ResponseEntity.status(200).body(new ApiResponse("user added",200));
    }
    //--------------------------------------------------role member(Leader)
    @PutMapping("/update/{taskID}")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody @Valid Task task,@PathVariable Integer taskID){
        taskService.updateTask(task,taskID);
        return ResponseEntity.status(200).body(new ApiResponse("user updated",200));
    }
    //--------------------------------------------------role member(Leader)
    @DeleteMapping("/delete/{taskID}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer taskID){
        taskService.deleteTask(taskID);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted",200));
    }
    //--------------------------------------------------role member
    @GetMapping("/findAllByUserID/{userID}")
    public ResponseEntity<List<Task>> findAllByUserID(@PathVariable Integer userID){
        List<Task> taskList =taskService.findAllByUserID(userID);
        return ResponseEntity.status(200).body(taskList);
    }
    //--------------------------------------------------role member(Leader)
    @GetMapping("/findAllByClubID/{clubID}")
    public ResponseEntity<List<Task>> findAllByClubID(@PathVariable Integer clubID){
        List<Task> taskList =taskService.findAllByClubID(clubID);
        return ResponseEntity.status(200).body(taskList);
    }


}
