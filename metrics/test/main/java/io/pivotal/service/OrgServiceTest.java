package io.pivotal.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.utils.TestFileReader;

public class OrgServiceTest {
	@InjectMocks
	OrgService orgService;
	
	@Mock
	CloudFoundryClient cloudFoundryClient;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotalOrgsCount() {
		String path = OrgService.ORG_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("orgs.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer orgs = orgService.getTotalOrgs();
		
		assertEquals(3, orgs.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetOrgs() {
		List<CloudOrganization> orgsMock = new ArrayList<>();
		orgsMock.add(mock(CloudOrganization.class));
		when(cloudFoundryClient.getOrganizations()).thenReturn(orgsMock);
		
		List<CloudOrganization> orgs = orgService.getOrgs();
		assertNotNull(orgs);
		assertEquals(orgsMock.size(), orgs.size());
		verify(cloudFoundryClient, times(1)).getOrganizations();
	}
}
