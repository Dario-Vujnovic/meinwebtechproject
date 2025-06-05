package com.wgplaner.wgplaner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgplaner.wgplaner.model.Flat;
import com.wgplaner.wgplaner.repository.FlatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FlatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFlats() throws Exception {
        mockMvc.perform(get("/api/flats"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateFlat() throws Exception {
        Flat newFlat = new Flat();
        newFlat.setName("Test-Wohnung");

        mockMvc.perform(post("/api/flats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFlat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test-Wohnung"));
    }

    @Test
    void testUpdateFlat() throws Exception {
        Flat flat = new Flat();
        flat.setName("Altbau");
        flat = flatRepository.save(flat);

        Flat updated = new Flat();
        updated.setName("Neubau");

        mockMvc.perform(put("/api/flats/" + flat.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Neubau"));
    }

    @Test
    void testDeleteFlat() throws Exception {
        Flat flat = new Flat();
        flat.setName("Zu löschen");
        flat = flatRepository.save(flat);

        mockMvc.perform(delete("/api/flats/" + flat.getId()))
                .andExpect(status().isOk());
    }
}
