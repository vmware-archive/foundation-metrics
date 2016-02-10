package io.pivotal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.util.JsonUtil;
import org.cloudfoundry.client.lib.util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.pivotal.domain.Metadata;

@Service
public class CloudFoundryClientService {
	@Autowired
	CloudFoundryClient cloudFoundryClient;

	RestTemplate restTemplate;

	RestUtil restUtil;

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public Map<String, Object> getResponseMap(String path) {
		ResponseEntity<String> response = getResponse(path);
		Map<String, Object> respMap = JsonUtil.convertJsonToMap(response.getBody());
		return respMap;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllResources(Map<String, Object> respMap) {
		List<Map<String, Object>> allResources = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newResources = (List<Map<String, Object>>) respMap.get("resources");
		if (newResources != null && newResources.size() > 0) {
			allResources.addAll(newResources);
		}
		String nextUrl = (String) respMap.get("next_url");
		while (nextUrl != null && nextUrl.length() > 0) {
			nextUrl = addPageOfResources(nextUrl, allResources);
		}
		return allResources;
	}

	@SuppressWarnings("unchecked")
	private String addPageOfResources(String nextUrl, List<Map<String, Object>> allResources) {
		String resp = getResponse(nextUrl).getBody();
		Map<String, Object> respMap = JsonUtil.convertJsonToMap(resp);
		List<Map<String, Object>> newResources = (List<Map<String, Object>>) respMap.get("resources");
		if (newResources != null && newResources.size() > 0) {
			allResources.addAll(newResources);
		}
		return (String) respMap.get("next_url");
	}

	@SuppressWarnings("unchecked")
	public Metadata getMeta(Map<String, Object> resource) {
		Map<String, Object> metadata = (Map<String, Object>) resource.get("metadata");
		UUID guid;
		try {
			guid = UUID.fromString(String.valueOf(metadata.get("guid")));
		} catch (IllegalArgumentException e) {
			guid = null;
		}
		Date createdDate = parseDate(String.valueOf(metadata.get("created_at")));
		Date updatedDate = parseDate(String.valueOf(metadata.get("updated_at")));
		String url = String.valueOf(metadata.get("url"));
		return new Metadata(guid, createdDate, updatedDate, url);
	}
	
	public Integer getTotalResults(Map<String, Object> respMap) {
		Integer totalCount = Integer.valueOf(respMap.get("total_results").toString());
		System.out.println("Total count: " + totalCount);
		return totalCount;
	}

	private RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restUtil = new RestUtil();
			restTemplate = restUtil.createRestTemplate(null, true);
		}
		return restTemplate;
	}

	protected String getUrl(String path) {
		String cloudControllerUrl = cloudFoundryClient.getCloudControllerUrl().toString();
		return cloudControllerUrl + (path.startsWith("/") ? path : "/" + path);
	}
	
	private ResponseEntity<String> getResponse(String path) {
		OAuth2AccessToken token = cloudFoundryClient.login();
		String authorizationHeaderValue = token.getTokenType() + " " + token.getValue();

		String url = cloudFoundryClient.getCloudControllerUrl() + path;

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, authorizationHeaderValue);

		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = getRestTemplate().exchange(url, HttpMethod.GET, httpEntity, String.class);
		return response;
	}


	private static Date parseDate(String dateString) {
		if (dateString != null) {
			try {
				// if the time zone part of the dateString contains a colon
				// (e.g. 2013-09-19T21:56:36+00:00)
				// then remove it before parsing
				String isoDateString = dateString.replaceFirst(":(?=[0-9]{2}$)", "").replaceFirst("Z$", "+0000");
				return dateFormatter.parse(isoDateString);
			} catch (Exception ignore) {
			}
		}
		return null;
	}
}
