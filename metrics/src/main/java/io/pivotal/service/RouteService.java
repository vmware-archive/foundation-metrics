package io.pivotal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
	static final String ROUTES_API_URI = "/v2/routes";
	@Autowired
	CloudFoundryClientService clientService;
	
	public Integer getTotalRoutes() {
		Map<String, Object> respMap = clientService.getResponseMap(ROUTES_API_URI);
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}
}
