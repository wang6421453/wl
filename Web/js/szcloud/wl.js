/**
 * 用户信息查询控制器
 * author wl
 * data 2014-11-18
 */
var searchApp = angular.module('searchApp', []);
	var searchCtrl = function($scope) {
		//初始化查询信息声明
		$scope.searchInfo = {
			onlineOrOff : '0',
			id : '',
			userName : '',
			compName : '',
			compCode : '',
			cardNo : '',
			phoneNo : '',
			pageNo : 1,
			pageSize : 5
		};

		//初始化结果集
		$scope.totalCount = 0;
		$scope.pageCount = 0;
		$scope.lstUserVO = {};
		$scope.oldPhoneNo = "";
		$scope.webRoot = webRoot + "/szcloud";
		$scope.databaseUrl = "10.86.1.1";
		$scope.phoneNoChanged = false;
		//切换环境
		$scope.changeEnv = function(onlineOrOff) {
			$scope.searchInfo.onlineOrOff = onlineOrOff;
			if (onlineOrOff == 0) {
				$("#offlineEnv").removeClass("active");
				$("#onlineEnv").addClass("active");
				$scope.databaseUrl = "10.86.1.1";
			} else {
				$("#onlineEnv").removeClass("active");
				$("#offlineEnv").addClass("active");
				$scope.databaseUrl = "10.86.0.29/root/Zwy_123";
			}
			if($scope.searchInfo.userName != "" || $scope.searchInfo.compCode != "" || $scope.searchInfo.cardNo != "" || $scope.searchInfo.compName != ""){
			    $scope.ajaxGetUserInfo();
			}
		}

		//点击获取信息按钮
		$scope.getUserList = function(){
			if($scope.searchInfo.userName != "" || $scope.searchInfo.compCode != "" || $scope.searchInfo.cardNo != "" || $scope.searchInfo.compName != ""){
				$scope.searchInfo.pageNo = 1;
				$scope.ajaxGetUserInfo();
				//加入本地历史记录
				if(localStorage){
					 var history = localStorage.getItem("history");
					 var info = Util.getNowFormatDate() + "查询政务云:" + $scope.searchInfo.userName + "|" + $scope.searchInfo.compCode + "|" + 
					 $scope.searchInfo.cardNo + "|" + $scope.searchInfo.compName + "的信息;</br>";
					 //将信息加入到本地存储
				     localStorage.setItem("history", (!history ? "" : history) + info);
				}
			}else{
				$("#tipInfo").html("<font color='red'>请至少输入一项查询条件。</font>");
				setTimeout('$("#tipInfo").html("")',3000);
				//alert("请至少输入一项查询条件。");
			}
		}
		//通用的异步获取数据
		$scope.ajaxGetUserInfo = function() {
			$.post($scope.webRoot + "/userInfo/getUserInfo.ac", $scope.searchInfo,
					function(result) {
						$scope.totalCount = result.totalCount;
						$scope.pageCount = Math.ceil(result.totalCount/5);
						$scope.lstUserVO = result.lstUserVO;
						if (!$scope.$$phase) {
							$scope.$apply();
						}
						//因为是异步，防止pageCount值还没更新就执行所以把下面放里面来了
						$("#resultList").show();
						if($scope.searchInfo.pageNo == 1){
							$("#prePage").hide();
						}else{
							$("#prePage").show();
						}
						if($scope.searchInfo.pageNo == $scope.pageCount || $scope.totalCount == 0){
							$("#nextPage").hide();
						}else{
							$("#nextPage").show();
						}
						$(".hide_input").hide();
					});
		}
		
		//翻页(上一页)
		$scope.prePage = function(){
			$scope.searchInfo.pageNo--;
			$scope.ajaxGetUserInfo();
		}
		//翻页(下一页)
        $scope.nextPage = function(){
        	$scope.searchInfo.pageNo++;
			$scope.ajaxGetUserInfo();
		}
		
		/**
		 * 重置
		 */
		$scope.reset = function() {
			$scope.searchInfo = {
				onlineOrOff : '0',
				userName : '',
				compName : '',
				compCode : '',
				cardNo : '',
				pageNo : 1,
				pageSize : 5
			};
			$scope.lstUserVO = {};
			$("#resultList").hide();
			$("#offlineEnv").removeClass("active");
			$("#onlineEnv").addClass("active");
			$("#tipInfo").html("");
		}
		
		//双击修改电话号码
		$scope.editPhoneNo = function(index){
			$scope.oldPhoneNo = $scope.lstUserVO[index].phoneNo;
			$("#input" + index).show();
			$("#input" + index + " input").focus();
			$("#text" + index).hide();
		}
		
		//保存手机号
		$scope.savePhoneNo = function(index){
			if($scope.lstUserVO[index].phoneNo == $scope.oldPhoneNo){
				
			 }else if(/^1\d{10}$/.test($scope.lstUserVO[index].phoneNo)){
				    $scope.searchInfo.id = $scope.lstUserVO[index].id;
				    $scope.searchInfo.userName = $scope.lstUserVO[index].userName;
					$scope.searchInfo.phoneNo = $scope.lstUserVO[index].phoneNo;
					$scope.searchInfo.compCode = $scope.lstUserVO[index].groupVO.orgCode;
					$scope.ajaxSavePhoneNo();
					//加入本地历史记录
					if(localStorage){
						 var history = localStorage.getItem("history");
						 var info ="<span style='color:blue'>" + Util.getNowFormatDate() + '修改政务云:' + $scope.searchInfo.userName + "|" + $scope.searchInfo.compCode + "|" + 
						 $scope.lstUserVO[index].cardNo + "|" + $scope.lstUserVO[index].groupVO.groupName + "的手机号:" + $scope.oldPhoneNo + "为:" + $scope.searchInfo.phoneNo + ";</span></br>";
						 //将信息加入到本地存储
					     localStorage.setItem("history", (!history ? "" : history) + info);
					}
				}else{
					$("#tipInfo").html("<font color='red'>手机号不符合规则，未进行修改</font>");
					setTimeout('$("#tipInfo").html("")',3000);
					$scope.lstUserVO[index].phoneNo = $scope.oldPhoneNo;
				}
				$("#input" + index).hide();
				$("#text" + index).show();
		}
		
		//电话号失去输入焦点
		$scope.phoneNoBlur = function(index){
			$scope.savePhoneNo(index);
			//$scope.oldPhoneNo = "";  //不知为何会执行两次savePhoneNo，第二次这个空了，就会访问后台了
		}
		
		//输入电话回车
		$scope.enterPhone = function(index, ev){
			 if (ev.keyCode == 13){
				 $scope.savePhoneNo(index);
				 //$scope.oldPhoneNo = "";
			 }
		}
		
		//异步保存手机号
		$scope.ajaxSavePhoneNo = function(){
			$.post($scope.webRoot + "/userInfo/changePhoneNo.ac", $scope.searchInfo,
					function(result) {
				        $scope.ajaxGetUserInfo();
						if(result == 1){
							$("#tipInfo").html("<font color='lime'>修改成功</font>");
						}else{
							$("#tipInfo").html("<font color='red'>修改失败！</font>");
						}
						setTimeout('$("#tipInfo").html("")',3000);
					});
		}
	};
	searchApp.controller("searchCtrl", searchCtrl);
	
	$(function() {
		$("#resultList").hide();
		//动态变化最上面的提示信息
		setInterval(function(){
			var myDate = new Date();
			var second = myDate.getSeconds(); //获取当前秒数(0-59)
			var i = second % 3;
			switch(i){
			  case 0: $("#news").html("注：小助手中可以找回被修改的手机号"); break;
			  case 1: $("#news").html("注：双击手机号即可进行修改"); break;
			  case 2: $("#news").html("注：小助手中新增清数据库和清模板缓存功能");break;
			}
			/*if(second % 2 == 0){
				$("#news").html("注：小助手中可以找回被修改的手机号");
			}else{
				$("#news").html("注：双击手机号即可进行修改");
			}*/
		},5000);
		
		//设置ajax全局参数
	    $.ajaxSetup({  
			    contentType : "application/x-www-form-urlencoded;charset=utf-8",  
			    complete : function(xhr, textStatus) {  
			        //session timeout  
			        if (xhr.getResponseHeader("sessionStatus") == "timeout") {  
			            window.location = webRoot + "/login.jsp";//返回登陆页面  
			            return;
			        }
			    }  
			});
	})