<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/base.jsp" %>
<meta charset="UTF-8" />
<html ng-app="stringHelperApp">
<head>
<title>字符小助手</title>
<link rel="stylesheet" href="<%=webRoot%>/css/bootstrap.min.css">
<link rel="stylesheet" href="css/stringHelper.css">
</head>

<body>
	<div ng-controller="stringHelperCtrl">
		<div style="float: center">
			<form name="stringHelperForm" novalidate>
				<div class="form-group">
					<div class="col-md-6">
						<textarea class="form-control" id="sourceString" ng-model="stringHelper.sourceString" rows="30"></textarea>
					</div>
					<div class="col-md-6">
						<textarea class="form-control" id="resultString" ng-model="stringHelper.resultString" rows="30"></textarea>
					</div>
				</div>
				<div class="form-group">
				   <div style="margin-left:15px">
						<label class="radio-inline"> 
						   <input type="radio" name="doType" id="findSameValue" value="0" ng-model="stringHelper.doType" ng-checked="true"/>找重复值
						</label> 
						<label class="radio-inline"> 
						   <input type="radio" name="doType" id="findNoSameValue" value="1" ng-model="stringHelper.doType"/>找唯一值
						</label> 
						<label class="radio-inline"> 
						    <input type="radio" name="doType" id="addBeforeText" value="2" ng-model="stringHelper.doType"/>在每行前加
						         <input class="input_bootstap" ng-model="stringHelper.beforeText"/>
						</label>
						<label class="radio-inline"> 
						    <input type="radio" name="doType" id="addAfterText" value="3" ng-model="stringHelper.doType"/>在每行后加
						         <input class="input_bootstap" ng-model="stringHelper.afterText"/>
						</label>
						<label class="radio-inline"> 
						    <input type="radio" name="doType" id="cutSameValue" value="4" ng-model="stringHelper.doType"/>去重
						</label>
					</div>
				</div>
				
				<div class="form-group">
				   <div class="col-md-3 col-md-offset-3">
						<button type="submit" class="btn btn-lg btn-success btn-block"
							ng-click="doIt()">Do it</button>
				   </div>
				   <div class="col-md-2">
						<button type="reset" class="btn btn-lg btn-primary btn-block"
							ng-click="reset()">清 空</button>
				   </div>
				</div>
			</form>
			<div>
			   <span id="resultInfo"></span>
			</div>
		</div>
		
	</div>
</body>

<script src="<%=webRoot%>/js/angular.js"></script>
<script src="<%=webRoot%>/js/jquery-1.8.2.js"></script>
<script src="<%=webRoot%>/js/nicEdit.js"></script>
<script src="js/stringHelper.js"></script>
</html>