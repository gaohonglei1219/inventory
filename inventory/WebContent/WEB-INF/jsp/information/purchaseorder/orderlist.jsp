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
    <!-- <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" href="static/css/font-awesome.min.css">
    <link rel="stylesheet" href="static/css/ace.min.css">
    <link rel="stylesheet" href="static/css/ace-skins.min.css">
    <link rel="stylesheet" href="static/css/ace-responsive.min.css"> -->
  <style>
    body,html{
      width: 100%;
      height: 100%;
      background: #fff;
    }

    .info div{
        float: right;
    }
    td th{ text-overflow:ellipsis; white-space:nowrap; overflow:hidden; }
	#main-content{
		margin-left: 0!important
	}
  </style>
    <body>

    <div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">

    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">供应管理<small><i class="icon-double-angle-right"></i>订单管理</small></h1>
    </div>

    <div class="tabbable" style="z-index:2;position:absolute">
      <ul class="nav nav-tabs" id="myTab">
        <li class="<c:if test="${pageType=='build'}">active</c:if>"><a data-toggle="tab" id = "buildTab"  href="#build"> 已创建订单</a></li>
        <li class="<c:if test="${pageType=='send'}">active</c:if>"><a data-toggle="tab" id = "sendTab"  href="#send">已发布订单</a></li>
        <li class="<c:if test="${pageType=='manage'}">active</c:if>"><a data-toggle="tab" id = "manageTab"  href="#manage">可维护订单</a></li>
      </ul>
      <div class="tab-content">
        <div id="build" class="tab-pane <c:if test="${pageType=='build'}">active</c:if>" style="overflow:scroll;">
		<form class="form-search" action="<%=basePath%>purchaseOrder/querylist?runState=1" id="buildTabForm">
      供应商:  <input type="text" name="supName" class="input-medium search-query">
      总价区间:  <input type="text" name="minPrice" class="input-medium search-query">元--<input type="text" name="maxPrice" class="input-medium search-query">元
     <button  class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
    </form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover" style="min-width:1500px;">
      <thead>
        <tr>
          <th class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </th>
          <th>编号</th>
          <th>名称</th>
          <th>创建时间</th>
          <th>前置仓库</th>
          <th>供应商</th>
          <th>单品种数</th>
          <th>单品总量</th>
          <th>总金额</th>
          <th>联系电话</th>
          <th>备注</th>
          <th>运行状态</th>
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
         		<td>${order.orderCreateTime}</td>
         		<td>${order.fwName}</td>
         		<td>${order.supName}</td>
         		<td>${order.orderItemCount}</td>
         		<td>${order.orderTotalNumber}</td>
         		<td>${order.orderTotalPrice}</td>
         		<td>${order.orderPhone}</td>
         		<td>${order.orderRemarks}</td>
         		<td>${order.runStateName}</td>
         		<td>${order.payTypeName}</td>
         		<td>
            <div class="inline position-relative" >
              <button class="btn btn-mini btn-info" onclick='adu_fun("purchaseOrder","update","${order.orderId}")'><i class="icon-edit"></i></button>
              <button class="btn btn-mini btn-success" onclick='adu_fun("purchaseOrder","send","${order.orderId}")'><i class="icon-share"></i></button>
              <button class="btn btn-mini btn-danger" onclick='adu_fun("purchaseOrder","del","${order.orderId}")'><i class="icon-trash"></i></button>
            </div>
          </td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
		 <button class="btn btn-primary" onclick='adu_fun("purchaseOrder","add","")'>新增</button>
		</div>
        <div id="send" class="tab-pane <c:if test="${pageType=='send'}">active</c:if>">
		<form class="form-search" action="<%=basePath%>purchaseOrder/querylist?runState=2" id="sendTabForm">
      发货时间：从<input type="date" name="puBeginTime" class="input-medium search-query">到<input type="date" name="puEndTime" class="input-medium search-query">
      交货时间：从<input type="date" name="deBeginTime" class="input-medium search-query">到<input type="date" name="deEndTime" class="input-medium search-query"><br>
      供应商:  <input type="text" name="supName" class="input-medium search-query">
    总价区间:  从<input type="text" name="minPrice" class="input-medium search-query">到<input type="text" name="maxPrice" class="input-medium search-query">
     <button  class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
    </form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover" style="min-width:1500px;">
      <thead>
        <tr>
          <th class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </th>
          <th>编号</th>
          <th>名称</th>
          <th>创建时间</th>
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
		<c:forEach items="${sendList}" var="order">
			 <tr>
			 	<td><label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label></td>
         		<td>${order.orderId}</td>
         		<td>${order.orderName}</td>
         		<td>${order.orderCreateTime}</td>
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
              <button class="btn btn-mini btn-info" onclick='javaScript:window.location.href="<%=basePath%>purchaseOrderItem/querylist?orderId=${order.orderId}"'><i class="icon-edit"></i></button>
              <button class="btn btn-mini btn-success" onclick='javaScript:window.location.href="<%=basePath%>purchaseOrder/toManagelist?orderId=${order.orderId}"'><i class="icon-share"></i></button>
            </div>
          </td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
        </div>
        <div id="manage" class="tab-pane <c:if test="${pageType=='manage'}">active</c:if>">
        <form class="form-search" id="manageTabForm" action="<%=basePath%>singleState/querylist?runState=4,5" method="post">
		    发货时间：<input type="date" name="puBeginTime" class="input-medium search-query">-<input type="date" name="puEndTime" class="input-medium search-query">
      交货时间：<input type="date" name="deBeginTime" class="input-medium search-query">-<input type="date" name="deEndTime" class="input-medium search-query"><br>
      供应商:  <input type="text" name="supName" class="input-medium search-query">
    总价区间:  <input type="date" name="minPrice" class="input-medium search-query">-<input type="date" name="maxPrice" class="input-medium search-query">
		     <button  class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
             <table id="table_bug_report" class="table table-striped table-bordered table-hover" style="min-width:1500px;">
      <thead>
        <tr>
          <th class="center">
            <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
          </th>
          <th>编号</th>
          <th>名称</th>
          <th>创建时间</th>
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
		<c:forEach items="${manageList}" var="order">
			 <tr>
			 	<td><label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label></td>
         		<td>${order.orderId}</td>
         		<td>${order.orderName}</td>
         		<td>${order.orderCreateTime}</td>
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
              <button class="btn btn-mini btn-info" onclick='javaScript:window.location.href="<%=basePath%>purchaseOrder/toManagelist?orderId=${order.orderId}"'><i class="icon-edit"></i></button>
            </div>
          </td>
        	</tr>
		</c:forEach>

       </tbody>
    </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
        </div>
      </div>
  </div>
  </div>
  </div>















   <!--  <script src="static/js/jquery-1.9.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>-->
    <script src="<%=basePath%>static/js/common.js"></script>



    <script type="text/javascript">
     	
      $(function(){
    	   
    	  $(top.hangge());	
    	 /*  切换列表页面触发查询 */
    	 $('#buildTab').click(function(){
    		 window.location.href = '<%=basePath%>purchaseOrder/querylist?runState=1'
    	 })
    	 $('#sendTab').click(function(){
    		 window.location.href = '<%=basePath%>purchaseOrder/querylist?runState=2'
    	 })
    	 $('#manageTab').click(function(){
    		 window.location.href = '<%=basePath%>purchaseOrder/querylist?runState=4,5'
    	 })
    
    	
    	 
    	    
    	  $('.ace-checkbox-2').each(function(){
              $('.ace-checkbox-2').change(function(){
                if($(this).prop('checked')){
                 $(this).parents('.tab-pane').find('.input').prop('checked',true);
               }else{
                $(this).parents('.tab-pane').find('.input').prop('checked',false);
              }
          })

        })

	})
    </script>
    </body>
</html>
