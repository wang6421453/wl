<%
/* 获取网址webRoot */
String webRoot = request.getContextPath();
if(webRoot.equals("/")) {
	webRoot = "";
}
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+webRoot+"/";

/*  获取当前用户  */
Object userName = request.getSession().getAttribute("myUser");
String path = request.getRequestURI();
String url = path.substring(webRoot.length());
if(userName == null && !"/login.jsp".equals(url)){
	response.sendRedirect(webRoot + "/login.jsp");
}
%>


<script>
   webRoot = "<%=webRoot%>";
   userName = "<%=userName%>";
</script>