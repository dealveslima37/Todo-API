package com.cromws.todoapi.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromws.todoapi.dto.UserDTO;
import com.cromws.todoapi.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {
		UserDTO user = service.save(dto);

		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

}
