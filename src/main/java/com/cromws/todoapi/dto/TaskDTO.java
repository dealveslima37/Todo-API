package com.cromws.todoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cromws.todoapi.models.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Por favor, preencha a sua tarefa")
	@Size(min = 3, max = 30, message = "O campo tarefa deve conter entre 3 e 30 caracteres")
	private String name;
	
	public TaskDTO(Task task) {
		this.id = task.getId();
		this.name = task.getName();
	}

}
