package io.pivotal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {

	private static final long serialVersionUID = 8695431861163792223L;

	private int totalUsers;
	private List<CloudUser> cloudUsers = new ArrayList<>();

	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public List<CloudUser> getCloudUsers() {
		return cloudUsers;
	}

	public void addCloudUsers(CloudUser cloudUser) {
		this.getCloudUsers().add(cloudUser);
	}

}
