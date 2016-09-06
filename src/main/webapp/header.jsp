<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String contextPath = request.getContextPath();
	if (contextPath.equals("/"))
		request.getSession().setAttribute("root", "");
	else
		request.getSession().setAttribute("root", contextPath);
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/reset.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/resetUE.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workplace.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workflat.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/space.css" />
<link rel="stylesheet" type="text/css" href="${root}/uikit-2.3.1/css/uikit.gradient.min.css">
<link rel="stylesheet" type="text/css" href="${root}/uikit-2.3.1/css/addons/uikit.addons.min.css">
<!-- alert dialog -->
<link rel="stylesheet" href="${root}/static/art_dialog/css/ui-dialog.css">

