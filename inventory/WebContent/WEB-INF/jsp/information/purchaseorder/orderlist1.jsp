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
	#form1 input,select,button{
		margin:2px 2px
	}

  </style>
    <body>
<div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">


    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">供应管理<small><i class="icon-double-angle-right"></i> 采购订单管理</small></h1>
    </div>
    <div style="overflow:scroll;">
    <form class="form-search" action="<%=basePath%>purchaseOrder/querylist" id="form1">
      发货时间：<input type="date" name="puBeginTime" class="input-medium search-query">-<input type="date" name="puEndTime" class="input-medium search-query">
      交货时间：<input type="date" name="deBeginTime" class="input-medium search-query">-<input type="date" name="deEndTime" class="input-medium search-query"><br>
      供应商:  <input type="text" name="supName" class="input-medium search-query">
     支付状态: <select name="payState" id="payState"></select>
    流转状态:  <select name="runState" id="runState"></select><br>
    总价区间:  <input type="date" name="minPrice" class="input-medium search-query">-<input type="date" name="maxPrice" class="input-medium search-query">
     <button  class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
    </form>
    <table id="table_bug_report" class="table table-striped table-bordered table-hover" style="min-width:1500px;">
      <thead>
        <tr>
          <th class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </th>
          <th>编号</th>
          <th>名称</th>
          <th>发布时间</th>
          <th>交货时间</th>
          <th>前置仓库</th>
          <th>供应商</th>
          <th>单品种数</th>
          <th>单品总量</th>
          <th>总金额</th>
          <th>发布人员</th>
          <th>接收人员</th>
          <th>联系电话</th>
          <th>备注</th>
          <th>运行状态</th>
          <th>支付状态</th>
          <th>支付类型</th>
          <th>操作</th>
        </tr>
      </thead>

      <tbody>
		<c:forEach items="${list}" var="order">
			 <tr>
			 	<td><label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label></td>
         		<td>${order.orderId}</td>
         		<td>${order.orderName}</td>
         		<td>${order.orderPurchasingTime}</td>
         		<td>${order.orderDeliveryTime}</td>
         		<td>${order.fwName}</td>
         		<td>${order.supName}</td>
         		<td>${order.orderItemCount}</td>
         		<td>${order.orderTotalNumber}</td>
         		<td>${order.orderTotalPrice}</td>
         		<td>${order.purchaseName}</td>
         		<td>${order.receiveName}</td>
         		<td>${order.orderPhone}</td>
         		<td>${order.orderRemarks}</td>
         		<td>${order.runStateName}</td>
         		<td>${order.payStateName}</td>
         		<td>${order.payTypeName}</td>
         		<td>
            <div class="inline position-relative" >
              <button class="btn btn-mini btn-info" onclick='adu_fun("purchaseOrder","update","${order.orderId}")'><i class="icon-edit"></i></button>
              <button class="btn btn-mini btn-danger" onclick='adu_fun("purchaseOrder","del","${order.orderId}")'><i class="icon-trash"></i></button>
            </div>
          </td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
	<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
	<button class="btn btn-primary" onclick='adu_fun("purchaseOrder","add","")'>新增</button>
	</div>
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
        
	})



    </script>
    </body>
</html>
