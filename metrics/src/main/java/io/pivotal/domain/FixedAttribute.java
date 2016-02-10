package io.pivotal.domain;

import java.io.Serializable;

public class FixedAttribute implements Serializable {

	private static final long serialVersionUID = -6109341777090339905L;

	private String system_healthy;
	private String system_load_1m;
	private String system_swap_percent;
	private String system_swap_kb;
	private String system_cpu_user;
	private String system_disk_ephemeral_inode_percent;
	private String system_disk_ephemeral_percent;
	private String system_disk_system_percent;
	private String system_cpu_sys;
	private String system_disk_persistent_percent;
	private String system_mem_kb;
	private String system_cpu_wait;
	private String system_mem_percent;
	private String system_disk_system_inode_percent;
	private String system_disk_persistent_inode_percent;

	public String getSystem_healthy() {
		return system_healthy;
	}

	public void setSystem_healthy(String system_healthy) {
		this.system_healthy = system_healthy;
	}

	public String getSystem_load_1m() {
		return system_load_1m;
	}

	public void setSystem_load_1m(String system_load_1m) {
		this.system_load_1m = system_load_1m;
	}

	public String getSystem_swap_percent() {
		return system_swap_percent;
	}

	public void setSystem_swap_percent(String system_swap_percent) {
		this.system_swap_percent = system_swap_percent;
	}

	public String getSystem_swap_kb() {
		return system_swap_kb;
	}

	public void setSystem_swap_kb(String system_swap_kb) {
		this.system_swap_kb = system_swap_kb;
	}

	public String getSystem_cpu_user() {
		return system_cpu_user;
	}

	public void setSystem_cpu_user(String system_cpu_user) {
		this.system_cpu_user = system_cpu_user;
	}

	public String getSystem_disk_ephemeral_inode_percent() {
		return system_disk_ephemeral_inode_percent;
	}

	public void setSystem_disk_ephemeral_inode_percent(String system_disk_ephemeral_inode_percent) {
		this.system_disk_ephemeral_inode_percent = system_disk_ephemeral_inode_percent;
	}

	public String getSystem_disk_ephemeral_percent() {
		return system_disk_ephemeral_percent;
	}

	public void setSystem_disk_ephemeral_percent(String system_disk_ephemeral_percent) {
		this.system_disk_ephemeral_percent = system_disk_ephemeral_percent;
	}

	public String getSystem_disk_system_percent() {
		return system_disk_system_percent;
	}

	public void setSystem_disk_system_percent(String system_disk_system_percent) {
		this.system_disk_system_percent = system_disk_system_percent;
	}

	public String getSystem_cpu_sys() {
		return system_cpu_sys;
	}

	public void setSystem_cpu_sys(String system_cpu_sys) {
		this.system_cpu_sys = system_cpu_sys;
	}

	public String getSystem_disk_persistent_percent() {
		return system_disk_persistent_percent;
	}

	public void setSystem_disk_persistent_percent(String system_disk_persistent_percent) {
		this.system_disk_persistent_percent = system_disk_persistent_percent;
	}

	public String getSystem_mem_kb() {
		return system_mem_kb;
	}

	public void setSystem_mem_kb(String system_mem_kb) {
		this.system_mem_kb = system_mem_kb;
	}

	public String getSystem_cpu_wait() {
		return system_cpu_wait;
	}

	public void setSystem_cpu_wait(String system_cpu_wait) {
		this.system_cpu_wait = system_cpu_wait;
	}

	public String getSystem_mem_percent() {
		return system_mem_percent;
	}

	public void setSystem_mem_percent(String system_mem_percent) {
		this.system_mem_percent = system_mem_percent;
	}

	public String getSystem_disk_system_inode_percent() {
		return system_disk_system_inode_percent;
	}

	public void setSystem_disk_system_inode_percent(String system_disk_system_inode_percent) {
		this.system_disk_system_inode_percent = system_disk_system_inode_percent;
	}

	public String getSystem_disk_persistent_inode_percent() {
		return system_disk_persistent_inode_percent;
	}

	public void setSystem_disk_persistent_inode_percent(String system_disk_persistent_inode_percent) {
		this.system_disk_persistent_inode_percent = system_disk_persistent_inode_percent;
	}

}
