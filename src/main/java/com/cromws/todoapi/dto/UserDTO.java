package com.cromws.todoapi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cromws.todoapi.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Por favor, preencha seu nome")
	@Size(min = 3, max = 30, message = "O campo nome deve conter entre 3 e 30 caracteres")
	private String name;
	
	@NotEmpty(message = "Por favor, preencha seu email")
	@Email(message = "Informe um email válido")
	private String email;
	
	@NotEmpty(message = "Por favor, preencha sua senha")
	@Size(min = 8, max = 30, message = "A senha deve conter entre 8 e 30 caracteres")
	private String password;

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

}
