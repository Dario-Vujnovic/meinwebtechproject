package com.wgplaner.wgplaner.model.controller;

import com.wgplaner.wgplaner.model.Roommate;
import com.wgplaner.wgplaner.repository.RoommateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roommates")
@CrossOrigin(origins = "*")
public class RoommateController {

    private final RoommateRepository roommateRepository;

    public RoommateController(RoommateRepository roommateRepository) {
        this.roommateRepository = roommateRepository;
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
        roommateRepository.deleteById(id);
    }
}


