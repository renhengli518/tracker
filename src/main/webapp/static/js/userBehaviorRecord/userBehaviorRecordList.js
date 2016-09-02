var mySplit;
	var userBehaviorRecordListApp = angular.module('userBehaviorRecordListApp',[]);
	userBehaviorRecordListApp.controller('userBehaviorRecordListCtrl', ['$scope', '$http','$location', function ($scope, $http,$location) {
		$scope.userBehaviorRecordlist=[];
		$scope.viewTypeList = [{"code":"",
						        "name":"请选择"  
						       },
		                       {"code":"pageView",
		                    	"name":"浏览网页"  
		                       },{
		                    	  "code":"a_link",
			                      "name":"点击链接" 
		                       },{
		                    	  "code":"button_click",
			                      "name":"点击按钮" 
		                       }
		                       ];
		$scope.pageList = function () {
			$("#pageNavi").empty();
			$("#noData").empty();
			$scope.config = {
					node : $id("pageNavi"),
					url : "list",
					count : 10,
					data : {
						"date_start":$("#date_start").val(),
						"date_end":$("#date_end").val(),
						"viewType":$("#viewType").val()
					},
					callback : $scope.listInfosData,
					type : 'get'
				};

				mySplit = new SplitPage($scope.config);
			}
		
		$scope.listInfosData = function (data) {
			$scope.$apply(function(){
				var len = data.length;
				if (len > 0) {
					$scope.userBehaviorRecordlist = data;
					
				} else {
					$scope.userBehaviorRecordlist = [];
					$("#noData").text("没查到数据");
				}
			});
		}; 
		$scope.pageList();
        /* $http({
            method: 'GET',
            url: "/shopOrder/queryShopOrderList",
            params: $scope.pageParams
        }).success(function (data, header, config, status) {
           
        }).error(function (data, header, config, status) {
           
        }); */
        
	}]);

	userBehaviorRecordListApp.filter('contains', function() {
		return function(input, key) {
			var arr = [];
			arr = jQuery.parseJSON(input);
			var flag = false;
			var keys = key.split(",");
			OK: for (var i = 0; i < arr.length; i++) {
				for (var x = 0; x < keys.length; x++) {
					if (keys[x] == arr[i]) {
						flag = true;
						break OK;
					}
				}
			}
			return flag;
		};
	});