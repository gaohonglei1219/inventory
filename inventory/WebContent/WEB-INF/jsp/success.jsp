<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 17-7-21
  Time: 上午10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">

    <!-- jsp文件头和头部 -->
    <%@ include file="system/admin/top.jsp"%>

    <meta charset="utf-8">
    <title>Form Elements - Ace Admin</title>
    <meta name="description" content="Common form elements and layouts">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- basic styles -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="css/jquery-ui-1.10.2.custom.min.css">
    <link rel="stylesheet" href="css/chosen.css">
    <link rel="stylesheet" href="css/datepicker.css">
    <link rel="stylesheet" href="css/bootstrap-timepicker.css">
    <link rel="stylesheet" href="css/daterangepicker.css">
    <link rel="stylesheet" href="css/colorpicker.css">
    <!-- ace styles -->
    <link rel="stylesheet" href="css/ace.min.css">
    <link rel="stylesheet" href="css/ace-responsive.min.css">
    <link rel="stylesheet" href="css/ace-skins.min.css">
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="css/ace-ie.min.css" />
    <![endif]-->
<!--5秒钟后跳转到指定的页面-->
<meta http-equiv="refresh" content="5;url=http://localhost:8080/admin/order/findOrders?manId=1&status=0" />
</head>

<body>


<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>

<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<!-- 引入 -->



    <div class="page-header position-relative">
        <h1 class="header smaller lighter blue">Successful!</h1>
        <!--/page-header-->
    </div>

<div class="row-fluid wizard-actions">
    <button class="btn btn-prev" id="prev1"><i class="icon-arrow-left"></i> Prev</button>


</div>

</div>
</body>
<script type="text/javascript">

   // $(top.hangge());
   
//prev按钮返回原来页面
    $("#prev1").click(function(){
        window.location.href="<%=basePath%>404.jsp";
    });
</script>
</html>
