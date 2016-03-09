package io.pivotal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.utils.TestFileReader;

public class ServicesServiceTest {
	@InjectMocks
	ServicesService servicesService;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotalServiceBrokersCount() {
		String path = "/v2/service_brokers";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("service_brokers.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer serviceBrokers = servicesService.getTotalServiceBrokers();
		
		assertEquals(6, serviceBrokers.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetTotalServiceInstancesCount() {
		String path = "/v2/service_instances";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("service_instances.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer serviceInstances = servicesService.getTotalServiceInstances();
		
		assertEquals(7, serviceInstances.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetTotalServicesCount() {
		String path = "/v2/services";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("services.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer services = servicesService.getTotalServices();
		
		assertEquals(8, services.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
}
