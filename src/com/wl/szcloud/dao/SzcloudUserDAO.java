package com.wl.szcloud.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.wl.szcloud.model.SzcloudUserVO;
import com.wl.szcloud.utils.HibernateUtil;



public class SzcloudUserDAO {
	private static Logger logger = Logger.getLogger(SzcloudUserDAO.class);
	
	private Session session = null;
	
	public SzcloudUserDAO(int onOrOffLine) throws Exception{
		if(onOrOffLine == 0){
			session = HibernateUtil.getSessionFactoryOnline().openSession();
		}else if(onOrOffLine == 1){
			session = HibernateUtil.getSessionFactoryOffline().openSession();
		}else{
			logger.error("SzcloudUserDAO:未指定连接哪个环境！");
			throw new Exception("未指定连接哪个环境！");
		}
	}
    /**
     * 获取用户数量
     * @param userVO 用户VO
     * @return int 用户数量
     */
	public int getUserCount(SzcloudUserVO userVO){
		String userName = userVO.getUserName();
		String compName = userVO.getCompName();
		String compCode = userVO.getCompCode();
		String cardNo = userVO.getCardNo();
		int count = 0;
		String hql = "select count(*) from SzcloudUserVO u where 1=1 ";
		if(!"".equals(userName)){
			hql += (" and u.userName like '%" + userName +"%'");
        }
		if(!"".equals(compName)){
			hql += (" and u.groupVO.groupName like '%" + compName + "%'");
		}
		if(!"".equals(compCode)){
			hql += (" and u.groupVO.orgCode like '%" + compCode + "%'");
		}
		
		if(!"".equals(cardNo)){
			hql += (" and u.cardNo = '" + cardNo + "'");
		}
		Query query = session.createQuery(hql);
		count =  ((Long)query.uniqueResult()).intValue();
		return count;
	}
	
	/**
	 * 获取用户列表
	 * @param userVO 用户VO
	 * @return List<UserVO> 用户VO list
	 */
	@SuppressWarnings("unchecked")
	public List<SzcloudUserVO> getUserList(SzcloudUserVO szcloudUserVO){
 		String userName = szcloudUserVO.getUserName();
		String compName = szcloudUserVO.getCompName();
		String compCode = szcloudUserVO.getCompCode();
		String cardNo = szcloudUserVO.getCardNo();
		int pageNo = szcloudUserVO.getPageNo();
		int pageSize = szcloudUserVO.getPageSize();
		//String hql = "from SzcloudUserVO u,GroupVO g, UserRoleVO ur, RoleInfoVO ri where u.groupId = g.groupId and u.id=ur.userId and ur.roleId = ri.roleId ";
		String hql = "from SzcloudUserVO u where 1=1 ";
		if(!"".equals(userName)){
			hql += (" and u.userName like '%" + userName +"%'");
        }
		if(!"".equals(compName)){
			hql += (" and u.groupVO.groupName like '%" + compName + "%'");
		}
		if(!"".equals(compCode)){
			hql += (" and u.groupVO.orgCode like '%" + compCode + "%'");
		}
		
		if(!"".equals(cardNo)){
			hql += (" and u.cardNo = '" + cardNo + "'");
		}
		List<SzcloudUserVO> lstSzcloudUserVO = new ArrayList<SzcloudUserVO>();
		Query query = session.createQuery(hql).setFirstResult(
				(pageNo-1) * pageSize).setMaxResults(pageSize);
		lstSzcloudUserVO =  query.list();
		/*List<SzcloudUserVO> lstUserVO = new ArrayList<SzcloudUserVO>();
		SzcloudUserVO tempUser = new SzcloudUserVO();
		for(Object[] obj : lstObject){
			SzcloudUserVO userVO = (SzcloudUserVO) obj[0];
			userVO.setCompCode(((GroupVO) obj[1]).getOrgCode());
			userVO.setCompName(((GroupVO) obj[1]).getGroupName());
			
			lstUserVO.add(userVO);
		}*/
		return lstSzcloudUserVO;
	}
	
	
	/**
	 * 修改手机号
	 * @param userVO
	 * @return int 是否成功修改
	 */
	public int changePhoneNo(SzcloudUserVO userVO){
		int result = 0;
		String id = userVO.getId();
		String phoneNo = userVO.getPhoneNo();
		if("".equals(id) || "".equals(phoneNo)){
			return result;
		}
		Transaction ts = session.beginTransaction();
		userVO = (SzcloudUserVO) session.load(SzcloudUserVO.class, id);
		userVO.setPhoneNo(phoneNo);
		ts.commit();
		return 1;
	}
	
	/**
	 * 获取手机号
	 * @param userVO
	 * @return Sting 手机号
	 */
	public String getUserPhoneNo(SzcloudUserVO userVO){
		String result = "";
		String id = userVO.getId();
		if(id == null || "".equals(id)){
			return result;
		}
		//Transaction ts = session.beginTransaction();
		userVO = (SzcloudUserVO) session.load(SzcloudUserVO.class, id);
		result = userVO.getPhoneNo();
		//ts.commit();
		return result;
	}
	
	/**
	 * 关闭session
	 * 
	 */
	public void closeSession(){
		if(session != null && session.isOpen()){
			session.close();
			session = null;
		}
	}
	
}
