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


  </style>
    <body>

    <div id="main-content" class="clearfix">
      <div id="page-content" class="clearfix">
        <div class="page-header position-relative">
          <h1 style="color: #2679b5;">供应商管理<small><i class="icon-double-angle-right"></i> 供应商录入</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>supplier/${getMethod}">

            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "id" value="${pd.id}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商名称</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="name" value="${pd.name}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商三证合一代码</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="uscc" value="${pd.uscc}">
              </div>
            </div>
            <div class="control-group">
            <label class="control-label" for="form-field-1">供应商类型</label>
            <div class="controls">
				<select name="typeId" id="chooseType">
				</select>
            </div>
          </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商法人</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="legalPerson" value="${pd.legalPerson}">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商联系电话</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="phone" value="${pd.phone}">
              </div>
            </div>


            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商微信号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="weChat" value="${pd.weChat}">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">供应商QQ号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="qq" value="${pd.qq}">
              </div>
            </div>

            <div class="control-group">
			  <label class="control-label" for="form-field-1">供应商电子邮箱</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="email" value="${pd.email}">
              </div>
			</div>
         
            <div class="control-group">
				<label class="control-label" for="form-field-1">供应商地址</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="address" value="${pd.address}">
              </div>
			</div>

		<div class="control-group" >
		<button class="btn btn-primary saved" style="margin-left:18%" >保存</button>
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
    	  
    	  
   	  /* 拉取供应商类型信息 */
   		 $.ajax({
   			 url : '<%=basePath%>supplierType/queryAllType',
   			 success : function(data){
   				 $('#chooseType').html('');
   				 $.each(data,function(i,v){
   					 var userType = ${pd!=none?pd.typeId:"'no'"}  
   					 if(userType==v.typeId){
   						$('#chooseType').append("<option value='"+v.typeId+"'selected>"+v.typeName+"</option>")
   					 }else{
   						$('#chooseType').append("<option value='"+v.typeId+"'>"+v.typeName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
	})





    </script>
    </body>
</html>
