<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"  language="java" %>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8"/> 
<html ng-app="loginApp">
<head>
<title>委系统人员信息查询</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=webRoot%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=webRoot%>/css/wl.css">
</head>

<body>
	<div ng-controller="loginCtrl">
	 <div class="news" style="margin-left:-70px">提示：结合ip进行校验，有需要向管理员申请账号</div>
	 <!-- <div id="news" class="news">注：小助手中可以找回被修改的手机号</div> -->
	    <!-- 登陆 -->
		<div class="both_center">
			<form name="loginForm" novalidate>
				<div class="form-group"><span ng-bind="databaseUrl"><font style="color:red">您的浏览器版本太低，建议使用Chrome浏览器</font></span></div>
				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="loginInfo.userName"
							placeholder="请输入用户名..." />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">&nbsp;</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="loginInfo.password"
							placeholder="请输入密码..." type="password"/>
					</div>
				</div>

                <div class="form-group">
					<div class="col-md-12">&nbsp;</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6 col-md-offset-1">
						<button type="submit" class="btn btn-lg btn-success btn-block"
							ng-click="login()">登陆</button>
					</div>
					<div class="col-md-4">
						<button type="reset" class="btn btn-lg btn-primary btn-block"
							ng-click="reset()">清 空</button>
					</div>
				</div>
			</form>
		</div>
        <!-- 查询框结束 -->
        
        <!-- 提示信息 -->
		<div class="tip_info">
			<span id="tipInfo"></span>
		</div>

		
	</div>
</body>

<script src="<%=webRoot%>/js/angular.js"></script>
<script src="<%=webRoot%>/js/jquery-1.8.2.js"></script>
<script>
var loginApp = angular.module('loginApp', []);
var loginCtrl = function($scope) {
	
	$scope.webRoot = webRoot;
	
	$scope.loginInfo = {
			userName : '',
			password : ''
	};
	//登陆
	$scope.login = function(){
		$.post($scope.webRoot + "/login.ac", $scope.loginInfo,
			function(result) {
				if(result.status == "success"){
					window.location.href = "szcloud/index.jsp";
				}else{
					$("#tipInfo").html("<font color='red'>登陆失败！</font>");
				}
			});
	}
	
	/**
	 * 重置
	 */
	$scope.reset = function() {
		$scope.loginInfo = {
				userName : '',
				password : ''
		};
		
		$("#tipInfo").html("");
	}
};
loginApp.controller("loginCtrl", loginCtrl);
</script>
</html>