package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.model.Roommate;
import com.wgplaner.wgplaner.repository.TaskRepository;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println("Received task: " + task);
        System.out.println("Roommate in task: " + (task.getRoommate() != null ? task.getRoommate().getId() : "null"));

        if (task.getRoommate() != null && task.getRoommate().getId() != null) {
            Long roommateId = task.getRoommate().getId();
            Roommate roommate = roommateRepository.findById(roommateId)
                    .orElseThrow(() -> new RuntimeException("Roommate not found with ID: " + roommateId));
            System.out.println("Found roommate: " + roommate.getName() + " with ID: " + roommate.getId());
            task.setRoommate(roommate);
        } else {
            System.out.println("No roommate set or roommate ID is null");
            task.setRoommate(null);
        }

        Task savedTask = taskRepository.save(task);
        System.out.println("Saved task: " + savedTask.getId() + " with roommate: " +
                (savedTask.getRoommate() != null ? savedTask.getRoommate().getId() : "null"));
        return savedTask;
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


