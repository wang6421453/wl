package com.wl.admin.service;

public class ProjectPath {
	//项目路径(D:\JavaCode\works\GetUserInfo\Web\)
	private static String projectPathName ="";
	//网站跟目录(/wl)
	private static String webRoot ="";
	//hibernate配置路径--线上
	private static String hibePathOnline ="";
	//hibernate配置路径--开发
	private static String hibePathOffline ="";

	public static String getProjectPathName() {
		return projectPathName;
	}

	public static void setProjectPathName(String pathName) {
		projectPathName = pathName;
	}

	public static String getWebRoot() {
		return webRoot;
	}

	public static void setWebRoot(String webRoot) {
		ProjectPath.webRoot = webRoot;
	}

	public static String getHibePathOnline() {
		return hibePathOnline;
	}

	public static void setHibePathOnline(String hibePathOnline) {
		ProjectPath.hibePathOnline = hibePathOnline;
	}

	public static String getHibePathOffline() {
		return hibePathOffline;
	}

	public static void setHibePathOffline(String hibePathOffline) {
		ProjectPath.hibePathOffline = hibePathOffline;
	} 
	
}
