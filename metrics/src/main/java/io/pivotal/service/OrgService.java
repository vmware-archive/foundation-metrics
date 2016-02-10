package io.pivotal.service;

import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {
	@Autowired
	CloudFoundryClient cloudFoundryClient;
	
	@Autowired
	CloudFoundryClientService clientService;

	public List<CloudOrganization> getOrgs() {
		List<CloudOrganization> organizations = cloudFoundryClient.getOrganizations();
		return organizations;
	}

	public Integer getTotalOrgs() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/organizations");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}
	
}
