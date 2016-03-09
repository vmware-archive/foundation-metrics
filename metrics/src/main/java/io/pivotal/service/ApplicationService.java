package io.pivotal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.pivotal.domain.Application;
import io.pivotal.domain.Total;

@Service
public class ApplicationService {

	static final String APPS_API_URI = "/v2/apps";

	@Autowired
	CloudFoundryClient cloudFoundryClient;

	@Autowired
	CloudFoundryClientService clientService;

	@SuppressWarnings("unchecked")
	public List<Application> getApplications() {
		List<Application> applications = new ArrayList<Application>();
		Map<String, Object> respMap = clientService.getResponseMap(APPS_API_URI);
		List<Map<String, Object>> allResources = clientService.getAllResources(respMap);

		for (Map<String, Object> resource : allResources) {
			Application application = new Application();
			Map<String, Object> metadata = (Map<String, Object>) resource.get("metadata");
			Map<String, Object> entity = (Map<String, Object>) resource.get("entity");

			String appGuid = metadata.get("guid").toString();
			application.setAppGuid(appGuid);
			application.setName(entity.get("name").toString());
			application.setSpaceGUID(entity.get("space_guid").toString());
			application.setInstances(Integer.valueOf(entity.get("instances").toString()));
			application.setState(entity.get("state").toString());
			application.setMemory(Integer.valueOf(entity.get("memory").toString()));
			application.setDiskQuota(Integer.valueOf(entity.get("disk_quota").toString()));
			if (!StringUtils.isEmpty(entity.get("buildpack"))) {
				application.setBuildpack(entity.get("buildpack").toString());
			} else if (!StringUtils.isEmpty(entity.get("detected_buildpack"))) {
				application.setBuildpack(entity.get("detected_buildpack").toString());
			}

			applications.add(application);
		}

		return applications;
	}

	@SuppressWarnings("unchecked")
	public void populateAppCounts(List<Total> totals) {
		Map<String, Object> respMap = clientService.getResponseMap(APPS_API_URI);
		List<Map<String, Object>> allResources = clientService.getAllResources(respMap);
		Integer runningAIs = 0;
		Integer totalAIs = 0;
		Integer totalDiegoApps = 0;
		Integer totalWardenApps = 0;

		for (Map<String, Object> resource : allResources) {
			Map<String, Object> entity = (Map<String, Object>) resource.get("entity");
			if (entity.get("state").toString().equalsIgnoreCase("STARTED")) {
				runningAIs += Integer.valueOf(entity.get("instances").toString());
			}
			totalAIs += Integer.valueOf(entity.get("instances").toString());
			if (Boolean.valueOf(entity.get("diego").toString())) {
				totalDiegoApps += 1;
			} else {
				totalWardenApps += 1;
			}
		}

		Integer totalCount = clientService.getTotalResults(respMap);

		totals.add(new Total("Total Apps Count", totalCount));
		totals.add(new Total("Total AI's Count", totalAIs));
		totals.add(new Total("Total Running AI's Count", runningAIs));
		totals.add(new Total("Total Diego Apps Count", totalDiegoApps));
		totals.add(new Total("Total Warden Apps Count", totalWardenApps));
	}

}
