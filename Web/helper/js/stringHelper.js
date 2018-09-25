/**
 * 字符小助手
 * author wl
 * data 2014-11-23
 */

var stringHelperApp = angular.module('stringHelperApp', []);
	var stringHelperCtrl = function($scope) {
		
		$scope.webRoot = webRoot;
		$scope.stringHelper = {
				sourceString : '',
				resultString : '',
				doType : '0',
				beforeText : '',
				afterText : ''
		};
		
		$scope.editNum = 1;
		$scope.doIt = function(){
			$.post($scope.webRoot + "/stringHelper/doForString.ac", $scope.stringHelper,
					function(result) {
						$scope.stringHelper.resultString = result;
						$scope.refresh();
					});
		};
		
		$scope.refresh = function(){
			if (!$scope.$$phase) {
				$scope.$apply();
			}
		};
	};
stringHelperApp.controller("stringHelperCtrl", stringHelperCtrl);