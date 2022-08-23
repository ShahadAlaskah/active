package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.Task;
import com.example.active.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(Task task, Integer taskID) {
        Task oldTask=taskRepository.findByID(taskID);
        if (oldTask == null) {
            throw new ApiException("taskID not found");
        }
        oldTask.setStatus(task.getStatus());
        oldTask.setDescription(task.getDescription());
        oldTask.setUserID(task.getUserID());
        oldTask.setTitle(task.getTitle());
        oldTask.setClubID(task.getClubID());
        taskRepository.save(oldTask);

    }

    public void deleteTask(Integer taskID) {
        Task task=taskRepository.findByID(taskID);
        if (task == null) {
            throw new ApiException("taskID not found");
        }
        taskRepository.delete(task);
    }

    public List<Task> findAllByUserID(Integer userID) {
        return taskRepository.findAllByUserID(userID);
    }

    public List<Task> findAllByClubID(Integer clubID) {
        return taskRepository.findAllByClubID(clubID);
    }
}
