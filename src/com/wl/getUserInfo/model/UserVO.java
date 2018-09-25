package com.wl.getUserInfo.model;

import com.wl.common.model.BaseVO;


public class UserVO extends BaseVO{
	/**环境，是否线上*/
	private String onlineOrOff;
	/**用户id*/
	private String id;
	/**用户名*/
	private String userName;
	/**公司名*/
	private String compName;
	/**密码*/
	private String password;
	/**组织机构代码*/
	private String compCode;
	/**身份证号*/
	private String cardNo;
	/**电话号*/
	private String phoneNo;
	/**是否启用*/
	private String useOrNo;
	/**角色*/
	private String role;
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
	public String getUseOrNo() {
		return useOrNo;
	}
	public void setUseOrNo(String useOrNo) {
		this.useOrNo = useOrNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
}
