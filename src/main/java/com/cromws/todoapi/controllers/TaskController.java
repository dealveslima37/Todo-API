package com.cromws.todoapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromws.todoapi.dto.TaskDTO;
import com.cromws.todoapi.services.TaskService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

	private TaskService service;

	@PostMapping
	public ResponseEntity<TaskDTO> save(@Valid @RequestBody TaskDTO dto) {
		TaskDTO task = service.save(dto);

		return new ResponseEntity<TaskDTO>(task, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> findAll() {
		List<TaskDTO> tasks = service.findAll();

		return new ResponseEntity<List<TaskDTO>>(tasks, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		TaskDTO user = service.findById(id);

		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
		TaskDTO task = service.update(id, dto);

		return new ResponseEntity<TaskDTO>(task, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
