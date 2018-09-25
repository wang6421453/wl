package com.wl.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wl.common.model.MyUser;

public class LoadMyUserInfo {
	
	static private List<MyUser> lstMyUser = new ArrayList<MyUser>();
	static{
		//创建saxReader对象
		SAXReader reader = new SAXReader();
		
		try {
			//通过read方法读取一个文件，转换成document对象
			File file = new File(LoadMyUserInfo.class.getResource("/res/allowUsers.xml").toURI());
			Document document = reader.read(file.getCanonicalPath());
			//获取根节点元素对象
			Element root = document.getRootElement();
			
			//遍历所有
			List<Element> allows = root.elements();
			for(Element el : allows){
				MyUser myUser = new MyUser();
				myUser.setUserName(el.element("name").getText());
				myUser.setPassword(el.element("password").getText());
				myUser.setIp(el.element("ip").getText());
				myUser.setRealName(el.element("realName").getText());
				lstMyUser.add(myUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static List<MyUser> getMyUserList(){
		return lstMyUser;
	}
}
