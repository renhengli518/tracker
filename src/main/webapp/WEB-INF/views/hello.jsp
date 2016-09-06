<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>文件上传下载</title>

</head>

<body>

	<div align="center">
			<form action="readUploadDocFile" method="post"
				enctype="multipart/form-data">
				<input type="file" name="file" /> <input type="submit" value="提交" />
			</form>
			<a href="/static/excelTemplate/testExcelImport.xls">下载excel模板</a>
			<a href="/downLoadFileServlet?url=F:\workspace\itfintracker\src\main\webapp\static\excelTemplate\testExcelImport.xls">下载excel模板</a>
	</div>
</body>

</html>