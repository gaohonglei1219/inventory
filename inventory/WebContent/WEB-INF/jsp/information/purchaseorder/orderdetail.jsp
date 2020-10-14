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
          <h1 style="color: #2679b5;">采购订单管理<small><i class="icon-double-angle-right"></i> 订单信息</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>purchaseOrder/${getMethod}">
			
            <div class="control-group" <c:if test="${getMethod=='updateById'}"> style="display:none" </c:if>>
              <label class="control-label" for="form-field-1">订单编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "orderId" value="${pd.orderId}" >
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="form-field-1">订单名称</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="orderName" value="${pd.orderName}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">前置仓库</label>
              <div class="controls">
              <select id="chooseFw" name="orderFrontwarehouseId">
              </select>
              </div>
            </div>
            <div class="control-group">
            <label class="control-label" for="form-field-1">供应商</label>
            <div class="controls">
            <select id="chooseSup" name="orderSupplierId">
             </select>
             </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="form-field-1">支付类型</label>
            <div class="controls">
            <select id="choosePay" name="orderPayTypeId">
             </select>
             </div>
          </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">联系电话</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="orderPhone" value="${pd.orderPhone}">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">备注</label>
              <div class="controls" id = "typeChoose">
                <textarea rows="" cols="" name="orderRemarks"></textarea>
              </div>
            </div>
		<div class="control-group" >
		<button class="btn btn-primary saved" style="margin-left:18%" >
		<c:choose>
		<c:when test="${getMethod=='insert'}">
			下一步
		</c:when>
		<c:otherwise>
			保存
		</c:otherwise>
		</c:choose>
		</button>
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

   		 /* 拉取前置仓信息 */
   		 $.ajax({
   			 url : '<%=basePath%>purchaseOrder/getAllFw',
   			 success : function(data){
   				 $('#chooseFw').html("<option value='1'>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var userType = ${pd.orderFrontwarehouseId!=none?pd.orderFrontwarehouseId:"'no'"}
   					 if(userType==v.fwId){
   						$('#chooseFw').append("<option value='"+v.fwId+"'selected>"+v.fwName+"</option>")
   					 }else{
   						$('#chooseFw').append("<option value='"+v.fwId+"'>"+v.fwName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		 
   		 /* 拉取供应商信息 */
   		 $.ajax({
   			 url : '<%=basePath%>supplier/getAllSupIdName',
   			 success : function(data){
   				 $('#chooseSup').html("<option value=''>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var userType = "${pd.orderSupplierId!=none?pd.orderSupplierId:'no'}"
   					 if(userType==v.id){
   						$('#chooseSup').append("<option value='"+v.id+"'selected>"+v.name+"</option>")
   					 }else{
   						$('#chooseSup').append("<option value='"+v.id+"'>"+v.name+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		 
   		 /* 拉取支付类型信息 */
   		 $.ajax({
   			 url : '<%=basePath%>purchaseOrder/getPayType',
   			 success : function(data){
   				 $('#choosePay').html("<option value=''>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var userType = ${pd.orderPayTypeId!=none?pd.orderPayTypeId:"'no'"}
   					 if(userType==v.payId){
   						$('#choosePay').append("<option value='"+v.payId+"'selected>"+v.payName+"</option>")
   					 }else{
   						$('#choosePay').append("<option value='"+v.payId+"'>"+v.payName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
	})





    </script>
    </body>
</html>
