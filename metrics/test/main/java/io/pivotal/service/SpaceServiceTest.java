package io.pivotal.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.utils.TestFileReader;

public class SpaceServiceTest {
	@InjectMocks
	SpaceService spaceService;
	
	@Mock
	CloudFoundryClient cloudFoundryClient;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotalSpacesCount() {
		String path = SpaceService.SPACES_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("spaces.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer spaces = spaceService.getTotalSpaces();
		
		assertEquals(8, spaces.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetSpaces() {
		List<CloudSpace> spacesMock = new ArrayList<>();
		spacesMock.add(mock(CloudSpace.class));
		when(cloudFoundryClient.getSpaces()).thenReturn(spacesMock);
		
		List<CloudSpace> spaces = spaceService.getSpaces();
		assertNotNull(spaces);
		assertEquals(spacesMock.size(), spaces.size());
		verify(cloudFoundryClient, times(1)).getSpaces();
	}
}
