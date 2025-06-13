package com.wgplaner.wgplaner.repository;

import com.wgplaner.wgplaner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Alle Aufgaben finden, die einem bestimmten Roommate zugewiesen sind
    List<Task> findByRoommate_Id(Long roommateId);
}




