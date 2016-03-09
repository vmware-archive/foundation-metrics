package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.client.lib.domain.CloudOrganization;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.service.OrgService;

public class OrgControllerTest {
	
	@InjectMocks
	OrgController orgConttoller;
	
	@Mock
	OrgService orgService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOrgs() {
		List<CloudOrganization> cloudOrgs = new ArrayList<CloudOrganization>();
		cloudOrgs.add(mock(CloudOrganization.class));
		when(orgService.getOrgs()).thenReturn(cloudOrgs);
		List<CloudOrganization> orgs = orgConttoller.getOrgs();
		assertNotNull(orgs);
		assertEquals(1, orgs.size());
		verify(orgService, times(1)).getOrgs();
	}

}
