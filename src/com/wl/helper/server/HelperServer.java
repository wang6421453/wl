package com.wl.helper.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wl.helper.utils.TelnetOperator;

public class HelperServer {

	/**
	 * 清数据库缓存（Telnet）
	 * @param ips
	 * @param ports
	 * @return
	 */
	public String clearDBCache(String[] ips, int[] ports){
		TelnetOperator telnet = new TelnetOperator("VT220", "\r\n"); //Windows,用VT220,否则会乱码 
		String result ="";
		for(int i = 0; i < ips.length; i++){
			telnet.login(ips[i], ports[i]);
			String rs = telnet.sendCommand("flush_all");
			try{
				rs = new String(rs.getBytes("ISO-8859-1"), "GBK");
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			if(rs != null){
				result += ips[i] + ":"+ rs;
			}
			telnet.close();
		}
		
		return result;
	}
	
	/**
	 * 清模板缓存
	 * @param servers
	 * @return
	 */
	public String clearTemplateCache(String[] servers){
		String result ="";
		try {
			for(String server : servers){
				URL url = new URL("http://" + server + "/cache/clearTC.do");
				HttpURLConnection connection = (HttpURLConnection)url.openConnection(); //此时connection只是为一个连接对象，待连接中
				try{
				   connection.connect(); 
				}catch(Exception e){
					result += server + " :清理失败！" + "\r\n";
					continue;
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line;
				StringBuilder sb = new StringBuilder();
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				br.close();
				connection.disconnect();
				if(sb.toString().contains("清理成功")){
					result += server + " :清理成功！" + "\r\n";
				}else{
					result += server + " :清理失败！" + "\r\n";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
