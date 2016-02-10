package io.pivotal.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5867638461735629617L;

	private boolean admin;
	private boolean active;
	private String defaultSpaceGuid;
	private String username;
	private String spacesUrl;
	private String organizationsUrl;
	private String managedOrganizationsUrl;
	private String billingManagedOrganizationsUrl;
	private String auditedOrganizationsUrl;
	private String managedSpacesUrl;
	private String auditedSpacesUrl;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDefaultSpaceGuid() {
		return defaultSpaceGuid;
	}

	public void setDefaultSpaceGuid(String defaultSpaceGuid) {
		this.defaultSpaceGuid = defaultSpaceGuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSpacesUrl() {
		return spacesUrl;
	}

	public void setSpacesUrl(String spacesUrl) {
		this.spacesUrl = spacesUrl;
	}

	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	public String getManagedOrganizationsUrl() {
		return managedOrganizationsUrl;
	}

	public void setManagedOrganizationsUrl(String managedOrganizationsUrl) {
		this.managedOrganizationsUrl = managedOrganizationsUrl;
	}

	public String getBillingManagedOrganizationsUrl() {
		return billingManagedOrganizationsUrl;
	}

	public void setBillingManagedOrganizationsUrl(String billingManagedOrganizationsUrl) {
		this.billingManagedOrganizationsUrl = billingManagedOrganizationsUrl;
	}

	public String getAuditedOrganizationsUrl() {
		return auditedOrganizationsUrl;
	}

	public void setAuditedOrganizationsUrl(String auditedOrganizationsUrl) {
		this.auditedOrganizationsUrl = auditedOrganizationsUrl;
	}

	public String getManagedSpacesUrl() {
		return managedSpacesUrl;
	}

	public void setManagedSpacesUrl(String managedSpacesUrl) {
		this.managedSpacesUrl = managedSpacesUrl;
	}

	public String getAuditedSpacesUrl() {
		return auditedSpacesUrl;
	}

	public void setAuditedSpacesUrl(String auditedSpacesUrl) {
		this.auditedSpacesUrl = auditedSpacesUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
