package io.pivotal.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.client.lib.domain.CloudSpace;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.service.SpaceService;

public class SpaceControllerTest {
	
	@InjectMocks
	SpaceController spaceController;
	
	@Mock
	SpaceService spaceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetSpaces() {
		List<CloudSpace> cloudSpaces = new ArrayList<>();
		cloudSpaces.add(mock(CloudSpace.class));
		when(spaceService.getSpaces()).thenReturn(cloudSpaces);
		
		List<CloudSpace> spaces = spaceController.getSpaces();
		assertNotNull(spaces);
		assertEquals(1, spaces.size());
		verify(spaceService, times(1)).getSpaces();
	}

}
