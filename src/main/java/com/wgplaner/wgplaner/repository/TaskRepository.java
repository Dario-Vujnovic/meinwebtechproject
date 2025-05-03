package com.wgplaner.wgplaner.repository;

import com.wgplaner.wgplaner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

