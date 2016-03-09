package io.pivotal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.pivotal.domain.Total;

public class TotalsServiceTest {

	@InjectMocks
	TotalsService totalsService;

	@Mock
	ApplicationService applicationService;

	@Mock
	BuildpackService buildpackService;

	@Mock
	SpaceService spaceService;

	@Mock
	UserService userService;

	@Mock
	OrgService orgService;

	@Mock
	RouteService routeService;

	@Mock
	ServicesService servicesService;

	@Mock
	EventService eventService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetTotals() {
		List<Total> totals = totalsService.getTotals();
		assertEquals(15, totals.size());
		verify(applicationService, times(1)).populateAppCounts(anyList());
		verify(buildpackService, times(1)).getTotalBuildpacks();
		verify(spaceService, times(1)).getTotalSpaces();
		verify(orgService, times(1)).getTotalOrgs();
		verify(userService, times(1)).getTotalUsers();
		verify(servicesService, times(1)).getTotalServiceBrokers();
		verify(servicesService, times(1)).getTotalServices();
		verify(servicesService, times(1)).getTotalServiceInstances();
		verify(eventService, times(1)).getTotalAppCrashEvents();
		verify(eventService, times(1)).getTotalAppCreateEvents();
		verify(eventService, times(1)).getTotalAppDeleteEvents();
		verify(eventService, times(1)).getTotalAppStartEvents();
		verify(eventService, times(1)).getTotalAppStopEvents();
		verify(eventService, times(1)).getTotalAppUpdateEvents();
		verify(eventService, times(1)).getTotalAuthorizedSSHEvents();
		verify(eventService, times(1)).getTotalUnAuthorizedSSHEvents();
	}

}
