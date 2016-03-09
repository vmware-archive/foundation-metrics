package io.pivotal.controller;

import java.util.List;

import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.service.OrgService;

@RestController
@RequestMapping(value = "orgs")
public class OrgController {
	@Autowired
	OrgService orgService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public List<CloudOrganization> getOrgs() {
		List<CloudOrganization> orgs = orgService.getOrgs();
		return orgs;
	}
}
