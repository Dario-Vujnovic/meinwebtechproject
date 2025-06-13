package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Roommate;
import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import com.wgplaner.wgplaner.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roommates")
@CrossOrigin(origins = "*")
public class RoommateController {

    private final RoommateRepository roommateRepository;
    private final TaskRepository taskRepository;

    public RoommateController(RoommateRepository roommateRepository, TaskRepository taskRepository) {
        this.roommateRepository = roommateRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Roommate> getAllRoommates() {
        return roommateRepository.findAll();
    }

    @GetMapping("/{id}")
    public Roommate getRoommateById(@PathVariable Long id) {
        return roommateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mitbewohner mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public Roommate createRoommate(@RequestBody Roommate roommate) {
        return roommateRepository.save(roommate);
    }

    @PutMapping("/{id}")
    public Roommate updateRoommate(@PathVariable Long id, @RequestBody Roommate updatedRoommate) {
        Optional<Roommate> roommateOptional = roommateRepository.findById(id);
        if (roommateOptional.isPresent()) {
            Roommate roommate = roommateOptional.get();
            roommate.setName(updatedRoommate.getName());
            return roommateRepository.save(roommate);
        } else {
            throw new RuntimeException("Mitbewohner mit ID " + id + " nicht gefunden");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRoommate(@PathVariable Long id) {
        // 1. Alle Aufgaben des Roommates finden
        List<Task> tasks = taskRepository.findByRoommate_Id(id);

        // 2. Roommate von Aufgaben entkoppeln
        for (Task task : tasks) {
            task.setRoommate(null);  // Unassigned
            taskRepository.save(task);
        }

        // 3. Roommate löschen
        roommateRepository.deleteById(id);
    }
}




