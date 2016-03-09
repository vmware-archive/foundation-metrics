package io.pivotal.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Users;
import io.pivotal.service.UserService;

public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUsers() {
		Users usersMock = mock(Users.class);
		when(userService.getAllUsers()).thenReturn(usersMock);
		
		Users users = userController.getUsers();
		
		assertNotNull(users);
		assertEquals(usersMock, users);
		verify(userService, times(1)).getAllUsers();
	}
}
