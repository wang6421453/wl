package com.wl.getUserInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private Connection connection =null;

	/**
	 * 连接数据库
	 * @return Statement  
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
   public Connection getConnection(int onlineOrOff) throws ClassNotFoundException, SQLException{
	    //加载驱动
	    Class.forName("com.mysql.jdbc.Driver");
	    //得到连接
        if(onlineOrOff == 0){
        	String url = "jdbc:mysql://10.66.0.121:3306/obpm";
    	    connection = DriverManager.getConnection(url, "root", "root");
	    }else{
	    	String url = "jdbc:mysql://10.66.0.99:3306/obpm";
    	    connection = DriverManager.getConnection(url, "root", "Mysql_123");
	    }
	   //上面是采用jdbc连接，性能不好，且换了地址后需要改代码或xml，下面采用数据源的方法连接
	   //创建一个上下文环境
	   /*try{
		   Context conn = new javax.naming.InitialContext();
           //通过conn得到数据源
		   DataSource ds = (DataSource)conn.lookup("java:comp/env/CTDataSource");
		   connection = ds.getConnection();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }*/
	   
	    return connection;
   }
   
}
