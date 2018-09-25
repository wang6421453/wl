package com.wl.szcloud.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wl.szcloud.dao.SzcloudUserDAO;
import com.wl.szcloud.model.SzcloudUserVO;
import com.wl.szcloud.model.UserRoleVO;
import com.wl.szcloud.utils.Security;




public class SzcloudUserServer {
    private SzcloudUserDAO dao;
	
	public SzcloudUserServer(int onlineOrOff) throws Exception{
		dao = new SzcloudUserDAO(onlineOrOff);
	}
	
	/**
	 * 获取用户列表
	 * @param userVO 用户VO
	 * @return List<UserVO> 用户VO list
	 */
	public List<SzcloudUserVO> getUserList(SzcloudUserVO userVO){
		List<SzcloudUserVO> lstUserVO = dao.getUserList(userVO);
		//解析密码,hibernate查询结果lst里指向的对象可能是同一个，所以set一个密码的时候可能可能同时修改了lst中的其他对象，再次解析下个对象的密码的时候就是出现null值情况(之前使用join时的情况)
		for(SzcloudUserVO szcloudUserVO : lstUserVO){
				szcloudUserVO.setPassword(Security.decryptPassword(szcloudUserVO.getPassword()));
				Set<UserRoleVO> userRole = szcloudUserVO.getSetUserRoleVO();
				Set<String> roles = new HashSet<String>();
				Set<String> sysIds = new HashSet<String>();
				for(Iterator<UserRoleVO> it = userRole.iterator(); it.hasNext();){
					UserRoleVO userRoleVO = it.next();
					roles.add(userRoleVO.getRoleInfoVO().getRoleName());
					sysIds.add(userRoleVO.getRoleInfoVO().getSysId());
				}
				szcloudUserVO.setRoles(roles.toString());
				szcloudUserVO.setSysIds(sysIds.toString());
				szcloudUserVO.setUseOrNo("1".equals(szcloudUserVO.getUseOrNo()) ? "是" : "否");
		}
		
		/*int count = lstUserVO.size();
		for(int i =0; i < count; i++){
			lstUserVO.get(i).setPassword(Security.decryptPassword(lstUserVO.get(i).getPassword()));
		}*/
		return lstUserVO;
	}
	
	/**
     * 获取用户数量
     * @param userVO 用户VO
     * @return int 用户数量
     */
	public int getUserCount(SzcloudUserVO userVO){
		return dao.getUserCount(userVO);
	}
	
	/**
	 * 修改手机号
	 * @param userVO
	 * @return int 是否成功修改
	 */
	public int changePhoneNo(SzcloudUserVO userVO){
		return dao.changePhoneNo(userVO);
	}
	
	/**
	 * 获取手机号
	 * @param userVO
	 * @return String 手机号
	 */
	public String getUserPhoneNo(SzcloudUserVO userVO){
		return dao.getUserPhoneNo(userVO);
	}
	
	/**
	 * 关闭session
	 */
	public void closeSession(){
		dao.closeSession();
	}

}
