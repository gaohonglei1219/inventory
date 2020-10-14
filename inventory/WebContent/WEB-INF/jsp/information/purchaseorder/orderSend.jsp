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
        <link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/chosen.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
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
          <h1 style="color: #2679b5;">采购订单管理<small><i class="icon-double-angle-right"></i> 发布订单</small></h1>
        </div>

          <form class="form-horizontal" id="sendForm" action="<%=basePath%>purchaseOrder/${getMethod}">
			<input name="runState" value="2" style="display:none">
            <div class="control-group" <c:if test="${getMethod=='updateById'}"> style="display:none" </c:if>>
              <label class="control-label" for="form-field-1">订单编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "orderId" value="${pd.id}" >
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="form-field-1">发布时间</label>
              <div class="controls">
                <input type="text" id="orderPurchasingTime" name="orderPurchasingTime" readonly>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="form-field-1">交货时间</label>
              <div class="controls">
                <input  type="date" id="orderDeliveryTime" name="orderDeliveryTime">
              </div>
            </div>
		<div class="control-group" >
		<input type="button" class="btn btn-primary saved" style="margin-left:18%" value="发布" id="send">
		<input type="button" class="btn" onclick="javascript:window.history.go(-1)" value="取消">
        </div>
        </form>
        
      </div>

    </div>
    <script src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>static/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/js/bootbox.min.js"></script>
    <!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='<%=basePath%>static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
		<script src="<%=basePath%>static/js/ace-elements.min.js"></script>
		<script src="<%=basePath%>static/js/ace.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
		<script type="text/javascript" src="<%=basePath%>static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
    <script>
    Date.prototype.format = function(fmt) { 
        var o = { 
           "M+" : this.getMonth()+1,                 //月份 
           "d+" : this.getDate(),                    //日 
           "h+" : this.getHours(),                   //小时 
           "m+" : this.getMinutes(),                 //分 
           "s+" : this.getSeconds(),                 //秒 
           "q+" : Math.floor((this.getMonth()+3)/3),  
           "S"  : this.getMilliseconds()             //毫秒 
       }; 
       if(/(y+)/.test(fmt)) {
               fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
       }
        for(var k in o) {
           if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
       return fmt; 
   }
      $(function(){
    	  $(top.hangge());
    	  //设置发布时间为当前时间
		  $('#orderPurchasingTime').val(new Date().format('yyyy-MM-dd'))
		  $('#send').click(function(){
			  var getDate = new Date(Date.parse($('#orderDeliveryTime').val().replace(/-/g,   "/")));
			  var now = new Date();
			  var diff = (getDate-now) / (1000 * 60 * 60 * 24);
			  if(diff>=1){
				  $('#sendForm').submit()
			  }else{
				  alert("接收日期至少在发布日期一天以后");
			  }
		  })
		  
		//日期框
			$('.date-picker').datepicker();
   		 
	})





    </script>
    </body>
</html>
