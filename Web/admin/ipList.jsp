<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"  language="java" %>
<%@ page import="com.wl.admin.action.OnLineCounter" %>
<meta charset="UTF-8"/> 
<html>
<head>
<title>委系统人员信息查询系统当前在线人员</title>

</head>

<body onload="init()">
   <div style="position:absolute;left:40%;top:30%;line-height:25px;width:303px;height:302px;overflow:hidden"">
      <div id = "top" style="width:4000px; border-top:dashed 2px #DADDE1;position:relative;left:-400px;"></div>
	  <div id = "left" style="width:1px;height:4000px; border-left:dashed 2px #DADDE1 ;position:relative;"></div>
	  <div style="position:absolute;top:30px;left:30px">
		  <%=OnLineCounter.getIpList().toString()%>
	  </div>
	  <div id = "right" style="width:1px;height:4000px; border-right:dashed 2px #DADDE1;position:absolute;top:-400px;left:300px"></div>
	  <div id = "bottom" style="width:4000px; border-bottom:dashed 2px #DADDE1 ;position:absolute;top:300px;"></div>	  
   </div>
</body>

<script>
  //初始化
  function init(){
	  borderMove();
  }
  //提示信息边框移动
  function borderMove(){
     var top_Left = Number(document.getElementById("top").style.left.replace("px",""));
     var left_top = Number(document.getElementById("left").style.top.replace("px",""));
	 var right_top = Number(document.getElementById("right").style.top.replace("px",""));
	 var bottom_left = Number(document.getElementById("bottom").style.left.replace("px",""));
     //document.getElementById("right").style.left="900px";
	 
	 setInterval(function(){
	   top_Left += 2;
	   left_top -= 2;
	   right_top += 2;
	   bottom_left -= 2;
	   if(top_Left > 0){
	      top_Left = -400;
	   }
	   if(left_top < -400){
	      left_top = 0;
	   }
	   if(right_top > 0){
	      right_top = -400;
	   }
	   if(bottom_left < -400){
	      bottom_left = 0;
	   }
	   document.getElementById("top").style.left = top_Left + "px";
	   document.getElementById("left").style.top = left_top + "px";
	   document.getElementById("right").style.top = right_top + "px";
	   document.getElementById("bottom").style.left = bottom_left + "px";
	 },60);
  }
  </script>
</html>