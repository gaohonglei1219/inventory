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

  </style>
    <body>

    <div id="main-content" class="clearfix">
  <div id="page-content" class="clearfix">

    <div class="page-header position-relative">
      <h1 style="color: #2679b5;">供应管理<small><i class="icon-double-angle-right"></i> 单品管理</small></h1>
    </div>

    <div class="tabbable" style="z-index:2;position:absolute">
      <ul class="nav nav-tabs" id="myTab">
        <li class="<c:if test="${pageType==none}">active</c:if>"><a data-toggle="tab" id = "singleItemTab"  href="#singleItem"> 单品管理</a></li>
        <li class="<c:if test="${pageType=='sinType'}">active</c:if>"><a data-toggle="tab" id = "sinTypeTab"  href="#sinType">单品类型管理</a></li>
        <li class="<c:if test="${pageType=='sinState'}">active</c:if>"><a data-toggle="tab" id = "sinStateTab"  href="#sinState">单品状态管理</a></li>
      </ul>
      <div class="tab-content">
        <div id="singleItem" class="tab-pane <c:if test="${pageType==none}">active</c:if>" style="overflow:scroll;">
		<form class="form-search" id="singleItemTabForm" action="<%=basePath%>singleItem/querylist" method="post">
		  单品类型：
		  <input type="text" name = "sinType" class="input-medium search-query" value = "${pd.sinType}">
		  生产商：
		  <input type="text" name = "manuFac" class="input-medium search-query" value = "${pd.manuFac}">
		  最低价格：
		  <input type="text" name = "price" class="input-medium search-query" value = "${pd.minPrice}">
		最高价格:  
		  <input type="text" name = "price" class="input-medium search-query" value = "${pd.maxPrice}">
		  单品名：
		  <input type="text" name = "sinName" class="input-medium search-query" value = "${pd.sinName}">
		  <button class="btn btn-purple btn-small">Search <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover" style="min-width:1500px;">     
          <thead>
            <tr>
            <th class="center">
              <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
            </th>
            <th>编号</th>
            <th>短名称</th>
            <th class="hidden-480">全名称</th>
             <th class="hidden-480">富文本描述</th>
             <th class="hidden-480">生产商</th>
			 <th class="hidden-480">类型</th>
			 <th class="hidden-480">保质期</th>
			 <th class="hidden-480">库存条件</th>
			 <th class="hidden-480">条码</th>
			 <th class="hidden-480">计价单位</th>
			 <th class="hidden-480">计价规格</th>
			 <th class="hidden-480">正常价格</th>
			 <th class="hidden-480">划线价格</th>
			 <th class="hidden-480">会员价格</th>
			 <th class="hidden-480">大图url</th>
			 <th class="hidden-480">小图url</th>
			 <th class="hidden-480">短视频url</th>
			 <th class="hidden-480">状态</th>
			 <th class="hidden-480">备注</th>
			 <th class="hidden-480">操作</th>
            </tr>
          </thead>

          <tbody>
          <c:forEach items="${list}" var="sin">
          	<tr>
              <td class="center">
                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
              </td>
              <td>${sin.id}</td>
              <td>${sin.shortName}</td>
              <td class="hidden-480">${sin.name}</td>
              <td class="hidden-480">${sin.richText}</td>
			  <td class="hidden-480">
                  ${sin.manufactor }
              </td>
			  <td class="hidden-480">
                  ${sin.typeName}
              </td>
			  <td class="hidden-480">
                  ${sin.shelfLifeData}
              </td>
			  <td>
                  ${sin.storageCon}
              </td>
              <td>
                  ${sin.barCode}
              </td>
              <td>
                  ${sin.uovName}
              </td>
              <td>
                  ${sin.specs}
              </td>
              <td>
                  ${sin.normalPrice}
              </td>
              <td>
                  ${sin.linePrice}
              </td>
              <td>
                  ${sin.membePrice}
              </td>
              <td>
                  ${sin.bigPicture}
              </td>
              <td>
                  ${sin.smallPicture}
              </td>
              <td>
                  ${sin.videoUrl}
              </td>
              <td>
                  ${sin.stateName}
              </td>
              <td>
                  ${sin.remarks}
              </td>
			  <td>
				<div class="inline position-relative" >
				  <button class="btn btn-mini btn-info" onclick='adu_fun("singleItem","update","${sin.id}")'><i class="icon-edit"></i></button>
				  <button class="btn btn-mini btn-danger" onclick='adu_fun("singleItem","del","${sin.id}")'><i class="icon-trash"></i></button>
				</div>
              </td>
            </tr>
          
          </c:forEach>
            

                  </tbody>
            </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
		 <button class="btn btn-primary" onclick='adu_fun("singleItem","add","")'>新增</button>
		 
		</div>
        <div id="sinType" class="tab-pane <c:if test="${pageType=='sinType'}">active</c:if>">
		<form class="form-search" id="sinTypeTabForm" action="<%=basePath%>singleType/querylist" method="post">
		  单品类型：
		  <input type="text" name="typeName" class="input-medium search-query">
		  <button  class="btn btn-purple btn-small">查询 <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
          <table id="table_bug_report" class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
            <th class="center">
              <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
            </th>
            <th>类型编号</th>
            <th>类型名</th>
            <th class="hidden-480">类型层级</th>
			 <th class="hidden-480">类型归属</th>
			 <th class="hidden-480">类型描述</th>
			 <th class="hidden-480">操作</th>
            </tr>
          </thead>

          	<tbody>
          	<c:forEach items="${typeList}" var="type">
          	<tr>
              <td class="center">
                <label><input type="checkbox" class="input"><span class="lbl"></span></label>
              </td>
              <td>${type.typeId}</td>
              <td>${type.typeName}</td>
              <td class="hidden-480">${type.typeHierarchy}</td>
              <td class="hidden-480">${type.typeAscription}</td>
              <td class="hidden-480">${type.typeDescribe}</td>
			  <td>
				<div class="inline position-relative" >
				  <button class="btn btn-mini btn-info" onclick='adu_fun("singleType","update","${type.typeId}")'><i class="icon-edit"></i></button>
				  <button class="btn btn-mini btn-danger" onclick='adu_fun("singleType","del","${type.typeId}")'><i class="icon-trash"></i></button>
				</div>
              </td>
            </tr>
          	
          	</c:forEach>
               </tbody>
            </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
		 <button class="btn btn-primary out" onclick='adu_fun("singleType","add","")'>新增</button>
		  
        </div>
        <div id="sinState" class="tab-pane <c:if test="${pageType=='sinState'}">active</c:if>">
        <form class="form-search" id="sinStateTabForm" action="<%=basePath%>singleState/querylist" method="post">
		  状态名称：
		  <input type="text" name="stateName" class="input-medium search-query">
		  <button  class="btn btn-purple btn-small">查询 <i class="icon-search icon-on-right"></i></button>
		</form>
          <p>
            <table  class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                  <th class="center">
                    <label><input type="checkbox" class="ace-checkbox-2"><span class="lbl"></span></label>
                  </th>
                  <th>状态编号</th>
                  <th>状态名称</th>
                  <th>状态描述</th>
                  <th>操作</th>
                </tr>
              </thead>

              <tbody>
              	<c:forEach items="${stateList}" var='state'>
              		<tr>
	                  <td class="center">
	                    <label><input type="checkbox" class="input" ><span class="lbl"></span></label>
	                  </td>
	                  <td>${state.stateId}</td>
	                  <td>${state.stateName}</td>
	                  <td>${state.stateDescribe}</td>
	                  <td>
						<div class="inline position-relative" >
					  		<button class="btn btn-mini btn-info" onclick='adu_fun("singleState","update","${state.stateId}")'><i class="icon-edit"></i></button>
					  		<button class="btn btn-mini btn-danger" onclick='adu_fun("singleState","del","${state.stateId}")'><i class="icon-trash"></i></button>
						</div>
	              	  </td>
                </tr>
              	</c:forEach>
              </tbody>
            </table>
            <div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
          </p>
		   <button class="btn btn-primary" onclick='adu_fun("singleState","add","${state.stateId}")'>新增</button>
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
    	 $('#singleItemTab').click(function(){
    		 window.location.href = '<%=basePath%>singleItem/querylist'
    	 })
    	 $('#sinTypeTab').click(function(){
    		 window.location.href = '<%=basePath%>singleType/querylist'
    	 })
    	 $('#sinStateTab').click(function(){
    		 window.location.href = '<%=basePath%>singleState/querylist'
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
