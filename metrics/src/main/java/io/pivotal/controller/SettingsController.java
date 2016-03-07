package io.pivotal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.domain.Email;
import io.pivotal.service.EmailService;

@RestController
@RequestMapping(value = "settings")
public class SettingsController {

	@Autowired
	EmailService emailService;

	@CrossOrigin
	@RequestMapping(value = "mail", method = RequestMethod.POST)
	public Email configureEmail(@RequestBody Email email) {
		emailService.setEmail(email);
		return emailService.getEmail();
	}

	@CrossOrigin
	@RequestMapping(value = "mail", method = RequestMethod.GET)
	public Email getEmail() {
		return emailService.getEmail();
	}

}
