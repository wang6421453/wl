package com.wl.szcloud.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author wl
 * 用户角色对应表（user与role的中间关系表）
 * date 2015-5-14
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "p_un_user_role")
public class UserRoleVO {

	/**用户角色id*/
	@Id
	/*@GeneratedValue(generator = "generator")*/
	/*@GeneratedValue(strategy = GenerationType.AUTO, generator = "")*/
	/*@GenericGenerator(name = "generator", strategy = "increment")*/
	/*@GenericGenerator(name = "generator", strategy = "uuid")*/
	@Column(name = "USER_ROLE_ID", unique = true, nullable = false)
	private String userRoleId;
	
	/**用户ID*/
	@Column(name = "USER_ID")
	private String userId;
	
	/**角色ID*/
	@Column(name = "ROLE_ID")
	private String roleId;
	
	/**多对一，cascade:级联; fetch:开启懒加载 FetchType.LAZY，如果使用急加载：FetchType.EAGER, 测试发现默认就是懒加载*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private RoleInfoVO roleInfoVO;
    
	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public RoleInfoVO getRoleInfoVO() {
		return roleInfoVO;
	}

	public void setRoleInfoVO(RoleInfoVO roleInfoVO) {
		this.roleInfoVO = roleInfoVO;
	}
	
	
}
