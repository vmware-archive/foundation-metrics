package io.pivotal.domain;

import java.io.Serializable;

public class Rule implements Serializable {

	private static final long serialVersionUID = 8002090417630349025L;
	private String attributeName1;
	private String attributeName2;
	private Integer threadshold;
	private String message;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAttributeName1() {
		return attributeName1;
	}

	public String getAttributeName2() {
		return attributeName2;
	}

	public Integer getThreadshold() {
		return threadshold;
	}

	public String getMessage() {
		return message;
	}

	public Rule(String attributeName1, String attributeName2, Integer threadshold, String message) {
		this.attributeName1 = attributeName1;
		this.attributeName2 = attributeName2;
		this.threadshold = threadshold;
		this.message = message;
	}

}
