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
	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%>    
		<meta name="description" content="Widget Boxes & Containers" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		
		<!-- ace styles -->
		<link rel="stylesheet" href="css/ace.min.css" />
		<link rel="stylesheet" href="css/ace-responsive.min.css" />
		<link rel="stylesheet" href="css/ace-skins.min.css" />
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
	</head> 
	<body>
	<script>
	$(top.hangge());
	</script>
	<%-- <table>
			<tr height="7px;"><td colspan="100"></td></tr>
			<tr>
			<td><font color="#808080">本组：</font></td>
			<td>
			<c:if test="${QX.edit == 1 }">
			<a class="btn btn-mini btn-info" onclick="editRole('${pd.roleId }');">修改组名称<i class="icon-arrow-right  icon-on-right"></i></a>
			</c:if>
				<c:choose>
					<c:when test="${pd.roleId == '99'}">
					</c:when>
					<c:otherwise>
					<c:if test="${QX.edit == 1 }">
					<a class="btn btn-mini btn-purple" onclick="editRights('${pd.roleId}');"><i class="icon-pencil"></i>组菜单权限</a>
					</c:if>
					</c:otherwise>
				</c:choose>
				<c:choose> 
					<c:when test="${pd.roleId == '6' or pd.roleId== '4' or pd.roleId == '1' or pd.roleId == '7'}">
					</c:when>
					<c:otherwise>
					 <c:if test="${QX.del == 1 }">
					 <a class='btn btn-mini btn-danger' title="删除" onclick="delRole('${pd.roleId }','z','${pd.roleName }');"><i class='icon-trash'></i></a>
					 </c:if>
					</c:otherwise>
				</c:choose>
			</td>
			</tr>
			<tr height="7px;"><td colspan="100"></td></tr>
		</table> --%>
	<div class="span6" >
		<div class="widget-box"  style=" width:1000px">
			<div class="widget-header header-color-blue">
			   <h5>角色列表</h5> 
				<%-- <h5 class="bigger lighter">
					<!-- <i class="icon-table"></i>  -->
					<c:choose>
					<c:when test="${not empty roleList}">
					<c:forEach items="${roleList}" var="role" varStatus="vs">
						<td style="width:100px;" class="center" <c:choose><c:when test="${pd.ROLE_ID == role.ROLE_ID}">bgcolor="#000000" onMouseOut="javascript:this.bgColor='#FFC926';"</c:when><c:otherwise>bgcolor="#E5E5E5" onMouseOut="javascript:this.bgColor='#E5E5E5';"</c:otherwise></c:choose>  onMouseMove="javascript:this.bgColor='#000000';" >
							<a href="role.do?roleId=${role.roleId }" style="text-decoration:none; display:block;"><li style="width:30px;height:30px;display:block;float:left" ><img src="static/images/my.png"></li>&nbsp;<font color="#000000";float="left">${role.roleName}</font></a>
						</td>
						<td style="width:5px;"></td>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
						<td colspan="100">没有相关数据</td>
						</tr>
					</c:otherwise>
					</c:choose>				
				</h5> --%>
				<div class="widget-toolbar widget-toolbar-light no-border" >
					<select id="simple-colorpicker-1" class="hidden">
						  <option selected data-class="blue" value="#307ECC">#307ECC</option>
						  <option data-class="blue2" value="#5090C1">#5090C1</option>
						  <option data-class="blue3" value="#6379AA">#6379AA</option>
						  
						  <option data-class="green" value="#82AF6F">#82AF6F</option>
						  <option data-class="green2" value="#2E8965">#2E8965</option>
						  <option data-class="green3" value="#5FBC47">#5FBC47</option>
						  
						  <option data-class="red" value="#E2755F">#E2755F</option>
						  <option data-class="red2" value="#E04141">#E04141</option>
						  <option data-class="red3" value="#D15B47">#D15B47</option>
						  
						  <option data-class="orange" value="#FFC657">#FFC657</option>
						  <option data-class="purple" value="#7E6EB0">#7E6EB0</option>
						  <option data-class="pink" value="#CE6F9E">#CE6F9E</option>
						  <option data-class="dark" value="#404040">#404040</option>
						  <option data-class="grey" value="#848484">#848484</option>
						  <option data-class="default" value="#EEE">#EEE</option>
					</select>
				</div>
			</div>
	<div class="widget-body">
			 <div class="widget-main no-padding">
			  <table id="table_bug_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:5% " class="center">序号</th>
						<th style="width:10%" class="center"><i class="icon-user"></i>角色</th>	
						<th style="width:20%" class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty newRoleList}">
							<%-- <c:if test="${QX.cha == 1 }"> --%>
							<c:forEach items="${newRoleList}" var="var" varStatus="vs">								
							<tr>
							<td class='center' style="width:40px;">${vs.index+1}</td>
							<td class='center' id="ROLE_NAMETd${var.roleId }">${var.roleName }</td>							
							<td class='center' style="width:70px;">	
							<a class="btn btn-mini btn btn-yellow" onclick="RoleEdit('${var.roleId }');"><i class="icon-pencil"></i>菜单权限</a>
							<a class="btn btn-mini btn btn-yellow" onclick="editRole('${var.roleId }');"><i class="icon-pencil"></i>编辑</a>
							<a class='btn btn-mini btn-danger ' title="删除" onclick="delRole('${var.roleId }','${var.roleName }');"><i class='icon-trash'></i></a>
											
							</tr>
							</c:forEach>
							<%-- </c:if>	 --%>								
						</c:when>
						<c:otherwise>
							<tr>
							<td colspan="100" class="center" >没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			  </table>
			  
			 </div>
			</div>
			
		<p style="margin-top:20px"><a class="btn btn-small btn-primary" onclick="addRole();">新增</a></p>
		<!-- 引入 -->
		
		<script type="text/javascript">
		
		top.hangge();		
		
		
		//新增角色
		function addRole(){
			
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增角色";
			 diag.URL ='<%=basePath%>newRole/toAdd.do';
			 diag.Width = 222;
			 diag.Height = 90;
			 diag.CancelEvent = function(){ //关闭事件
				if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					top.jzts();
					setTimeout("self.location.reload()",100);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//角色授权
		function RoleEdit(roleId){			
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="授权管理";
			 diag.URL = '<%=basePath%>newRole/toRights.do?roleId='+roleId;
			 diag.Width = 220;
			 diag.Height = 900;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
						top.jzts(); 
						setTimeout("location.reload()",100);
					}
					diag.close(); 	
			 };
			 diag.show();
		}
		
		//修改
		function editRole(roleId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>newRole/toEdit.do?roleId='+roleId;
			 diag.Width = 222;
			 diag.Height = 90;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					top.jzts();
					setTimeout("self.location.reload()",100);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delRole(roleId,roleName){			
			bootbox.confirm("确定要删除["+roleName+"]吗?", function(result) {
				if(result) {					
					var url = "<%=basePath%>newRole/saveDelete.do?roleId="+roleId;
					top.jzts();
					$.get(url,function(data){
								top.jzts();
								document.location.reload();
					});
				}
			});
			
		}
		
		</script>	
	</body>
</html>

