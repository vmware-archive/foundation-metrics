package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Email;
import io.pivotal.service.EmailService;

public class SettingsControllerTest {
	@InjectMocks
	SettingsController settingsController;
	
	@Mock
	EmailService emailService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testConfigureEmailSettings() {
		Email email = mock(Email.class);
		when(emailService.getEmail()).thenReturn(email);
		
		Email savedEmail = settingsController.configureEmail(email);
		assertNotNull(savedEmail);
		assertEquals(email, savedEmail);
		verify(emailService, times(1)).setEmail(email);
		verify(emailService, times(1)).getEmail();
	}

	@Test
	public void testGetEmailSettings() {
		Email email = mock(Email.class);
		when(emailService.getEmail()).thenReturn(email);
		
		Email savedEmail = settingsController.configureEmail(email);
		assertNotNull(savedEmail);
		assertEquals(email, savedEmail);
		verify(emailService, times(1)).getEmail();
	}

}
