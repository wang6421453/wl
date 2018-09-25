package com.wl.szcloud.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.wl.admin.service.ProjectPath;

public class HibernateUtil {
	
	private static SessionFactory sessionFactoryOnline = null;
	private static SessionFactory sessionFactoryOffline = null;
	
	static{
		//获取hibernate路径
		String hibePathOnline = ProjectPath.getHibePathOnline();
		String hibePathOffline = ProjectPath.getHibePathOffline();
		//使用注解的话需要new AnnotationConfiguration
		Configuration cfOnline=new AnnotationConfiguration().configure(hibePathOnline);
		Configuration cfOffline=new AnnotationConfiguration().configure(hibePathOffline);
		//创建会话工厂.
		sessionFactoryOnline=cfOnline.buildSessionFactory();
		sessionFactoryOffline=cfOffline.buildSessionFactory();
	}

	public static SessionFactory getSessionFactoryOnline() {
		return sessionFactoryOnline;
	}

	public static void setSessionFactoryOnline(SessionFactory sessionFactoryOnline) {
		HibernateUtil.sessionFactoryOnline = sessionFactoryOnline;
	}

	public static SessionFactory getSessionFactoryOffline() {
		return sessionFactoryOffline;
	}

	public static void setSessionFactoryOffline(SessionFactory sessionFactoryOffline) {
		HibernateUtil.sessionFactoryOffline = sessionFactoryOffline;
	}
	
	
}
