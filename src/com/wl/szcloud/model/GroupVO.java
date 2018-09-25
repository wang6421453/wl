package com.wl.szcloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author wl
 * 组VO
 * date 2015-5-12
 *
 */

@Entity
@Table(name = "p_un_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GroupVO {

	/**组id*/
	@Id
	/*@GeneratedValue(generator = "generator")*/
	/*@GeneratedValue(strategy = GenerationType.AUTO, generator = "")*/
	/*@GenericGenerator(name = "generator", strategy = "increment")*/
	/*@GenericGenerator(name = "generator", strategy = "uuid")*/
	@Column(name = "GROUP_ID", unique = true, nullable = false)
	private String groupId;
	
	/**公司名*/
	@Column(name = "GROUP_CH_NAME")
	private String groupName;
	
	/**组织机构代码*/
	@Column(name = "ORG_CODE")
	private String orgCode;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
