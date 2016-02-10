package io.pivotal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.domain.Total;

@Service
public class TotalsService {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	BuildpackService buildpackService;

	@Autowired
	SpaceService spaceService;

	@Autowired
	UserService userService;

	@Autowired
	OrgService orgService;

	@Autowired
	RouteService routeService;

	@Autowired
	ServicesService servicesService;

	@Autowired
	EventService eventService;

	public List<Total> getTotals() {
		List<Total> totals = new ArrayList<Total>();
		
		
		totals.add(new Total("Total Orgs", orgService.getTotalOrgs()));
		totals.add(new Total("Total Spaces", spaceService.getTotalSpaces()));
		totals.add(new Total("Total Users", userService.getTotalUsers()));
		totals.add(new Total("Total Buildpacks", buildpackService.getTotalBuildpacks()));
		totals.add(new Total("Total Services", servicesService.getTotalServices()));
		totals.add(new Total("Total Service Brokers", servicesService.getTotalServiceBrokers()));
		totals.add(new Total("Total Service Instances", servicesService.getTotalServiceInstances()));

		totals.add(new Total("Total App Create Events", eventService.getTotalAppCreateEvents()));
		totals.add(new Total("Total App Delete Events", eventService.getTotalAppDeleteEvents()));
		totals.add(new Total("Total App Authorized SSH Events", eventService.getTotalAuthorizedSSHEvents()));
		totals.add(new Total("Total App UnAuthorized SSH Events", eventService.getTotaUnAuthorizedSSHEvents()));
		totals.add(new Total("Total App Start Events", eventService.getTotalAppStartEvents()));
		totals.add(new Total("Total App Stop Events", eventService.getTotalAppStartEvents()));
		totals.add(new Total("Total App Update Events", eventService.getTotalAppUpdateEvents()));
		totals.add(new Total("Total App Crash Events", eventService.getTotalAppCrashEvents()));

		applicationService.populateAppCounts(totals);

		return totals;
	}
}
