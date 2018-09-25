package com.wl.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * 读取txt文件
 * @author Administrator
 *
 */
public class TxtHelper{
	
	 public static String readTxtFile(String filePath){
		 StringBuffer strTxt = new StringBuffer();
		  try {
		        String encoding="UTF-8";
		        File file=new File(filePath);
		        if(file.isFile() && file.exists()){ //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(file),encoding);//考虑到编码格式
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String lineTxt = null;
		            while((lineTxt = bufferedReader.readLine()) != null){
		            	strTxt.append(lineTxt);
		            }
		            read.close();
		        }else{
		           System.out.println("找不到指定的文件");
		        }
		     } catch (Exception e) {
		        System.out.println("读取文件内容出错");
		        e.printStackTrace();
		     }
		   return strTxt.toString();
		}
	 
	 public static void writeTxtFile(String filePath, String text){
		 try{
			 File file=new File(filePath);
		     if(file.isFile() && file.exists()){ //判断文件是否存在
		    	 BufferedWriter output = new BufferedWriter(new FileWriter(file));
		    	 output.write(text);
		    	 output.close();
		     }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}

