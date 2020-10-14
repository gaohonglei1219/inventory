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
        <meta charset="utf-8">
        <base href="<%=basePath%>">
    </head>
    <!-- <link rel="stylesheet" href="richText/bootstrap-combined.no-icons.min.css"> -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <!-- <link rel="stylesheet" href="richText/index.css" type="text/css"> </link> -->
    <link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/ace.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/ace-skins.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/ace-responsive.min.css">
  <style>
    body,html{
      width: 100%;
      height: 100%;
      background: #fff;
    }
    #editor{
      height: 200px;
    }
	input,select,textarea{
	 color:black!important
	}

  </style>
    <body>

    <div id="main-content" class="clearfix">
      <div id="page-content" class="clearfix">
        <div class="page-header position-relative">
          <h1 style="color: #2679b5;">供应商管理<small><i class="icon-double-angle-right"></i> 供应商录入</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>supplierType/${getMethod}">
          
          	<div class="control-group">
              <label class="control-label" for="form-field-1">供应商类型编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="typeId" value="${pd.typeId}" readonly>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商类型名称</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="typeName" value="${pd.typeName}">
              </div>
            </div>

		<div class="control-group" >
		<button class="btn btn-primary saved" style="margin-left:18%">保存</button>
		<button class="btn" onclick="javascript:window.history.go(-1)">取消 </button>
        </div>
        </form>
        
      </div>

    </div>
    <script src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>static/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/js/bootbox.min.js"></script>
  <!--   <script src="richText/bootstrap-wysiwyg.js" type="text/javascript"></script>
    <script src="richText/jquery.hotkeys.js" type="text/javascript"></script> -->
    <script>
      $(function(){
    	  $(top.hangge());	
})





    </script>
    </body>
</html>
