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

public class EventServiceTest {

	@InjectMocks
	EventService eventService;

	@Mock
	CloudFoundryClientService clientService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetTotalAppCrashEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.crash";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_crash_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appCrashEvents = eventService.getTotalAppCrashEvents();

		assertEquals(0, appCrashEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

	@Test
	public void testGetTotalAppStartEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.start";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_start_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appStartEvents = eventService.getTotalAppStartEvents();

		assertEquals(0, appStartEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

	@Test
	public void testGetTotalAppStopEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.stop";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_stop_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appStopEvents = eventService.getTotalAppStopEvents();

		assertEquals(0, appStopEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

	@Test
	public void testGetTotalAppUpdateEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.update";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_update_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appUpdateEvents = eventService.getTotalAppUpdateEvents();

		assertEquals(229, appUpdateEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

	@Test
	public void testGetTotalAppDeleteEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.delete-request";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_delete-requests_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appDeleteEvents = eventService.getTotalAppDeleteEvents();

		assertEquals(11, appDeleteEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}

	@Test
	public void testGetTotalAppCreateEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.create";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_create_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer appCreateEvents = eventService.getTotalAppCreateEvents();

		assertEquals(25, appCreateEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetTotalAppAuthorizedSSHEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.ssh-authorized";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_ssh-authorized_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer authorizedSSHEvents = eventService.getTotalAuthorizedSSHEvents();

		assertEquals(1, authorizedSSHEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
	
	@Test
	public void testGetTotalAppUnAuthorizedEventCount() {
		String path = EventService.EVENT_API_URI + "?q=type:audit.app.ssh-unauthorized";
		Map<String, Object> responseMap = new TestFileReader().getFileContents("audit_app_ssh-unauthorized_events.json")
				.getResponseMap();
		Integer totalCount = Integer.valueOf(responseMap.get("total_results").toString());
		when(clientService.getResponseMap(path)).thenReturn(responseMap);
		when(clientService.getTotalResults(responseMap)).thenReturn(totalCount);

		Integer unAuthorizedSSHEvents = eventService.getTotalUnAuthorizedSSHEvents();

		assertEquals(0, unAuthorizedSSHEvents.intValue());

		verify(clientService, times(1)).getResponseMap(path);
		verify(clientService, times(1)).getTotalResults(responseMap);
	}
}
