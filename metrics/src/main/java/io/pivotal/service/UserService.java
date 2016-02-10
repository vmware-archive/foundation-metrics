package io.pivotal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.domain.CloudUser;
import io.pivotal.domain.Metadata;
import io.pivotal.domain.UserEntity;
import io.pivotal.domain.Users;

@Service
public class UserService {

	@Autowired
	CloudFoundryClientService clientService;

	public Users getAllUsers() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/users");
		Integer totalUsersCount = clientService.getTotalResults(respMap);

		List<Map<String, Object>> allResources = clientService.getAllResources(respMap);
		Users users = getCloudUsers(allResources);
		users.setTotalUsers(totalUsersCount);

		return users;
	}

	public Integer getTotalUsers() {
		Map<String, Object> respMap = clientService.getResponseMap("/v2/users");
		Integer totalUsersCount = clientService.getTotalResults(respMap);
		return totalUsersCount;
	}
	

	private Users getCloudUsers(List<Map<String, Object>> allResources) {
		Users user = new Users();
		for (Map<String, Object> resource : allResources) {
			user.addCloudUsers(getCloudUser(resource));
		}
		return user;
	}

	private CloudUser getCloudUser(Map<String, Object> resource) {
		CloudUser cloudUser = new CloudUser();

		Metadata meta = clientService.getMeta(resource);
		UserEntity userEntity = getEntity(resource);

		cloudUser.setMetadata(meta);
		cloudUser.setEntity(userEntity);

		return cloudUser;
	}

	@SuppressWarnings("unchecked")
	private UserEntity getEntity(Map<String, Object> resource) {
		UserEntity userEntity = new UserEntity();

		Map<String, Object> metadata = (Map<String, Object>) resource.get("entity");
		userEntity.setDefaultSpaceGuid(String.valueOf(metadata.get("default_space_guid")));
		userEntity.setUsername(String.valueOf(metadata.get("username")));
		userEntity.setSpacesUrl(String.valueOf(metadata.get("spaces_url")));
		userEntity.setOrganizationsUrl(String.valueOf(metadata.get("organizations_url")));
		userEntity.setManagedOrganizationsUrl(String.valueOf(metadata.get("managed_organizations_url")));
		userEntity.setBillingManagedOrganizationsUrl(String.valueOf(metadata.get("billing_managed_organizations_url")));
		userEntity.setAuditedOrganizationsUrl(String.valueOf(metadata.get("audited_organizations_url")));
		userEntity.setManagedSpacesUrl(String.valueOf(metadata.get("managed_spaces_url")));
		userEntity.setAuditedSpacesUrl(String.valueOf(metadata.get("audited_spaces_url")));
		userEntity.setAdmin(Boolean.getBoolean(String.valueOf(metadata.get("admin"))));
		userEntity.setActive(Boolean.getBoolean(String.valueOf(metadata.get("active"))));

		return userEntity;
	}

}
