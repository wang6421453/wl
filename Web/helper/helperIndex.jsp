<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8" />
<html ng-app="helperApp">
<head>
  <title>小助手</title>
  <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
 <div ng-controller="helperCtrl">
	  <div class="form-group" style="margin-top:200px">
	       <div class="col-md-2 col-md-offset-3">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="history()">历史记录</button>
		   </div>
		   <div class="col-md-2">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="stringHelper()">字符处理</button>
		   </div>
		   <div class="col-md-2">
				<button type="button" class="btn btn-lg btn-primary btn-block"
					ng-click="fileCompare()">简易文件对比</button>
		   </div>
	  </div>
	  <div class="form-group">
		   <div class="col-md-12">&nbsp;</div>
	  </div>
	  <div class="form-group">
	     <div class="col-md-2 col-md-offset-4">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="clearDBCache()">清数据库缓存</button>
		  </div>
	      <div class="col-md-2">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="clearTemplateCache()">清模板缓存</button>
		   </div>
	  </div>
	  <div class="form-group">
			<div class="col-md-12">&nbsp;</div>
	  </div>
	  <div class="form-group">
	     <div class="col-md-2 col-md-offset-5">
				<button type="button" class="btn btn-lg btn-success btn-block"
					ng-click="downAutoGetTickets()">超算自动接单机器人</button>
		  </div>
	  </div>
  </div>
</body>
<script src="../js/angular.js"></script>
<script src="../js/jquery-1.8.2.js"></script>
<script>
var helperApp = angular.module('helperApp', []);
var helperCtrl = function($scope) {
	
	$(document).ready(function(){
		$.post(webRoot + "/helperMain/visitHelper.ac");
	});
	
	$scope.history = function(){
		window.open('history.html');
		$.post(webRoot + "/helperMain/visitHistory.ac");
	}
	
	$scope.stringHelper = function(){
		window.open('stringHelper.jsp');
		$.post(webRoot + "/helperMain/visitStringHelper.ac");
	}
	
	$scope.fileCompare = function(){
		window.open('fileCompare.jsp');
		$.post(webRoot + "/helperMain/visitFileCompare.ac");
	}
	
	$scope.clearDBCache = function(){
		$.post(webRoot + "/helperMain/visitClearDBCache.ac", function(result) {
			alert(result);
		});
	}
	
	$scope.clearTemplateCache = function(){
		$.post(webRoot + "/helperMain/visitClearTemplateCache.ac", function(result) {
			alert(result);
		});
		//28需要登录才能清
		//window.open("http://10.86.8.28:8080/szcloud/cache/clearTC.do");
		//采用前台方式（无法获取返回值）
		/* var servers = ["10.86.8.17:8088","10.86.8.18:8088","10.86.8.19:8088","10.86.8.20:8088","10.86.8.21:8088","10.86.8.22:8088","10.86.8.23:8088","10.86.8.24:8088","10.86.8.28:8080/szcloud"];
		$.post(webRoot + "/helperMain/visitClearTemplateCache.ac");
		var serverCleared= "";
  
		//清理模板缓存缓存
	    for(var server in servers){
			$.ajax({
				url:"http://" + servers[server] + "/cache/clearTC.do",
				async:false,
				type:"GET",
				dataType:"JSONP",
				success:function(data){
					alert(servers[server]);
					serverCleared += servers[server];
					serverCleared += "</br>";
				}
			});
		} */
	
		/* for(var server in servers){
			$.getJSON("http://" + servers[server] + "/cache/clearTC.do?callback=?",
				function(data){
					alert(servers[server]);
					serverCleared += servers[server];
					serverCleared += "</br>";
				}
			);
		} */
		
		//setTimeout(function(){alert(serverCleared)}, 2000);
		//alert(serverCleared);
		//alert("清理完成");
	}
	
	$scope.downAutoGetTickets = function(){
		window.location ="raws/autoGetTickets.crx";
	}
};
helperApp.controller("helperCtrl", helperCtrl);
</script>
</html>