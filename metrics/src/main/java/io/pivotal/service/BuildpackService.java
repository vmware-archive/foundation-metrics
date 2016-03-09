package io.pivotal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildpackService {
	static final String BUILDPACKS_API_URI = "/v2/buildpacks";
	
	@Autowired
	CloudFoundryClientService clientService;

	public Integer getTotalBuildpacks() {
		Map<String, Object> respMap = clientService.getResponseMap(BUILDPACKS_API_URI);
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

}
