package com.example.active.repository;

import com.example.active.model.MyUser;
import com.example.active.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUserID(Integer userID);
    List<Task> findAllByClubID(Integer clubID);
    Task findByID(Integer TaskID);
    Task findByIDAndUserID(Integer TaskID,Integer userID);

}
