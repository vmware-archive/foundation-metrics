package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Application;
import io.pivotal.service.ApplicationService;

public class ApplicationControllerTest {
	
	@InjectMocks
	ApplicationController applicationController;
	
	@Mock
	ApplicationService applicationService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetApps() {
		List<Application> applications = new ArrayList<>();
		applications.add(mock(Application.class));
		when(applicationService.getApplications()).thenReturn(applications);
		try {
			List<Application> apps = applicationController.getApps();
			assertEquals(1, apps.size());
			verify(applicationService, times(1)).getApplications();
		} catch (Exception e) {
			fail("No exception should be thrown here");
		}
	}

}
