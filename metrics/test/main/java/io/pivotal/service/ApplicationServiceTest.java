package io.pivotal.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Application;
import io.pivotal.domain.Total;
import io.pivotal.utils.TestFileReader;

public class ApplicationServiceTest {
	
	@InjectMocks
	ApplicationService applicationService;
	
	@Mock
	CloudFoundryClient cloudFoundryClient;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetApplications() {
		String path = ApplicationService.APPS_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("apps.json").getResponseMap();
		List<Map<String, Object>> newResources = (List<Map<String, Object>>) responseMap.get("resources");
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getAllResources(responseMap)).thenReturn(newResources);
		
		List<Application> applications = applicationService.getApplications();
		assertNotNull(applications);
		assertEquals(4, applications.size());
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getAllResources(responseMap);
	}
	
	@Test
	public void testGetApplicationsWithEmptyResponse() {
		String path = ApplicationService.APPS_API_URI;
		Map<String, Object> responseMap = new HashMap<>();
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		
		List<Application> applications = applicationService.getApplications();
		assertNotNull(applications);
		assertEquals(0, applications.size());
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getAllResources(responseMap);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPopulateAppCounts() {
		String path = ApplicationService.APPS_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("apps.json").getResponseMap();
		List<Map<String, Object>> newResources = (List<Map<String, Object>>) responseMap.get("resources");
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getAllResources(responseMap)).thenReturn(newResources);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		List<Total> totals = new ArrayList<>();
		applicationService.populateAppCounts(totals);
		
		assertNotNull(totals);
		assertEquals(5, totals.size());
		
		for (Total total : totals) {
			if(total.getName().contains("Total Apps Count")) assertEquals(4, total.getValue());
			if(total.getName().contains("Total AI's Count")) assertEquals(4, total.getValue());
			if(total.getName().contains("Total Running AI's Count")) assertEquals(3, total.getValue());
			if(total.getName().contains("Total Diego Apps Count")) assertEquals(4, total.getValue());
			if(total.getName().contains("Total Warden Apps Count")) assertEquals(0, total.getValue());
		}
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getAllResources(responseMap);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

}
