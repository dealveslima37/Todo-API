package com.cromws.todoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cromws.todoapi.models.Task;
import com.cromws.todoapi.models.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByUser(User user);

}
