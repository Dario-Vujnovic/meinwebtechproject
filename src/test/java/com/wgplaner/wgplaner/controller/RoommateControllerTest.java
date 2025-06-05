package com.wgplaner.wgplaner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgplaner.wgplaner.model.Roommate;
import com.wgplaner.wgplaner.repository.RoommateRepository;
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
public class RoommateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoommateRepository roommateRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRoommates() throws Exception {
        mockMvc.perform(get("/api/roommates"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateRoommate() throws Exception {
        Roommate newRoommate = new Roommate();
        newRoommate.setName("Test-Mitbewohner");

        mockMvc.perform(post("/api/roommates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRoommate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test-Mitbewohner"));
    }

    @Test
    void testUpdateRoommate() throws Exception {
        // Erst einen Mitbewohner speichern
        Roommate roommate = new Roommate();
        roommate.setName("Original");
        roommate = roommateRepository.save(roommate);

        // Dann aktualisieren
        Roommate updated = new Roommate();
        updated.setName("Aktualisiert");

        mockMvc.perform(put("/api/roommates/" + roommate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aktualisiert"));
    }

    @Test
    void testDeleteRoommate() throws Exception {
        // Erst einen Mitbewohner speichern
        Roommate roommate = new Roommate();
        roommate.setName("Zum Löschen");
        roommate = roommateRepository.save(roommate);

        mockMvc.perform(delete("/api/roommates/" + roommate.getId()))
                .andExpect(status().isOk());
    }
}

