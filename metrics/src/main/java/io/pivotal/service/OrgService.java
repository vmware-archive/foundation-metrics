package io.pivotal.service;

import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
	static String ORG_API_URI = "/v2/organizations";
	
	@Autowired
	CloudFoundryClient cloudFoundryClient;
	
	@Autowired
	CloudFoundryClientService clientService;

	public List<CloudOrganization> getOrgs() {
		List<CloudOrganization> organizations = cloudFoundryClient.getOrganizations();
		return organizations;
	}

	public Integer getTotalOrgs() {
		Map<String, Object> respMap = clientService.getResponseMap(ORG_API_URI);
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}
	
}
