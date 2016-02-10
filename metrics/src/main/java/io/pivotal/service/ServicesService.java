package io.pivotal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
	@Autowired
	CloudFoundryClientService clientService;

	public Integer getTotalServiceBrokers() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/service_brokers");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

	public Integer getTotalServices() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/services");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

	public Integer getTotalServiceInstances() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/service_instances");
		Integer totalCount = clientService.getTotalResults(respMap);
		return totalCount;
	}

}
