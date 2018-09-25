package com.wl.helper.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.helper.model.StringHelperVO;
import com.wl.helper.utils.StringUtil;


@Controller
@RequestMapping("/stringHelper")
public class StringHelper {
	
	@RequestMapping("/doForString.ac")
	@ResponseBody
	public String doForString(@ModelAttribute StringHelperVO stringHelperVO){
		String result = "";
		int doType = stringHelperVO.getDoType();
		String strSource = stringHelperVO.getSourceString();
		String beforeText = stringHelperVO.getBeforeText();
		String afterText = stringHelperVO.getAfterText();
		switch(doType){
		   case 0 : result = StringUtil.findSameValue(strSource); break;
		   case 1 : result = StringUtil.findUniqueValue(strSource); break;
		   case 2 : result = StringUtil.addBeforeText(strSource, beforeText); break;
		   case 3 : result = StringUtil.addAfterText(strSource, afterText); break;
		   case 4 : result = StringUtil.cutSameValue(strSource); break;
		}
		return result;
	}
	
	
}
