package com.wl.szcloud.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wl.common.model.BaseVO;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "p_un_user_base_info")
public class SzcloudUserVO extends BaseVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**用户id*/
	@Id
	/*@GeneratedValue(generator = "generator")*/
	/*@GeneratedValue(strategy = GenerationType.AUTO, generator = "")*/
	/*@GenericGenerator(name = "generator", strategy = "increment")*/
	/*@GenericGenerator(name = "generator", strategy = "uuid")*/
	@Column(name = "USER_ID", unique = true, nullable = false)
	private String id;
	
	/**组织ID*/
	@Column(name = "GROUP_ID")
	private String groupId;
	
	/**用户名*/
	@Column(name = "NAME")
	private String userName;
	
	/**密码*/
	@Column(name = "USER_PWD")
	private String password;
	
	/**身份证号*/
	@Column(name = "USER_ID_CARD_NUMBER")
	private String cardNo;
	
	/**电话号*/
	@Column(name = "MOBILE")
	private String phoneNo;
	
	/**是否启用，status*/
	@Column(name = "STATUS")
	private String useOrNo;
	
	/**环境，是否线上*/
	@Transient
	private String onlineOrOff;
	
	/**组织机构代码*/
	@Transient
	private String compCode;
	
	/**公司名*/
	@Transient
	private String compName;

	/**角色*/
	@Transient
	private String roles;
	
	/**系统Ids*/
	@Transient
	private String sysIds;
	
	/**多对一，cascade:级联; fetch:开启懒加载 FetchType.LAZY，如果使用急加载：FetchType.EAGER, 测试发现默认就是懒加载*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID", insertable = false, updatable = false)
	private GroupVO groupVO;
	
	/**一对多，cascade:级联; fetch:开启懒加载，如果使用急加载：FetchType.EAGER, 测试发现默认就是懒加载*/
	@OneToMany(mappedBy = "userId",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRoleVO> setUserRoleVO;
	
	public String getOnlineOrOff() {
		return onlineOrOff;
	}
	public void setOnlineOrOff(String onlineOrOff) {
		this.onlineOrOff = onlineOrOff;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public GroupVO getGroupVO() {
		return groupVO;
	}
	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}
	public Set<UserRoleVO> getSetUserRoleVO() {
		return setUserRoleVO;
	}
	public void setSetUserRoleVO(Set<UserRoleVO> setUserRoleVO) {
		this.setUserRoleVO = setUserRoleVO;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getSysIds() {
		return sysIds;
	}
	public void setSysIds(String sysIds) {
		this.sysIds = sysIds;
	}
	public String getUseOrNo() {
		return useOrNo;
	}
	public void setUseOrNo(String useOrNo) {
		this.useOrNo = useOrNo;
	}

	
}
