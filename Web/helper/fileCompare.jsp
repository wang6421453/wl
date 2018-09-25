<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8" />
<html>
<head>
<title>文件比对</title>
<link rel="stylesheet" href="<%=webRoot%>/css/bootstrap.min.css">
<link rel="stylesheet" href="css/stringHelper.css">
</head>

<body>
	<div>
		<div style="float: center">
			<form name="fileCompareForm" novalidate>
				<div class="form-group">
					<div class="col-md-6">
						<div contenteditable="true" class="form-control" id="string1"  style="height:600px;width:100%;border:1px solid #00ff00;overflow: auto;"></div>
					</div>
					<div class="col-md-6">
						<div contenteditable="true" class="form-control" id="string2"  style="height:600px;width:100%;border:1px solid #00ff00;overflow: auto;"></div>
					</div>
				</div>
				<div class="form-group">
				   <div class="col-md-3 col-md-offset-3" style="margin-top:20px">
						<button type="button" class="btn btn-lg btn-success btn-block"
							onclick="doIt()">Do it</button>
				   </div>
				   <div class="col-md-2" style="margin-top:20px">
						<button type="button" class="btn btn-lg btn-primary btn-block"
							onclick="clearEdit()">清 空</button>
				   </div>
				</div>
			</form>
		</div>
		
	</div>
</body>

<script src="<%=webRoot%>/js/jquery-1.8.2.js"></script>
<script>
	
	//var webRoot = "/wl";
	function doIt(){
		//ndinstance1.setContent("<div style='color:red'>dfdsa</div><div>dsafg</div>");
		var c1 = $("#string1").html(); 
		var c2 = $("#string2").html(); 
		//构造原始字符串使之满足<div>a</div><div>b</div>结构，原始的是a<div>b</div>
		var start = /<div/i;
		if(start.test(c1)){
			c1 = c1.replace(start,"</div><div");
			c1 = "<div>" + c1;
		}else{
			c1 = "<div>" + c1 + "</div>";
		}
		if(start.test(c2)){
			c2 = c2.replace(start,"</div><div");
			c2 = "<div>" + c2;
		}else{
			c2 = "<div>" + c2 + "</div>";
		}
		
		//去掉空标签
		var cut_blank_html = /<[^>^/]+><\/[^>]+>/gmi;
		c1 = c1.replace("<br>","&nbsp;").replace(cut_blank_html,"");
		c2 = c2.replace("<br>","&nbsp;").replace(cut_blank_html,"");
	    var get_div = /<[^>^/]+>[^<]*<\/[^>]+>/gmi;      //获得每个div及内容
	    var cut_html = /<[^>]+>/gmi;   //去除html标签
	    //var end_ptn = /<\/\w+>/gmi;            //过滤标签结束  
	    //var space_ptn = /(&nbsp;)*/;            //过滤空格  
	    //var c1 = c.replace(start_ptn,"").replace(end_ptn).replace(space_ptn,""); 
	    //c1 = c1.replace(start_ptn,"<div>").replace(end_ptn,"<div>");
	    //c2 = c2.replace(start_ptn,"");
	    //c = c.replace("</div>","");
	    //var c1 = c.split("<div>");
	    var arr1 = c1.match(get_div);
	    var arr2 = c2.match(get_div);
	    var result1 = "";
	    var result2 = "";
	    var len1 = 0, len2 = 0;
	    if(arr1 != null){
	    	len1 = arr1.length;
	    }
	    if(arr2 != null){
	    	len2 = arr2.length;
	    }
	    var maxLength = len1 >= len2 ? len1 : len2; 
	    for(var i = 0; i < maxLength; i++){
	    	if(i < len1){
	    		rowContent1 = arr1[i];
		    	rowContent1 = rowContent1.replace(cut_html,"");
	    	}else{
	    		rowContent1 = "";
	    	}
	    	if(i < len2){
	    		rowContent2 = arr2[i];
		    	rowContent2 = rowContent2.replace(cut_html,"");
	    	}else{
	    		rowContent2 = "";
	    	}
	    	
	    	if(rowContent1 != rowContent2){
	    		result1 += "<div style='color:red'>" + rowContent1 + "</div>";
	    		result2 += "<div style='color:red'>" + rowContent2 + "</div>";
	    	}else{
	    		result1 += "<div>" + rowContent1 + "</div>";
	    		result2 += "<div>" + rowContent2 + "</div>";
	    	}
	    	
	    }
	    $("#string1").html(result1);
	    $("#string2").html(result2);
	    
	    //window.setTimeout("doIt()", 2000);

	}
	
	function clearEdit(){
		 $("#string1").html("");
		 $("#string2").html("");
	}
</script>
</html>