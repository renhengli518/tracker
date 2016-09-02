<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<table class="uk-table uk-table-hover uk-table-striped">
	<thead>
		<tr>
			<th>渠道名称</th>
			<th>渠道号</th>
			<th>日期</th>
			<th>流量（PV）</th>
			<th>流量（UV）</th>
			<th>单量</th>
			<th>转换率</th>
			<th>未跳出UV</th>
			<th>平均访问深度</th>
			<th>跳出率</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orgDatas}" var="data">
			<tr>
				<td>${data.mediaName}</td>
				<td>${data.unionlv1}</td>
				<td>${data.stringDate}</td>
				<td>${data.pv}</td>
				<td>${data.uv}</td>
				<td>${data.orderpv}</td>
				<td><c:if test="${data.pv==0}">0%</c:if><c:if test="${data.pv!=0}"><fmt:formatNumber type="percent" value="${data.orderpv/data.pv}"/></c:if></td>
				<td>${data.secondclickcount}</td>
				<td>${data.accessdepth}</td>
				<td><c:if test="${data.pv==0}">0%</c:if><c:if test="${data.pv!=0}"><fmt:formatNumber type="percent" value="${(data.uv-data.secondclickcount)/data.uv}"/></c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
