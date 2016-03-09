package io.pivotal.service;

import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
	
	static final String SPACES_API_URI = "/v2/spaces";

	@Autowired
	CloudFoundryClient cloudFoundryClient;
	
	@Autowired
	CloudFoundryClientService clientService;
	
	public List<CloudSpace> getSpaces() {
		return cloudFoundryClient.getSpaces();
	}
	
	public Integer getTotalSpaces() {
		Map<String, Object> respMap = clientService.getResponseMap(SPACES_API_URI);
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

}
