package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Flat;
import com.wgplaner.wgplaner.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    private FlatRepository flatRepository;

    @GetMapping
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flat getFlatById(@PathVariable Long id) {
        return flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wohnung nicht gefunden"));
    }

    @PostMapping
    public Flat createFlat(@RequestBody Flat flat) {
        return flatRepository.save(flat);
    }

    @PutMapping("/{id}")
    public Flat updateFlat(@PathVariable Long id, @RequestBody Flat updatedFlat) {
        Flat existing = flatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wohnung nicht gefunden"));

        existing.setName(updatedFlat.getName()); // Nur den Namen übernehmen

        return flatRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteFlat(@PathVariable Long id) {
        flatRepository.deleteById(id);
    }
}
