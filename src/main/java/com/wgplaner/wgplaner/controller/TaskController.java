package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.repository.TaskRepository;
import com.wgplaner.wgplaner.repository.FlatRepository;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private RoommateRepository roommateRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping("/{flatId}")
    public Task createTask(@PathVariable Long flatId, @RequestBody Task task) {
        flatRepository.findById(flatId).ifPresent(task::setFlat);
        if (task.getRoommate() != null && task.getRoommate().getId() != null) {
            roommateRepository.findById(task.getRoommate().getId()).ifPresent(task::setRoommate);
        }
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        updatedTask.setId(id);
        if (updatedTask.getRoommate() != null && updatedTask.getRoommate().getId() != null) {
            roommateRepository.findById(updatedTask.getRoommate().getId()).ifPresent(updatedTask::setRoommate);
        }
        return taskRepository.save(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
