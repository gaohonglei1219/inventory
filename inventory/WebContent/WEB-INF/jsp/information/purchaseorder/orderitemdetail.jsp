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
	#main-content{
		margin-left: 0!important
	}
  </style>
    <body>

    <div id="main-content" class="clearfix">
      <div id="page-content" class="clearfix">
        <div class="page-header position-relative">
          <h1 style="color: #2679b5;">采购订单管理<small><i class="icon-double-angle-right"></i> 订单详情信息</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>purchaseOrderItem/${getMethod}">
			<c:if test="${getMethod=='updateById'}">
				<input name="id" value="${pd.orderItemId}" style="display:none">
			</c:if>
            <div class="control-group">
              <label class="control-label" for="form-field-1">订单编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "orderId" value="${pd.orderId}" readonly>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">单品名称</label>
              <div class="controls">
              <select name="singleItemId" id="chooseSingle">
              	
              </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">采购数量</label>
              <div class="controls">
                <input type="text" id="purchaseQuantity" name= "purchaseQuantity" value="${pd.purchaseQuantity}" >
              </div>
            </div>
            <div class="control-group">
            <label class="control-label" for="form-field-1">采购单价</label>
            <div class="controls">
                <input type="text" id="purchaseUnitPrice" name= "purchaseUnitPrice" value="${pd.purchaseUnitPrice}" >元
              </div>
          </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">采购总价</label>
              <div class="controls">
                <input type="text" id="purchaseTotalPrice" name="purchaseTotalPrice" value="${pd.purchaseTotalPrice}" readonly>元
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
    	  
   		 /* 拉取单品信息 */
   		 $.ajax({
   			 url : '<%=basePath%>singleItem/getAllIdName',
   			 success : function(data){
   				 $('#chooseSingle').html("<option value=''>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var single = "${pd.singleId!=none?pd.singleId:'no'}"
   					 if(single==v.id){
   						$('#chooseSingle').append("<option value='"+v.id+"'selected>"+v.name+"</option>")
   					 }else{
   						$('#chooseSingle').append("<option value='"+v.id+"'>"+v.name+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		 
   		 $('#purchaseUnitPrice').change(function(){
   			 var count = parseInt($('#purchaseQuantity').val())
   			 var price = parseInt($('#purchaseUnitPrice').val())
   			 $('#purchaseTotalPrice').val(count*price)
   		 })
   		 
   		
	})





    </script>
    </body>
</html>
