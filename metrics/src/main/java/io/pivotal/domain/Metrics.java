package io.pivotal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Metrics implements Serializable {

	private static final long serialVersionUID = 7593142592683888616L;

	private List<VMMetric> vmMetrics = new ArrayList<>();
	private List<CustomJobMetric> customJobMetrics = new ArrayList<>();

	public List<VMMetric> getVmMetrics() {
		return vmMetrics;
	}

	public void addVmMetrics(VMMetric vmMetric) {
		this.getVmMetrics().add(vmMetric);
	}

	public List<CustomJobMetric> getCustomJobMetrics() {
		return customJobMetrics;
	}

	public void addCustomJobMetrics(CustomJobMetric customJobMetric) {
		this.getCustomJobMetrics().add(customJobMetric);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
