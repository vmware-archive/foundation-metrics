package io.pivotal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
	@Autowired
	CloudFoundryClientService clientService;
	
	public Integer getTotalRoutes() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/routes");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}
}
