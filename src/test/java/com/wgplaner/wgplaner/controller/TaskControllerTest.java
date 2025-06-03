package com.wgplaner.wgplaner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgplaner.wgplaner.model.Flat;
import com.wgplaner.wgplaner.model.Task;
import com.wgplaner.wgplaner.repository.FlatRepository;
import com.wgplaner.wgplaner.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Flat flat;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll();
        flatRepository.deleteAll();
        flat = flatRepository.save(new Flat("Testwohnung"));
    }

    @Test
    public void testCreateTask() throws Exception {
        Task task = new Task();
        task.setDescription("Testaufgabe");
        task.setFlat(flat);

        mockMvc.perform(post("/api/tasks/" + flat.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Testaufgabe"));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        Task task = new Task();
        task.setDescription("Lesen");
        task.setFlat(flat);
        taskRepository.save(task);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is("Lesen")));
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setDescription("Alt");
        task.setFlat(flat);
        task = taskRepository.save(task);

        task.setDescription("Neu");

        mockMvc.perform(put("/api/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Neu"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Task task = new Task();
        task.setDescription("Zum Löschen");
        task.setFlat(flat);
        task = taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/" + task.getId()))
                .andExpect(status().isNoContent()); // korrekt: 204 statt 200
    }
}

