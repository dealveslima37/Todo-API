package com.cromws.todoapi.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromws.todoapi.dto.UserDTO;
import com.cromws.todoapi.models.User;
import com.cromws.todoapi.repositories.UserRepository;
import com.cromws.todoapi.securityconfig.UserSS;
import com.cromws.todoapi.securityconfig.UserSystem;
import com.cromws.todoapi.services.exceptions.AuthorizationException;
import com.cromws.todoapi.services.exceptions.EntityNotFoundException;
import com.cromws.todoapi.services.exceptions.ExistingEmailException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private UserRepository repository;
	private BCryptPasswordEncoder pe;

	public UserDTO save(UserDTO dto) {
		if (findByEmail(dto.getEmail()) != null) {
			throw new ExistingEmailException("Já existe um usuário cadastrado com esse email");
		}

		User user = new User(dto.getName(), dto.getEmail(), pe.encode(dto.getPassword()));
		user = repository.save(user);

		return new UserDTO(user);
	}

	public User findById(Long id) {
		UserSS userSystem = UserSystem.authenticated();

		if (userSystem == null) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			throw new EntityNotFoundException("Não existe usuário cadastrado com esse id");
		}

		return user.get();
	}

	public User findByEmail(String email) {
		Optional<User> user = repository.findByEmail(email);

		return user.orElse(null);
	}

}
