package io.pivotal.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Attribute implements Serializable {

	private static final long serialVersionUID = 5776125061796199444L;
	
	private String name;
	private String value;

	public Attribute(String attributeName, String attributeValue) {
		this.name = attributeName;
		this.value = attributeValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
