package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.repository.TaskRepository;
import com.wgplaner.wgplaner.repository.FlatRepository;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{flatId}")
    public ResponseEntity<Task> createTask(@PathVariable Long flatId, @RequestBody Task task) {
        return flatRepository.findById(flatId)
                .map(flat -> {
                    task.setFlat(flat);
                    if (task.getRoommate() != null && task.getRoommate().getId() != null) {
                        roommateRepository.findById(task.getRoommate().getId())
                                .ifPresent(task::setRoommate);
                    }
                    Task saved = taskRepository.save(task);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        updatedTask.setId(id);

        if (updatedTask.getRoommate() != null && updatedTask.getRoommate().getId() != null) {
            roommateRepository.findById(updatedTask.getRoommate().getId())
                    .ifPresent(updatedTask::setRoommate);
        }

        return ResponseEntity.ok(taskRepository.save(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
