package io.pivotal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Users;
import io.pivotal.utils.TestFileReader;

public class UserServiceTest {
	
	@InjectMocks
	UserService userService;
	
	@Mock
	CloudFoundryClientService clientService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTotalUsersCount() {
		String path = UserService.USERS_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("users.json").getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Integer users = userService.getTotalUsers();
		
		assertEquals(9, users.intValue());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetUsers() {
		String path = UserService.USERS_API_URI;
		Map<String, Object> responseMap = new TestFileReader().getFileContents("users.json").getResponseMap();
		List<Map<String, Object>> newResources = (List<Map<String, Object>>) responseMap.get("resources");
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getAllResources(responseMap)).thenReturn(newResources);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);
		
		Users allUsers = userService.getAllUsers();
		assertEquals(9, allUsers.getCloudUsers().size());
		
		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getAllResources(responseMap);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

}
