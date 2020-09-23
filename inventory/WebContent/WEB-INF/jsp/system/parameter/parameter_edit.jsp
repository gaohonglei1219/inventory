<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en"><head>
		<base href="http://localhost:8080/admin/">
		<meta charset="utf-8">
		<title></title>
		<meta name="description" content="overview &amp; stats">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="static/css/bootstrap.min.css" rel="stylesheet">
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link rel="stylesheet" href="static/css/font-awesome.min.css">
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css">
		<link rel="stylesheet" href="static/css/ace.min.css">
		<link rel="stylesheet" href="static/css/ace-responsive.min.css">
		<link rel="stylesheet" href="static/css/ace-skins.min.css">
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	$(top.hangge());
	//保存
	function save(){
		if($("#paramCd").val()==""){
			
			$("#paramCd").tips({
				side:3,
	            msg:'输入参数名',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#paramCd").focus();
			$("#paramCd").val('');
			$("#paramCd").css("background-color","white");
			return false;
		}else{
			$("#paramCd").val($.trim($('#paramCd').val()));
		}
		
		if($("#paramValue").val()==""){
			
			$("#paramValue").tips({
				side:3,
	            msg:'输入参数值',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#paramValue").focus();
			return false;
		}else{
			$("#paramValue").val($.trim($("#paramValue").val()));
		}
		
		
		
		if($("#parId").val()=="0"){
			hasCD();
		}else{
			$("#parameterForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}

	}
	
	//判断参数是否存在
	function hasCD(){
		var paramCd = $.trim($("#paramCd").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>parameter/hasPARAM_CD.ajax',
	    	data: {paramCd:paramCd},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.msg){
					$("#parameterForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					 $("#paramCd").tips({
							side:3,
				            msg:'参数已存在',
				            bg:'#AE81FF',
				            time:2
				        });
				 }
			}
		});
	}
	
</script>
	</head>
<body>
	<form action="parameter/${msg}.do?userName=${userName}" name="parameterForm" id="parameterForm" method="post">
		<input type="hidden" name="parId" id="parId" value="0${parameter.parId }"/>
		<div id="zhongxin">
		<table>
			
				
			<tbody>
			
			
			
			
			
			
			<tr>
				<td><input name="paramCd" id="paramCd" value="${parameter.paramCd}" maxlength="200" placeholder="参数名" title="参数名"></td>
			</tr>
			<tr>
				<td><input name="paramValue" id="paramValue" value="${parameter.paramValue}" maxlength="500" placeholder="参数值" title="参数值"></td>
			</tr>
			<tr>
				<td><textarea name="description" id="description" placeholder="参数描述" title="参数描述" style="height:150px;width:195px">${parameter.description}</textarea></td>
			</tr>
			
			
			
			
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</tbody></table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br><br><br><br><img src="static/images/jiazai.gif"><br><h4 class="lighter block green"></h4></div>
		
	</form>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$(".date-picker").datepicker();
			
		});
		
		</script>
	

</body></html>