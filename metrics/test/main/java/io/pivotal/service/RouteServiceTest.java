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

public class RouteServiceTest {
	
	@InjectMocks
	RouteService routeService;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotalBuildpacksCount() {
		String path = RouteService.ROUTES_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("routes.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer totalRoutes = routeService.getTotalRoutes();
		
		assertEquals(13, totalRoutes.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	

}
