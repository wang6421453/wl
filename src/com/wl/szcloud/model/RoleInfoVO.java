package com.wl.szcloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author wl
 * 角色VO
 * date 2015-5-14
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "p_un_role_info")
public class RoleInfoVO {
	/**角色id*/
	@Id
	/*@GeneratedValue(generator = "generator")*/
	/*@GeneratedValue(strategy = GenerationType.AUTO, generator = "")*/
	/*@GenericGenerator(name = "generator", strategy = "increment")*/
	/*@GenericGenerator(name = "generator", strategy = "uuid")*/
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private String roleId;
	
	/**系统ID*/
	@Column(name = "SYS_ID")
	private String sysId;
	
	/**角色名称*/
	@Column(name = "ROLE_NAME")
	private String roleName;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}
