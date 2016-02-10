package io.pivotal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.domain.Users;
import io.pivotal.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public Users getUsers() {
		return userService.getAllUsers();
	}
}
