package io.pivotal.domain;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Email implements Serializable {

	private static final long serialVersionUID = -8902782615580696757L;

	private String toAddr;
	private String subject;
	private String replyToAddr;
	private boolean emailEnabled;

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReplyToAddr() {
		return replyToAddr;
	}

	public void setReplyToAddr(String replyToAddr) {
		this.replyToAddr = replyToAddr;
	}

	public boolean isEmailEnabled() {
		return emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	@JsonIgnore
	public boolean isEmailConfigured() {
		return isEmailEnabled() && !StringUtils.isEmpty(this.getReplyToAddr())
				&& !StringUtils.isEmpty(this.getSubject()) && !StringUtils.isEmpty(this.getToAddr());
	}

}
