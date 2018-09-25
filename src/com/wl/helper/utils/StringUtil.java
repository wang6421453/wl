package com.wl.helper.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StringUtil {
	
    /*找出相同字符串
	 * @param strSource
	 * @return
	 */
	public static String findSameValue(String strSource){
		String result = "";
		String[] arrSource = strSource.split("\n");
		//数据总数
		int count = arrSource.length;
		//判断是否存在重复数据
		int isHaveSame = 0;
		//存放重复数据，防止到相同数据时进行重复比较
		List<String> lstSameData = new ArrayList<String>();
		//重复数据的序号
	    int index = 0;
		for (int i = 0; i < count; i++) {
			//先判断该数据是否为已经判断为重复的数据
			int isSameData = 0;
			for(String strSameData:lstSameData){
				if(strSameData.equals(arrSource[i])){
					isSameData = 1;
				}
			}
			if(isSameData == 1){
				continue;
			}
			//比较是否重复
			int j = i + 1;
			int sameCount = 0;
			while (j < count) {
				if (arrSource[i].equals(arrSource[j])) {
					isHaveSame++;
					sameCount++;
				}
				j++;
			}
			if (sameCount != 0) {
				lstSameData.add(arrSource[i]);
				index++;
				result += (index +". " + arrSource[i] + "\t共有：" + ++sameCount + "条\n");
			}
		}
		if (isHaveSame == 0) {
			result += "没有重复数据;";
		}
		
		return result;
	}
	
	/**
	 * 找出沒有重复的数据
	 * @param strSource
	 * @return
	 */
	public static String findUniqueValue(String strSource){
		String result = "";
		String[] arrSource = strSource.split("\n");
		//数据总数
		int count = arrSource.length;
		//判断是否有唯一值
		int hasUniqueValue = 0;
		for (int i = 0; i < count; i++) {
			//判断该值是否有唯一值
			int thisIsUnique = 1;
			for(int j =0; j < count; j++){
				if(i != j && arrSource[i].equals(arrSource[j])){
					thisIsUnique = 0;
					break;
				}
			}
			if(thisIsUnique == 1){
				result += arrSource[i] + "\n";
				hasUniqueValue = 1;
			}
		}
		if (hasUniqueValue == 0) {
			result += "没有唯一数据;";
		}
		
		return result;
	}
	
	/**
	 * 去重并保持顺序
	 * @param strSource
	 * @return
	 */
	public static String cutSameValue(String strSource){
		String result = "";
		String[] arrSource = strSource.split("\n");
		List<String> lstSource = Arrays.asList(arrSource);
		List<String> lstResult = new ArrayList<String>();
		Set<String> setResult = new HashSet<String>();
		for(String str : lstSource){
			if(setResult.add(str)){
				lstResult.add(str);
			}
		}
		Iterator<String> iterator = lstResult.iterator();
		while(iterator.hasNext()){
			result += iterator.next() + "\n";
		}
		return result;
	}

	/**
	 * 在每行前增加字符
	 * @param strSource
	 * @param beforeText
	 * @return
	 */
	public static String addBeforeText(String strSource, String beforeText){
		String result = "";
		result = strSource.replace("\n", "\n" + beforeText);
		result = beforeText + result;
		return result;
	}
	
	public static String addAfterText(String strSource, String afterText){
		String result = "";
		result = strSource.replace("\n", afterText + "\n");
		result += afterText;
		return result;
	}
	
}
