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

  </style>
    <body>

    <div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">

    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">供应管理<small><i class="icon-double-angle-right"></i> 供应商管理</small></h1>
    </div>

    <div class="tabbable" style="z-index:2;position:absolute">
      <ul class="nav nav-tabs" id="myTab">
        <li class="active"><a data-toggle="tab" href="#AwaitingPayment"> 供应商信息</a></li>
        <li class=""><a data-toggle="tab" href="#AwaitingShipment"> 供应商清单信息</a></li>
        <li class=""><a data-toggle="tab" href="#shipped">供应商类型信息</a></li>
        <li class=""><a data-toggle="tab" href="#complete">订单支付管理</a></li>
      </ul>
      <div class="tab-content">
        <div id="AwaitingPayment" class="tab-pane active">
		<form class="form-search" action="<%=basePath%>supplier/querySupBylist">
		  供应商名称：
		  <input type="text" name = "name" class="input-medium search-query" value = "${pd.name}">
		  供应商类型：
		  <select name="typeId">
		  	<option>请选择</option>
		  </select>
		  <button class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
            <th class="center">
              <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
            </th>
            <th>名称</th>
            <th>三证合一代码</th>
            <th class="hidden-480">类型</th>
             <th class="hidden-480">法人</th>
             <th class="hidden-480">联系电话</th>
			 <th class="hidden-480">微信号</th>
			 <th class="hidden-480">QQ号</th>
			 <th class="hidden-480">电子邮箱</th>
			 <th class="hidden-480">地址</th>
			 <th class="hidden-480">操作</th>
            </tr>
          </thead>

          <tbody>
          <c:forEach items="${list}" var="sup">
          	<tr>
              <td class="center">
                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
              </td>
              <td>${sup.name}</td>
              <td>${sup.id}</td>
              <td class="hidden-480">${sup.typeId}</td>
              <td class="hidden-480">${sup.legalPerson}</td>
              <td>
                  ${sup.phone}
              </td>
			  <td>
                  ${sup.weChat }
              </td>
			  <td>
                  ${sup.aa}
              </td>
			  <td>
                  ${sup.email}
              </td>
			  <td>
                  ${sup.address }
              </td>
			  <td>
				<div class="inline position-relative" >
				  <button class="btn btn-mini btn-info" onclick="javaScript:window.location.href='<%=basePath%>supplier/goInfo?method=update&id=${sup.id}'"><i class="icon-edit"></i></button>
				  <button class="btn btn-mini btn-danger" onclick="javaScript:window.location.href='<%=basePath%>supplier/delSup?id=${sup.id}'"><i class="icon-trash"></i></button>
				</div>
              </td>
            </tr>
          
          </c:forEach>
            

                  </tbody>
            </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
		 <button class="btn btn-primary" onclick="javaScript:window.location.href='<%=basePath%>supplier/goInfo?method=add'">新增</button>
		 
		</div>
        <div id="AwaitingShipment" class="tab-pane">
		<form class="form-search">
		  单品名称：
		  <input type="text" class="input-medium search-query">
		  单品类型：
		  <input type="text" class="input-medium search-query">
		  单品价格：
		  <input type="text" class="input-medium search-query">
		  <button onclick="return false;" class="btn btn-purple btn-small">查询 <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
            <th class="center">
              <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
            </th>
            <th>清单编号</th>
            <th>供应商编号</th>
            <th class="hidden-480">供应单品</th>
			 <th class="hidden-480">操作</th>
            </tr>
          </thead>

          <tbody>
            <tr>
              <td class="center">
                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
              </td>
              <td>eqeq13123</td>
              <td>12312</td>
              <td class="hidden-480">肥皂</td>
			  <td>
				<div class="inline position-relative" >
				  <button class="btn btn-mini btn-info" data-toggle="modal" data-target="#myModal"><i class="icon-edit"></i></button>
				  <button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
				</div>
              </td>
            </tr>

                  </tbody>
            </table>
          </p>
		 <button class="btn btn-primary out" onclick="add()">新增</button>
		  
        </div>
        <div id="shipped" class="tab-pane">
          <p>
            <table id="table_bug_report" class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">
                    <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                  </th>
                  <th>类型编号</th>
                  <th>类型名称</th>
                </tr>
              </thead>

              <tbody>

                <tr>
                  <td class="center">
                    <label><input type="checkbox" class="input" ><span class="lbl"></span></label>
                  </td>
                  <td>123</td>
                  <td>电商</td>
                  <td>
				<div class="inline position-relative" >
				  <button class="btn btn-mini btn-info" data-toggle="modal" data-target="#myModal"><i class="icon-edit"></i></button>
				  <button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
				</div>
              </td>
                </tr>
	
              </tbody>
            </table>
          </p>
		   <button class="btn btn-primary out" data-toggle="modal" data-target="#inserttype">新增</button>
        </div>
       <div id="complete" class="tab-pane">
          <p>
            <table id="table_bug_report" class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">
                    <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                  </th>
                  <th>商品标题</th>
                  <th>价格</th>
                  <th class="hidden-480">数量</th>
                  <th>sku</th>
                  <th>订单编号</th>
                  <th class="hidden-480">订单创建时间</th>
                  <th class="hidden-480">物流跟踪号</th>

                </tr>
              </thead>

              <tbody>

                <tr>
                  <td class="center">
                    <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                  </td>
                  <td><a href="bvo-goodsdetail.html">ace.com</a></td>
                  <td>$45</td>
                  <td class="hidden-480">3</td>
                   <td>GM100320</td>
                  <td>896777676</td>
                  <td class="hidden-phone">Feb 12</td>
                  <td class="hidden-480"><a href="brand-ordertracking.html">46578990890</a></td>


                </tr>


                <tr>
                  <td class="center">
                    <label><input type="checkbox" class="input"><span class="lbl"></span></label>
                  </td>
                  <td><a href="bvo-goodsdetail.html">base.com</a></td>
                  <td>$35</td>
                  <td class="hidden-480">2</td>
                  <td>GM100320</td>
                  <td>86754444444</td>
                  <td class="hidden-phone">Feb 18</td>
                  <td class="hidden-480"><a href="brand-ordertracking.html">75684894</a></td>


                </tr>

                      </tbody>
            </table>
          </p>
        </div>
      </div>
  </div>
  </div>
  </div>















   <!--  <script src="static/js/jquery-1.9.1.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootbox.min.js"></script> -->



    <script>

      $(function(){
    	  $(top.hangge());	
    	  
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
