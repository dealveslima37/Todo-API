package com.cromws.todoapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cromws.todoapi.dto.TaskDTO;
import com.cromws.todoapi.models.Task;
import com.cromws.todoapi.models.User;
import com.cromws.todoapi.repositories.TaskRepository;
import com.cromws.todoapi.repositories.UserRepository;
import com.cromws.todoapi.securityconfig.UserSS;
import com.cromws.todoapi.securityconfig.UserSystem;
import com.cromws.todoapi.services.exceptions.AuthorizationException;
import com.cromws.todoapi.services.exceptions.EntityNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskService {

	private TaskRepository repository;
	private UserService service;
	private UserRepository userRepository;

	public TaskDTO save(TaskDTO dto) {
		UserSS userSystem = UserSystem.authenticated();

		if (userSystem == null) {
			throw new AuthorizationException("Acesso negado");
		}

		User user = userRepository.getById(userSystem.getId());

		Task task = new Task(dto.getName(), user);
		task = repository.save(task);

		return new TaskDTO(task);
	}

	public List<TaskDTO> findAll() {
		UserSS userSystem = UserSystem.authenticated();

		if (userSystem == null) {
			throw new AuthorizationException("Acesso negado");
		}

		User user = service.findById(userSystem.getId());

		List<Task> tasks = repository.findByUser(user);

		return tasks.stream().map(x -> new TaskDTO(x)).collect(Collectors.toList());
	}

	public TaskDTO findById(Long id) {
		Optional<Task> task = repository.findById(id);
		if (task.isEmpty()) {
			throw new EntityNotFoundException("Não existe tarefa cadastrada com esse id");
		}

		return new TaskDTO(task.get());
	}

	public TaskDTO update(Long id, TaskDTO dto) {
		UserSS userSystem = UserSystem.authenticated();

		if (userSystem == null) {
			throw new AuthorizationException("Acesso negado");
		}
		User user = userRepository.getById(userSystem.getId());
		Task task = new Task();
		task.setId(id);
		task.setName(dto.getName());
		task.setUser(user);

		task = repository.save(task);

		return new TaskDTO(task);
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}

}
