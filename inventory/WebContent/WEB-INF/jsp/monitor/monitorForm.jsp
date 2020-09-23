<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</head>
<body>
	<form action="<%=basePath%>monitor/saveMonitor" method="post">
		类名：<input name="className"/><br/>
		json字符串：<input name="jsonString"/><br/>
		<input value="提交" type="submit"/>
	</form>
	
	=====================================
	<input type="button" value="ajax测试" onclick="ajaxTest()"/>

</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
  $(function() {
	  alert("初始化完成");
  })
  function ajaxTest() {
	  $.ajax({
		 url : "<%=basePath%>monitor/findMonitorNew",
		 type: "post",
		 contentType: "application/json",
		 data: JSON.stringify({
			 "morId": 1
		 }),
		 success: function(data) {
			 alert(data);
		 }
	  });
  }
</script>
</html>