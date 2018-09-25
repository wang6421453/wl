package com.wl.helper.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;


public class TelnetOperator {

	private InputStream in;
	private PrintStream out;
	private TelnetClient telnet;
	private String prompt = "\r\n"; //结束标识字符串,Windows中是>,Linux中是#  
    private char promptChar = '\n';   //结束标识字符  
	
    /** 
     * @param termtype  协议类型：VT100、VT52、VT220、VTNT、ANSI 
     * @param prompt    结果结束标识 
     */  
    public TelnetOperator(String termtype,String prompt){  
        telnet = new TelnetClient(termtype);  
        setPrompt(prompt);  
    }  
    
    public TelnetOperator(){  
        telnet = new TelnetClient();  
    }  
    
    public TelnetOperator(String termtype){  
        telnet = new TelnetClient(termtype);  
    }  
    
    
    /** 
     * 登录到目标主机 
     * @param ip 
     * @param port 
     * @param username 
     * @param password 
     */  
    public void login(String ip, int port, String userName, String password){  
       try {
		  telnet.connect(ip, port);
		  in = telnet.getInputStream();
		  out = new PrintStream(telnet.getOutputStream());
		  readUntil("login:");
		  write(userName);
		  readUntil("password:");
		  write(password);
		  String rs = readUntil(null);
		  if(rs != null && rs.contains("Login Failed")){
			  System.out.println("登陆失败");
		  }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
    }
    
    /** 
     * 登录到目标主机 
     * @param ip 
     * @param port 
     * @param username 
     * @param password 
     */  
    public void login(String ip, int port){  
       try {
		  telnet.connect(ip, port);
		  in = telnet.getInputStream();
		  out = new PrintStream(telnet.getOutputStream());
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
    }
       
    
    /** 
     * 发送命令,返回执行结果 
     *  
     * @param command 
     * @return 
     */  
    public String sendCommand(String command){
    	try{
    		write(command);
    		return readUntil(prompt);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /** 
     * 发送命令 
     *  
     * @param value 
     */  
    private void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 关闭连接 
     */ 
    public void close(){
    	try{
    		if(telnet != null && telnet.isConnected()){
    			telnet.disconnect();
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    
    //设置结束字符
    private void setPrompt(String prompt) {  
        if(prompt!=null){  
            this.prompt = prompt;  
            this.promptChar = prompt.charAt(prompt.length()-1);  
        }  
    }
    
    /** 
     * 读取分析结果 
     *  
     * @param pattern   匹配到该字符串时返回结果 
     * @return 
     */  
    private String readUntil(String pattern){
    	StringBuffer sb = new StringBuffer();
    	try{
    		char lastChar = (char)-1;
    		boolean flag = pattern != null && pattern.length() > 0;
    		if(flag){
    			lastChar = pattern.charAt(pattern.length() - 1);
    		}
    		char ch;
    		int code = -1;
    		while((code = in.read()) != -1){
    			ch = (char)code;
    			sb.append(ch);
    			
    			//匹配到结束标识时返回结果
    			if(flag){
    				if(ch == lastChar && sb.toString().endsWith(pattern)){
    					return sb.toString();
    				}
    			}else{
    				//如果没有指定结束标识，匹配到默认结束标识时返回结果
    				if(ch == promptChar){
    					return sb.toString();
    				}
    			}
    		}
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	return sb.toString();
    }
}
