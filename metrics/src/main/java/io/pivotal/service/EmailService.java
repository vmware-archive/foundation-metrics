package io.pivotal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.pivotal.domain.Email;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	private Email email = new Email();

	public void sendEmail(String message) {
		if (email != null && email.isEmailConfigured()) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email.getToAddr());
			mailMessage.setReplyTo(email.getReplyToAddr());
			mailMessage.setSubject(email.getSubject());
			mailMessage.setText(message);
			javaMailSender.send(mailMessage);
		}
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
