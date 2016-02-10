package io.pivotal.domain;

import java.io.Serializable;

public class Total implements Serializable {

	private static final long serialVersionUID = 6305544096143577423L;

	private String name;
	private int value;

	public Total(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
