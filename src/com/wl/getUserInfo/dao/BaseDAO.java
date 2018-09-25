package com.wl.getUserInfo.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {
	protected Connection conn;
	
	public BaseDAO(int onlineOrOff) throws ClassNotFoundException, SQLException{
		ConnectDB connectDB = new ConnectDB();
		conn = connectDB.getConnection(onlineOrOff);
	}
	
	public Connection getConnection(){
		return conn;
	}

}
