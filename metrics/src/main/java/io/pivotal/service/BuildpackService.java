package io.pivotal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildpackService {
	@Autowired
	CloudFoundryClientService clientService;

	public Integer getTotalBuildpacks() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/buildpacks");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

}
