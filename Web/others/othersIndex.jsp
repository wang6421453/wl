<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8" />
<html ng-app="othersApp">
<head>
  <title>其他</title>
  <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
 <div ng-controller="othersCtrl">
	  <div class="form-group" style="margin-top:200px">
		   <div class="col-md-2 col-md-offset-3">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="tankBattle()">坦克大战</button>
		   </div>
		   <div class="col-md-2">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="hanoi()">汉诺塔</button>
		   </div>
		   <div class="col-md-2">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="chatRoom()">聊天室</button>
		   </div>
	  </div>
	  <div class="form-group">
					<div class="col-md-12">&nbsp;</div>
	  </div>
	  <div class="form-group">
	      <div class="col-md-2 col-md-offset-5">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="download()">文档下载</button>
		   </div>
	  </div>
  </div>
</body>
<script src="../js/angular.js"></script>
<script src="../js/jquery-1.8.2.js"></script>
<script>
var othersApp = angular.module('othersApp', []);
var othersCtrl = function($scope) {
	
	$scope.webRoot = webRoot;
	//坦克大战
	$scope.tankBattle = function(){
		window.open('tankBattle.html');
	}
	//汉诺塔
	$scope.hanoi = function(){
		window.open('TowerOfHanoi.html');
	}
	//聊天室
	$scope.chatRoom = function(){
		window.open('chatRoom.html');
	}
	
	//文档下载
	$scope.download = function(){
		window.open("files.html");
	}
	
};
othersApp.controller("othersCtrl", othersCtrl);
</script>
</html>