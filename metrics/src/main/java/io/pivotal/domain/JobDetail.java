package io.pivotal.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JobDetail implements Serializable {

	private static final long serialVersionUID = -8484612833599570476L;

	String deployment;
	String job;
	String ip;
	String index;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDeployment() {
		return deployment;
	}

	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
