package io.pivotal.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import io.pivotal.domain.Email;

public class EmailServiceTest {
	@InjectMocks
	EmailService emailService;

	@Mock
	JavaMailSenderImpl javaMailSenderImpl;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSendEmailWithNullMessage() {
		emailService.sendEmail(null);
		verify(javaMailSenderImpl, times(0)).send(any(SimpleMailMessage.class));
	}

	@Test
	public void testSendEmailWithEmptyMessage() {
		emailService.sendEmail("");
		verify(javaMailSenderImpl, times(0)).send(any(SimpleMailMessage.class));
	}

	@Test
	public void testSendEmailWithEmailNotConfigured() {
		Email email = mock(Email.class);
		emailService.setEmail(email);
		emailService.sendEmail("Hello World");
		verify(javaMailSenderImpl, times(0)).send(any(SimpleMailMessage.class));
	}

	@Test
	public void testSendEmailWithEmailConfigured() {
		Email email = mock(Email.class);
		when(email.isEmailConfigured()).thenReturn(true);
		emailService.setEmail(email);
		emailService.sendEmail("Hello World");
		verify(javaMailSenderImpl, times(1)).send(any(SimpleMailMessage.class));
	}
}
