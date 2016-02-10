package io.pivotal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Metadata implements Serializable {

	private static final long serialVersionUID = -2687678814411445134L;

	private UUID guid;
	private String url;
	private Date createDate;
	private Date updateDate;

	public Metadata(UUID guid, Date createdDate, Date updatedDate, String url) {
		this.guid = guid;
		this.createDate = createdDate;
		this.updateDate = updatedDate;
		this.url = url;
	}

	public UUID getGuid() {
		return guid;
	}

	public void setGuid(UUID guid) {
		this.guid = guid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
