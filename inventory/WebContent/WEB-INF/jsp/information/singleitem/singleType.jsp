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
          <h1 style="color: #2679b5;">单品管理<small><i class="icon-double-angle-right"></i> 单品类型信息</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>singleType/${getMethod}">
			<c:if test="${getMethod=='updateById'}">
            <div class="control-group" style="display:none">
              <label class="control-label" for="form-field-1">类型编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "typeId" value="${pd.typeId}">
              </div>
            </div>
            </c:if>
            <div class="control-group">
              <label class="control-label" for="form-field-1">类型名</label>
              <div class="controls">
                <input type="text" id="form-field-2" name="typeName" value="${pd.typeName}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="typeHie">层级编号</label>
              <div class="controls">
                <input type="text" id="typeHie" name="typeHierarchy" value="${pd.typeHierarchy}" readonly>
              </div>
            </div>
            <div class="control-group">
            <label class="control-label" for="form-field-4">父类型</label>
            <div class="controls">
				<select id="parType" name="typeAscriptionId">
				</select>
            </div>
          </div>
            <div class="control-group">
              <label class="control-label" for="form-field-5">类型描述</label>
              <div class="controls">
                <input type="text" id="form-field-5" name="typeDescribe" value="${pd.typeDescribe}">
              </div>
            </div>

		<div class="control-group" >
		<button class="btn btn-primary saved" style="margin-left:18%" >保存</button>
		<input type="button" class="btn" onclick="javascript:window.history.go(-1)" value="取消">
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
    	  
    	  
   	  /* 拉取所有类型信息 */
   		 $.ajax({
   			 url : '<%=basePath%>singleType/getAllIdName',
   			 success : function(data){
   				 $('#parType').html('');
   				$('#parType').append("<option value='0' thc='no' >无</option>")
   				 $.each(data,function(i,v){
   					 var userType = ${pd.typeAscriptionId!=none?pd.typeAscriptionId:"no"}
   					 if(userType==v.typeId){
   						$('#parType').append("<option value='"+v.typeId+"' thc='"+v.typeHierarchy+"'  selected>"+v.typeName+"</option>")
   					 }else{
   						$('#parType').append("<option value='"+v.typeId+"' thc='"+v.typeHierarchy+"'  >"+v.typeName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		$("#parType").on("change",function(){
   			   thc = $("option:selected",this).attr('thc');
   			   if(thc=='no'){
   				$('#typeHie').val('0')
   			   }else{
   				$('#typeHie').val(parseInt(thc)+1+"")   
   			   }
   			   
   		}); 
   		
   		 
   		 
   		 
   		 
	})





    </script>
    </body>
</html>
