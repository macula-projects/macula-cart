<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>系统错误</title>
	</head>
	<body>
		程序发生了错误，有可能该页面正在调试或者是设计上的缺陷.
		<br /> 你可以选择
		<br />
		<a href="mailto:admin@infinitus.com.cn">反馈</a>提醒我，或者
		<br />
		<a href="javascript:history.go(-1)">返回上一页</a>
		<br /> 错误信息：<%=org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(exception)%>
	</body>
</html>