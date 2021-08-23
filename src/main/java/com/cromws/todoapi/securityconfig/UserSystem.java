package com.cromws.todoapi.securityconfig;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserSystem {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
