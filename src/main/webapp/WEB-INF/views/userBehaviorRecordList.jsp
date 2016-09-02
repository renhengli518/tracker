<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="userBehaviorRecordListApp">
<head>
<%
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE, -1);
	String todayStr = df.format(calendar.getTime());
	calendar.add(Calendar.DATE, -7);
	String lastWeekTodayStr = df.format(calendar.getTime());
%>
<jsp:include page="${root}/header.jsp"></jsp:include> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户行为列表</title>

</head>

<body ng-controller="userBehaviorRecordListCtrl">
	<div id="inputUI">
		<hr style="width: 900px; margin-left: auto; margin-right: auto;">
		<fieldset class="uk-form" style="width: 800px; margin-left: auto; margin-right: auto;">
			<div class="uk-form-icon">
				<label class="uk-form-label" for="txt_reportDate">开始日期</label>
				<div class="uk-form-icon">
					<i class="uk-icon-calendar"></i><input id="date_start" type="text" value="<%=todayStr%>"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,maxDate:'%y-%M-%d'})" />
				</div>
			</div>
			<div class="uk-form-icon">
				<label class="uk-form-label" for="txt_referDate">结束日期</label>
				<div class="uk-form-icon">
					<i class="uk-icon-calendar"></i><input id="date_end" type="text" value="<%=todayStr%>"
						onFocus="WdatePicker({isShowClear:false,readOnly:true,maxDate:'%y-%M-%d'})" />
				</div>
			</div>
			<div class="uk-form-icon">
				<label class="uk-form-label" for="viewType">访问类型</label>
				<div class="uk-form-icon">
					<select ng-model="viewType" name="viewType" id="viewType">
						<option ng-value="item.code" ng-repeat="item in viewTypeList" ng-bind="item.name"></option>
					</select>
				</div>
			</div>
			<div class="uk-form-icon">
				<button class="uk-button uk-button-primary uk-button-large" ng-click="pageList()">查询</button>
			</div>
		</fieldset>
	</div>
	<div id="reportUI_orgData" style="width: auto; margin-left: auto; margin-right: auto;">
		<table class="uk-table uk-table-hover uk-table-striped">
			<thead>
				<tr>
					<th width="100px">按钮名称</th>
					<th width="100px">链接名称</th>
					<th width="100px">访问类型</th>
					<th width="100px">ip</th>
					<!-- <th width="100px">sessionID</th> -->
					<th width="100px">用户名</th>
					<th width="100px">触发时间</th>
					<th width="100px">新用户</th>
					<th width="100px">浏览器</th>
					<th width="100px">访问页面</th>
					<th width="100px">国家</th>
					<th width="100px">省份</th>
					<th width="100px">城市</th>
					<th width="100px">浏览时长</th>
					<th width="100px">页面标题</th>
					<th width="100px">前一页面</th>
					<th width="100px">系统</th>
					<th width="100px">分辨率</th>
					<th width="100px">页面类型</th>
					<th width="100px">访问来源</th>
					<th width="100px">搜索关键字</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="obj in userBehaviorRecordlist">
					<td width="100px" ng-bind="obj.buttonposition"></td>
					<td width="100px" ng-bind="obj.linkposition"></td>
					<td width="100px" ng-bind="obj.viewtype"></td>
					<td width="100px" ng-bind="obj.ip"></td>
					<!-- <td width="100px" ng-bind="obj.sessionid"></td> -->
					<td width="100px" ng-bind="obj.endUserid"></td>
					<td width="100px" ng-bind="obj.clienttime"></td>
					<td width="100px" ng-bind="obj.newuserflag"></td>
					<td width="100px" ng-bind="obj.userurgent"></td>
					<td width="100px" ng-bind="obj.pageurl"></td>
					<td width="100px" ng-bind="obj.country"></td>
					<td width="100px" ng-bind="obj.province"></td>
					<td width="100px" ng-bind="obj.city"></td>
					<td width="100px" ng-bind="obj.staytime"></td>
					<td width="100px" ng-bind="obj.pagetitle"></td>
					<td width="100px" ng-bind="obj.refferpage"></td>
					<td width="100px" ng-bind="obj.clientsystem"></td>
					<td width="100px" ng-bind="obj.clientresolution"></td>
					<td width="100px" ng-bind="obj.clientpageType"></td>
					<td width="100px" ng-bind="obj.fromwhere"></td>
					<td width="100px" ng-bind="obj.serachkeywords"></td>
				</tr>
			</tbody>
		</table>
		<div id="noData" align="center">
		
		</div>
		
		<div class="page_yema" id="pageNavi">
		
		</div>
	</div>

</body>
<jsp:include page="${root}/footer.jsp"></jsp:include> 
<script type="text/javascript">
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
					url : "${root}/userBehaviorRecord/list",
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
</script>
</html>