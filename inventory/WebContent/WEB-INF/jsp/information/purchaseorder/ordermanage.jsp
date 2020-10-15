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

	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
    </head>
  <style>
    body,html{
      width: 100%;
      height: 100%;
      background: #fff;
    }
	input,select,textarea{
	 color:black!important
	}
	#form1 input,select{
		margin: 2px
	}
  </style>
    <body>
<div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">


    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">订单管理<small><i class="icon-double-angle-right"></i> 采购订单详情</small></h1>
    </div>
    <p><b>流转状态</b>:<select id="runState"></select></p>
    <p><b>支付状态</b>:<select id="payState"></select></p>
    <hr>
    <p>订单详情:</p>
    <form class="form-search" action="<%=basePath%>purchaseOrder/toManagelist" id="form1">
      单品名：<input type="text" name="singleName" class="input-medium search-query">
      单价区间：<input type="text" name="minSinglePrice" class="input-medium search-query">元--<input type="text" name="maxSinglePrice" class="input-medium search-query">元<br>
      总价区间: <input type="text" name="minTotalPrice" class="input-medium search-query">元--<input type="text" name="minTotalPrice" class="input-medium search-query">元
      采购状态:  <select name="charseState" id="charseState"></select>
     <button  class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
    </form>
    <table id="table_bug_report" class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </th>
          <th>编号</th>
          <th>订单编号</th>
          <th>单品名</th>
          <th>采购数量</th>
          <th>采购单价</th>
          <th>采购总价</th>
          <th>采购状态</th>
        </tr>
      </thead>

      <tbody>
		<c:forEach items="${orderList}" var="order">
			 <tr>
			 <td class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </td>
         		<td>${order.orderItemId}</td>
         		<td>${order.orderId}</td>
         		<td>${order.singleName}</td>
         		<td>${order.purchaseQuantity}</td>
         		<td>${order.purchaseUnitPrice}</td>
         		<td>${order.purchaseTotalPrice}</td>
         		<td>
         		<select name="orderState" id="${order.orderItemId}">
         			<c:forEach items="${allState}" var="state">
         				<option value="${state.orderStateId}"  <c:if test="${state.orderStateName==order.orderStateName}">selected</c:if>>${state.orderStateName}</option>
         			</c:forEach>
         		</select>
         		</td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
	<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
	<button class="btn btn-primary save" id="saveManage">提交</button>
  </div></div>
  <script src="<%=basePath%>static/js/common.js"></script>
    <script>
      $(function(){
    	  $(top.hangge());
        $('.ace-checkbox-2').change(function(){
          if($('.ace-checkbox-2').prop('checked')){
            $('.input').prop('checked',true);
          }else{
            $('.input').prop('checked',false);
          }
        })
        
        $('#saveManage').click(function(){
        	var stateJson = {};
        	$('select[name="orderState"]').each(function(){
        		var id = $(this).attr('id')
        		var state = $(this).children('option:selected').val()
        		stateJson[id] = state;
        	})
        	console.log(stateJson)
        	var sendJson = {};
        	sendJson['state'] = stateJson
        	sendJson['runState'] = $('#runState option:selected').val()
        	sendJson['payState'] = $('#payState option:selected').val()
        	sendJson['orderId'] = "${orderDetail.orderId}"
        	$.ajax({
        		url:'<%=basePath%>purchaseOrder/saveManage',
        		data:JSON.stringify(sendJson),
        		type:'post',
        		contentType:'application/json',
        		dataType:'json',
        		success:function(data){
        			alert('保存成功')
        			window.location.href="<%=basePath%>purchaseOrder/querylist?runState=4,5"
        		},
        		error:function(){
        			alert("保存失败")
        		}
        		
        	})
        	
        })
        
        //拉取所有状态
   		<%--  $.ajax({
   			 url : '<%=basePath%>purchaseOrderItem/getAllState',
   			 success : function(data){
   				 $('select[name="orderState"]').html("");
   				 $.each(data,function(i,v){
   					 
   					 var single = "${pd.stateId!=none?pd.stateId:'no'}"
   					 if(single==v.orderStateId){
   						$('#chooseSingle').append("<option value='"+v.orderStateId+"'selected>"+v.orderStateName+"</option>")
   					 }else{
   						$('#chooseSingle').append("<option value='"+v.orderStateId+"'>"+v.orderStateName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 }) --%>
   		 
   		 //拉取所有流转状态
   		 $.ajax({
   			 url : '<%=basePath%>purchaseOrder/getRunState',
   			 success : function(data){
   				 $('#runState').html("");
   				 var single = "${orderDetail.orderRunStateId!=none?orderDetail.orderRunStateId:'no'}"
   				 $.each(data,function(i,v){
   					 if(single==v.runStateId){
   						$('#runState').append("<option value='"+v.runStateId+"'selected>"+v.runStateName+"</option>")
   					 }else{
   						$('#runState').append("<option value='"+v.runStateId+"'>"+v.runStateName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		 
   		  //拉取所有支付状态
   		 $.ajax({
   			 url : '<%=basePath%>purchaseOrder/getPayState',
   			 success : function(data){
   				 $('#payState').html("");
   				 var single = "${orderDetail.orderPayStateId!=none?orderDetail.orderPayStateId:'no'}"
   				 $.each(data,function(i,v){
   					 if(single==v.payStateId){
   						$('#payState').append("<option value='"+v.payStateId+"'selected>"+v.payStateName+"</option>")
   					 }else{
   						$('#payState').append("<option value='"+v.payStateId+"'>"+v.payStateName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
        
        
	})



    </script>
    </body>
</html>
