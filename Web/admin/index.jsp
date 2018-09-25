<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"  language="java" %>
<%@ page import="com.wl.admin.action.OnLineCounter" %>
<%@ page import="com.wl.admin.action.TotalVisterCounter" %>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8"/> 
<html ng-app="searchApp">
<head>
<title>委系统人员信息查询</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=webRoot%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=webRoot%>/css/wl.css?ver=1">
</head>

<body>
	<div ng-controller="searchCtrl">
	 <div class="litter_helper_admin"><a style="color:blue;" href="<%=webRoot%>/helper/helperIndex.jsp" target="_blank">小助手</a></div>
	 <div class="others_admin"><a style="color:blue;" href="<%=webRoot%>/others/othersIndex.jsp" target="_blank">其他</a></div>
	    <!-- 查询框开始 -->
		<div class="both_center">
			<form name="searchForm" novalidate>
				<div class="form-group">
					<div class="btn-group btn-group-lg">
						<button id="onlineEnv" type="button"
							class="btn btn-default active" ng-click="changeEnv(0)">线上环境</button>
						<button id="offlineEnv" type="button" class="btn btn-default"
							ng-click="changeEnv(1)">测试环境</button>
					</div>
				</div>
				<div class="form-group"><span ng-bind="databaseUrl"><font style="color:red">您的浏览器版本太低，建议使用Chrome浏览器</font></span></div>
				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="searchInfo.userName"
							placeholder="请输入用户名..." />
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="searchInfo.compName"
							placeholder="请输入公司名称..." />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="searchInfo.compCode"
							placeholder="请输入组织机构代码..." />
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<input class="form-control" ng-model="searchInfo.cardNo"
							placeholder="请输入身份证号..." />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">&nbsp;</div>
				</div>

				<div class="form-group">
					<div class="col-md-6 col-md-offset-1">
						<button type="submit" class="btn btn-lg btn-success btn-block"
							ng-click="getUserList()">获取信息</button>
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

		<!-- 结果展示列表开始 -->
		<div id="resultList" class="result_list">
			<table class="table table-hover">
				<tr>
					<th>用户名</th>
					<th>组织机构代码</th>
					<th>身份证号</th>
					<th>密码</th>
					<th>手机号</th>
					<th>是否启用</th>
					<th>角色</th>
					<th>所属公司</th>
				</tr>
				<tr ng-repeat="userVO in lstUserVO">
					<td>{{userVO.userName}}</td>
					<td>{{userVO.compCode}}</td>
					<td>{{userVO.cardNo}}</td>
					<td>{{userVO.password}}</td>
					<td ng-dblclick="editPhoneNo($index)" style="width:150px">
					   <div id="input{{$index}}" class="hide_input" style="height:10px;margin-top:-5px">
					      <div  class="input-group input-group-sm">
					          <input class="form-control" ng-model="userVO.phoneNo" ng-blur="phoneNoBlur($index)" ng-keypress="enterPhone($index, $event)"/>
					          <span class="input-group-btn">
					             <!-- 按钮按下同时也会触发失去焦点事件，所以就去掉了ng-click事件，不然会重复 -->
					             <button class="btn btn-default" type="button"><span style="color:lime;font-weight: 800;">√</span></button>
					          </span>
					      </div>
					    </div>
					    <span id="text{{$index}}" ng-bind="userVO.phoneNo"/></td>
					<td>{{userVO.useOrNo}}</td>
					<td>{{userVO.role}}</td>
					<td>{{userVO.compName}}</td>
				</tr>
			</table>
			<div>
				<nav>
					<ul class="pager">
			            <span style="float: left;">总共：<span style="color: blue;">{{totalCount}}</span> 条</span>
						<li id="prePage"><a href="#" ng-click="prePage()">上一页</a></li>
						<li id="nextPage"><a href="#" ng-click="nextPage()">下一页</a></li>
						<span style="float: right;">第  <span style="color: blue;">{{searchInfo.pageNo}}</span> 页 &nbsp;&nbsp; 共<span style="color: blue;">{{pageCount}}</span>页 </span>
					</ul>
				</nav>
			</div>
		</div>
		<!-- 结果展示列表结束 -->
		
		<!-- 统计信息 -->
		<div class="statistics_info">
		    <span>当前在线人数：<a href="ipList.jsp" style="color: blue;"><%=OnLineCounter.getOnLineNum() %></a></span>
		    <span>&nbsp;总访问量：<span style="color: blue;"><%=TotalVisterCounter.getTotalVisterNum() %></span></span>
		</div>
		
	</div>
</body>

<script src="<%=webRoot%>/js/angular.js"></script>
<script src="<%=webRoot%>/js/jquery-1.8.2.js"></script>
<script src="<%=webRoot%>/js/my/wl.js?ver=1"></script>
<script src="<%=webRoot%>/js/my/util.js?ver=1"></script>
</html>