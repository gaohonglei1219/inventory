<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%> 
	<style type="text/css">
	#reback{
		position:absolute;
		top:20px;
		left:160px;
		z-index:2;
	}
	</style>
	</head> 
	

<body>
<div id="reback">
	<c:if test="${QX.cha == 1 }">
		<a class="btn btn-mini btn-light" onclick="window.location.href='<%=basePath%>parameter/listParameters.do';" title="切换模式"><i id="nav-search-icon" class="icon-exchange"></i></a>
		<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
		<c:if test="${QX.edit == 1 }"><td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="fromExcel();" title="从EXCEL导入"><i id="nav-search-icon" class="icon-cloud-upload"></i></a></td></c:if>
	</c:if>
</div>		
<div class="container-fluid" id="main-container">



<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	
<div class="row-fluid">
	
		<table id="table_report" class="table table-striped table-bordered table-hover">
			
			<thead>
				<tr>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th>参数名</th>
						<th>参数值</th>
						<th>描述</th>

						<th>最近修改者</th>
						<th><i class="icon-time hidden-phone"></i>最近修改时间</th>
						<th>创建者</th>
						<th><i class="icon-time hidden-phone"></i>创建时间</th>
						<th class="center">操作</th>
					</tr>
				
			</thead>
								
			<tbody>
				
		<!-- 开始循环 -->	
		<c:choose>
			<c:when test="${not empty parameterList}">
				<c:if test="${QX.cha == 1 }">
					<c:forEach items="${parameterList}" var="parameter" varStatus="vs">
									
							<tr>
								<td class='center' style="width: 30px;" nowrap="nowrap"><label>
										<input type='checkbox' name='ids' value="${parameter.parId }"/>
										<span class="lbl"></span>
								</label></td>
								<td style="word-break:break-all;">${parameter.paramCd }</td>
								<td style="word-break:break-all;"><a>${parameter.paramValue }</a></td>
								<td style="word-break:break-all;">${parameter.description }</td>
								<td>${parameter.lastUpdateBy }</td>
								<td nowrap="nowrap">${parameter.lastUpdateDate }</td>
								<td>${parameter.createdBy }</td>
								<td nowrap="nowrap">${parameter.creationDate }</td>
								<td style="width: 60px;">
									<div class='hidden-phone visible-desktop btn-group'>
										<c:if test="${QX.edit == 1 }">
											<a class='btn btn-mini btn-info' title="编辑" onclick="edit(${parameter.parId});"><i class='icon-edit'></i></a>
										</c:if>
										<c:if test="${QX.del == 1 }">
											 <a class='btn btn-mini btn-danger' title="删除" onclick="del(${parameter.parId},'${parameter.paramCd}');"><i class='icon-trash'></i></a>
										</c:if>
									</div>
								</td>
							</tr>

						
					</c:forEach>
				</c:if>
						
				<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="10" class="center">您无权查看</td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
								<td hidden="hidden"></td>
							</tr>
						</c:if>
			</c:when>
			
			<c:otherwise>
						<tr class="main_info">
							<td colspan="10" class="center">没有相关数据</td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
							<td hidden="hidden"></td>
						</tr>
			</c:otherwise>
		</c:choose>
					
			</tbody>
		</table>
	
</div>
 
 		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<c:if test="${QX.del == 1 }">
					<a title="批量删除" class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" ><i class='icon-trash'></i></a>
					</c:if>
					
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.dataTables.bootstrap.js"></script>
		
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<script type="text/javascript">
		
		$(top.hangge());
		$(function() {
			var oTable1 = $('#table_report').dataTable( {
			"aoColumns": [
		      { "bSortable": false },
		      null, null,null, null, null,null,null,
			  { "bSortable": false }
			] } );
			//可能是个数组,初始化表格
			
			
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			$('[data-rel=tooltip]').tooltip();
		})
		
		
		
		
		//检索
		function search(){
			top.jzts();
			$("#parameterForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>parameter/jumpAdd.do';
			 diag.Width = 215;
			 diag.Height = 275;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function edit(id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>parameter/jumpEdit.do?parId='+id;
			 diag.Width = 215;
			 diag.Height = 275;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					setTimeout("self.location.reload()",100);
					top.jzts();
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(id,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>/parameter/delete.ajax',
				    	data: {parId:id,userName:'${userName}'},
						dataType:'json',
						//beforeSend: validateData,
						cache: false,
						success: function(data){
							document.location.reload();
							nextPage(${page.currentPage});
						}
					});
				}
			});
		}	

		//批量删除
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					top.jzts();
					var str = '';
					
					for(var i=0;i < document.getElementsByName('ids').length;i++){
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>/parameter/deleteAll.ajax',
						    	data: {parIds:str,userName:'${userName}'},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									for(var i=0;i<data.num;i++){
										document.location.reload();
										nextPage(${page.currentPage});
									}
								}
							});
						}
					}
				}
			});
		}		
		</script>
		
		<script type="text/javascript">
		$(function() {
			//日期框
			$('.date-picker').datepicker();
			
						
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
	
		});

	});
		
		//导出excel
		function toExcel(){
			var selectData = $("#nav-search-input").val();
			var lastUpdataDataStart = $("#lastUpdataDataStart").val();
			var lastUpdataDataEnd = $("#lastUpdataDataEnd").val();
			window.location.href='<%=basePath%>parameter/excel.do?selectData='+selectData+'&lastUpdataDataStart='+lastUpdataDataStart+'&lastUpdataDataEnd='+lastUpdataDataEnd;
		}
		
		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>parameter/goUploadExcel.do';
			 diag.Width = 300;
			 diag.Height = 150;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}

		</script>
		
	</body>
</html>

