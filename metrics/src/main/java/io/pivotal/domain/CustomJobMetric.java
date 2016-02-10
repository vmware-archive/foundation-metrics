package io.pivotal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomJobMetric implements Serializable {

	private static final long serialVersionUID = 7882465698808357064L;

	private JobDetail jobDetail;
	private List<Attribute> customAttributes = new ArrayList<>();

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public List<Attribute> getCustomAttributes() {
		return customAttributes;
	}

	public void addCustomAttributes(Attribute customAttribute) {
		this.getCustomAttributes().add(customAttribute);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
