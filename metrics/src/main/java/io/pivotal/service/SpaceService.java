package io.pivotal.service;

import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
	
	@Autowired
	CloudFoundryClient cloudFoundryClient;
	
	@Autowired
	CloudFoundryClientService clientService;
	
	public List<CloudSpace> getSpaces() {
		return cloudFoundryClient.getSpaces();
	}
	
	public Integer getTotalSpaces() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/spaces");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

}
