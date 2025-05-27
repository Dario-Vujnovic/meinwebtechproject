package com.wgplaner.wgplaner.controller;

import com.wgplaner.wgplaner.model.Flat;
import com.wgplaner.wgplaner.repository.FlatRepository;
import com.wgplaner.wgplaner.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Flat> getFlatById(@PathVariable Long id) {
        return flatRepository.findById(id);
    }

    @PostMapping
    public Flat createFlat(@RequestBody Flat flat) {
        return flatRepository.save(flat);
    }

    @PutMapping("/{id}")
    public Flat updateFlat(@PathVariable Long id, @RequestBody Flat updatedFlat) {
        updatedFlat.setId(id);
        return flatRepository.save(updatedFlat);
    }

    @DeleteMapping("/{id}")
    public void deleteFlat(@PathVariable Long id) {
        flatRepository.deleteById(id);
    }
}


