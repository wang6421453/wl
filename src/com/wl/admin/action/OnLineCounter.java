package com.wl.admin.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * author 王磊
 * 统计在线人数
 */

public class OnLineCounter {
	//当前在线人数
	private static int onLineNum = 0;
	
	//在线session ip
	private static Map<String, String> sessionLst = new HashMap<String, String>();
	
	//获取当前在线人数
	public static int getOnLineNum(){
		//发现会出现0甚至小于0的情况，只能先治下标
		if(onLineNum > 0){
			return onLineNum;
		}else{
			return 1;
		}
	}
	
	//当前在线人数加1
	public static void addOnLineNum(){
		onLineNum++;
	}
	
	//当前在线人数减1
	public static void reduce(){
		if(onLineNum > 0){
			onLineNum--;
		}
	}
	
	//将用户Ip加入到列表中
	public static void addToSessionLst(String sessionId, String ip){
		sessionLst.put(sessionId, ip);
	}
	
	//删掉离开的session
	public static void reducefromSessionLst(String sessionId){
		sessionLst.remove(sessionId);
	}
	
	//获取所有在线ip
	public static List<String> getIpList(){
		List<String> ipList = new ArrayList<String>(sessionLst.values());
		return ipList;
	}
	
	//根据session获取ip
	public static String getIpBySessionId(String SessionId){
		return sessionLst.get(SessionId);
	}

}
