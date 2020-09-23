<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<meta charset="utf-8">

<script type="text/javascript"
	src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(top.hangge());//关闭遮罩层
</script>
<link rel="stylesheet"
	href="<%=basePath%>/static/css/bootstrap-combined.no-icons.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/static/css/bootstrap-responsive.min.css">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>/static/css/index.css"
	type="text/css">

<style>
body, html {
	width: 100%;
	height: 100%;
	background: #fff;
}

#editor {
	height: 200px;
}

#main-content {
	margin-left: 190px;
}

#page-content {
	margin: 0;
	background: #FFF;
	padding: 8px 20px 24px;
}

.modal-footer {
	background: #fff;
}
</style>
</head>
<body>

	<div id="main-content" class="clearfix">
		<div id="page-content" class="clearfix" style="margin-left:-100px;">

			<h3 style="line-height: 40px;">公司信息</h3>
			<table id="table_bug_report"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Company Name公司名称（中文）</th>
						<th>Company Name公司名称（英文）</th>
						<th>GMC Report Type品牌商认证类型</th>
						<th>GMC Report Url证书地址</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>

					<tr>
						<td>${manufacturer.nameCn }</td>
						<td>${manufacturer.nameEn }</td>
						<td>${manufacturer.gmcReportType }</td>
						<td>${manufacturer.gmcReportUrl }</td>
						<td>
							<div class="inline position-relative">
								<button class="btn btn-mini btn-info" onclick="goEdit()">
									<i class="icon-edit"></i>
								</button>
							</div>
						</td>
					</tr>

				</tbody>
			</table>

			<h3 style="margin-top: 40px; line-height: 40px;">品牌信息</h3>
			<!-- 品牌信息 -->
			<table id="table_bug_report"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center"><label><input type="checkbox"
								class="ace-checkbox-2"><span class="lbl"></span></label></th>
						<th>品牌名称Brand Name</th>
						<th>品牌图片Brand Logo</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>

					<tr>
						<th class="center"><label><input type="checkbox"
								class="input"><span class="lbl"></span></label></th>
						<td>${brand.nameCn}</td>
						<td><!-- <img src="#" width="160" alt="" /> --></td>
						<td>
							<div class="inline position-relative">
								<button class="btn btn-mini btn-info" data-toggle="modal"
									data-target="#myModal">
									<i class="icon-edit"></i>
								</button>
								<button class="btn btn-mini btn-danger">
									<i class="icon-trash"></i>
								</button>
							</div>
						</td>
					</tr>

				</tbody>
			</table>
			<button class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">新增</button>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">品牌信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">

								<div class="control-group">
									<label class="control-label" for="form-field-1">品牌名称<br>Brand
										Name
									</label>
									<div class="controls">
										<input type="text" id="form-field-1">
									</div>
								</div>

								<div class="control-group">
									<p class="red">(建议图片大小160*80的jpg/png格式)</p>
									<label class="control-label" for="form-field-1">品牌图片<br>Brand
										Logo
									</label>
									<div class="controls">
										<span style="font-size: 14px;"><div id="uploader-demo">
												<!--用来存放item-->
												<div id="thelist" class="uploader-list"></div>
												<div>
													<div id="filePicker">选择图片</div>
													<button id="ctlBtn" class="btn btn-default">开始上传</button>
												</div>
											</div> </span>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>