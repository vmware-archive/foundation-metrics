package io.pivotal.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CloudUser implements Serializable {

	private static final long serialVersionUID = -7342358601625547252L;

	private Metadata metadata;
	private UserEntity entity;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public UserEntity getEntity() {
		return entity;
	}

	public void setEntity(UserEntity entity) {
		this.entity = entity;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
