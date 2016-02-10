package io.pivotal.controller;

import java.util.List;

import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.service.SpaceService;

@RestController
@RequestMapping(value = "spaces")
public class SpaceController {
	
	@Autowired
	SpaceService spaceService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<CloudSpace> getSpaces() {
		return spaceService.getSpaces();
	}
}
