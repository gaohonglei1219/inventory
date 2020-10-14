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


  </style>
    <body>
<div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">


    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">订单管理<small><i class="icon-double-angle-right"></i> 采购订单详情</small></h1>
    </div>
    <form class="form-search" action="<%=basePath%>purchaseOrder/querylist" id="form1">
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
		  <th>操作</th>
        </tr>
      </thead>

      <tbody>
		<c:forEach items="${list}" var="order">
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
         		<td>${order.orderStateName}</td>
         		<td>
            <div class="inline position-relative" >
              <button class="btn btn-mini btn-info" onclick='adu_fun("purchaseOrderItem","update","${order.orderItemId}")'><i class="icon-edit"></i></button>
              <button class="btn btn-mini btn-danger" onclick='adu_fun("purchaseOrderItem","del","${order.orderItemId}")'><i class="icon-trash"></i></button>
            </div>
          </td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
	<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
	<button class="btn btn-primary" onclick='adu_fun("purchaseOrderItem","add","${pd.orderId}")'>新增</button>
	<button class="btn btn-primary save" id="allFinsh">完成</button>
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
        
        $('#allFinsh').click(function(){
        	window.location.href = "<%=basePath%>purchaseOrder/finshInsert"
        })
        
	})



    </script>
    </body>
</html>
