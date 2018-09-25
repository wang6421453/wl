package com.wl.getUserInfo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wl.getUserInfo.model.UserVO;
import com.wl.getUserInfo.utils.Security;



public class UserDAO extends BaseDAO{
	private static Logger logger = Logger.getLogger(UserDAO.class);
	private PreparedStatement ps;
	private ResultSet rset;
	
	public UserDAO(int onlineOrOff) throws ClassNotFoundException, SQLException {
		super(onlineOrOff);
	}

    /**
     * 获取用户数量
     * @param userVO 用户VO
     * @return int 用户数量
     */
	public int getUserCount(UserVO userVO){
		String userName = userVO.getUserName();
		String compName = userVO.getCompName();
		String compCode = userVO.getCompCode();
		String cardNo = userVO.getCardNo();
		int count = 0;
		String sql = "select count(1) from T_USER u LEFT JOIN T_USER_ROLE_SET rs ON u.id = rs.userid LEFT JOIN t_role r ON rs.roleid = r.id where 1=1 ";
		if("".equals(userName) && "".equals(compName) && "".equals(compCode) && "".equals(cardNo)){
			return count;
		}
		if(!"".equals(userName)){
        	sql += (" and u.NAME like '%" + userName +"%'");
        }
		if(!"".equals(compName)){
			Map<String, String> compMap = getCompCodeByCompName(compName);
			sql += (" and u.field3 = '" + compMap.get("compCodeByCompName") + "'");
		}
		if(!"".equals(compCode)){
		    sql += (" and u.field3 = '" + compCode + "'");
		}
		
		if(!"".equals(cardNo)){
			sql += (" and u.field2 = '" + cardNo + "'");
		}
		try{
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			count = resultSet.getInt(1);
		}catch(Exception e){
			logger.error(e);
		}finally{
			//如果数量为0就关闭资源，否则不关闭，让查询list后再关闭
			if(count == 0){
				closeConnection();
			}
		}
		return count;
	}
	
	/**
	 * 获取用户列表
	 * @param userVO 用户VO
	 * @return List<UserVO> 用户VO list
	 */
	public List<UserVO> getUserList(UserVO userVO){
		String userName = userVO.getUserName();
		String compName = userVO.getCompName();
		String compCode = userVO.getCompCode();
		String cardNo = userVO.getCardNo();
		String realCompName = "";
		int pageNo = userVO.getPageNo();
		int pageSize = userVO.getPageSize();
		List<UserVO> lstUserVO = new ArrayList<UserVO>();
		String sql = "select u.*,r.name as 'role' from T_USER u LEFT JOIN T_USER_ROLE_SET rs ON u.id = rs.userid LEFT JOIN t_role r ON rs.roleid = r.id where 1=1 ";
		if("".equals(userName) && "".equals(compName) && "".equals(compCode) && "".equals(cardNo)){
			return lstUserVO;
		}
		if(!"".equals(userName)){
        	sql += (" and u.NAME like '%" + userName +"%'");
        }
		if(!"".equals(compName)){
			Map<String, String> compMap = getCompCodeByCompName(compName);
			sql += (" and u.field3 = '" + compMap.get("compCodeByCompName") + "'");
			realCompName = compMap.get("realCompName");
		}
		if(!"".equals(compCode)){
		    sql += (" and u.field3 = '" + compCode + "'");
		}
		
		if(!"".equals(cardNo)){
			sql += (" and u.field2 = '" + cardNo + "'");
		}
		sql += " limit " + (pageNo-1)*pageSize + "," + pageSize;
		try{
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			lstUserVO = resultSetToUserVOList(resultSet, realCompName);
		}catch(Exception e){
			logger.error(e);
		}finally{
			//关闭资源
			closeConnection();
		}
		return lstUserVO;
	}
	
	private Map<String, String> getCompCodeByCompName(String compName){
		Map<String, String> compMap = new HashMap<String, String>();
		String sql = "select item_comp_code, item_comp_name from szstieos.tlk_企业基础信息  where item_comp_name like '%" + compName + "%' limit 1";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				compMap.put("compCodeByCompName", resultSet.getString(1));
				compMap.put("realCompName", resultSet.getString(2));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return compMap;
	}
	
	/**
	 * 修改手机号
	 * @param userVO
	 * @return int 是否成功修改
	 */
	public int changePhoneNo(UserVO userVO){
		int result = 0;
		String id = userVO.getId();
		String phoneNo = userVO.getPhoneNo();
		if("".equals(id) || "".equals(phoneNo)){
			return result;
		}
		String sql = "";
		if("expert".equals(userVO.getCompCode())){
			 sql = "update T_USER u set u.TELEPHONE = '" + phoneNo + "' where u.id = '" + id +"'";
		}else{
			 sql = "update T_USER u set u.field4 = '" + phoneNo + "' where u.id = '" + id +"'";
		}
		
		try{
			Statement statement = conn.createStatement();
			result = statement.executeUpdate(sql);
		}catch(Exception e){
			logger.error(e);
		}finally{
		    closeConnection();
		}
		return result;
	}
	
	/**
	 * 获取用户手机号
	 * @param userVO
	 * @return String 手机号
	 */
	public String getUserPhoneNo(UserVO userVO){
		
		String phoneNo = "";
		
		String id = userVO.getId();
		String compCode = userVO.getCompCode();
		
		if(id == null || "".equals(id)){
			return phoneNo;
		}
		String sql = "";
		if("expert".equals(compCode)){
			 sql = "select u.TELEPHONE from T_USER u where u.id = '" + id +"'";
		}else{
			 sql = "select u.field4 from T_USER u where u.id = '" + id +"'";
		}
		
		try{
			Statement statement = conn.createStatement();
			ResultSet resultSet =  statement.executeQuery(sql);
			if(resultSet.next()){
				phoneNo =resultSet.getString(1);
			}
			
		}catch(Exception e){
			logger.error(e);
		}
		return phoneNo;
	}
	
    private List<UserVO> resultSetToUserVOList(ResultSet resultSet, String realCompName) throws SQLException{
    	List<UserVO> lstUserVO = new ArrayList<UserVO>();
    	while(resultSet.next()){
    		UserVO userVO = new UserVO();
    		userVO.setId(resultSet.getString("id"));
    		userVO.setUserName(resultSet.getString("name"));  //用户名
    		userVO.setCompCode(resultSet.getString("field3")); //组织机构代码
    		userVO.setCardNo(resultSet.getString("field2")); //身份证号
			String password = resultSet.getString("loginpwd"); //密码
			password = Security.decryptPassword(password); //解析密码
			userVO.setPassword(password);
			//判断是否专家
			if("expert".equals(resultSet.getString("field3"))){
				userVO.setPhoneNo(resultSet.getString("TELEPHONE"));
			}else{
				userVO.setPhoneNo(resultSet.getString("field4")); //手机号
			}
			String status = resultSet.getString("status");
			userVO.setUseOrNo(("1".equals(status) ? "是" : "否")); //是否启用
			userVO.setRole(resultSet.getString("role")); //角色
			userVO.setCompName(realCompName);
			lstUserVO.add(userVO);
		}
    	return lstUserVO;
    }
	
	//关闭数据库连接
    private void closeConnection(){
	   try{
		   if(rset != null){
				rset.close();
                rset = null;				
		    }
		    if(ps != null){
		    	ps.close();
		    	ps = null;
		    }
		    if(conn != null){
		    	conn.close();
		    	conn = null;
		    }
		}catch(Exception e){
			logger.error(e);
		}
    }
}
