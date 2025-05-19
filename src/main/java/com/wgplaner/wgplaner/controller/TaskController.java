package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.model.Roommate;
import com.wgplaner.wgplaner.repository.TaskRepository;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskRepository taskRepository;
    private final RoommateRepository roommateRepository;

    public TaskController(TaskRepository taskRepository, RoommateRepository roommateRepository) {
        this.taskRepository = taskRepository;
        this.roommateRepository = roommateRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        if (task.getRoommate() != null && task.getRoommate().getId() != null) {
            Long roommateId = task.getRoommate().getId();
            Roommate roommate = roommateRepository.findById(roommateId)
                    .orElseThrow(() -> new RuntimeException("Roommate not found with ID: " + roommateId));
            task.setRoommate(roommate);
        } else {
            task.setRoommate(null);
        }
        return taskRepository.save(task);
    }



    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(updatedTask.getDescription());
        task.setDone(updatedTask.isDone());

        if (updatedTask.getRoommate() != null && updatedTask.getRoommate().getId() != null) {
            Long roommateId = updatedTask.getRoommate().getId();
            Roommate roommate = roommateRepository.findById(roommateId)
                    .orElseThrow(() -> new RuntimeException("Roommate not found with ID: " + roommateId));
            task.setRoommate(roommate);
        } else {
            task.setRoommate(null);
        }

        return taskRepository.save(task);
    }

}


