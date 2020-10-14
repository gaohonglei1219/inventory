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
          <h1 style="color: #2679b5;">单品管理<small><i class="icon-double-angle-right"></i> 单品信息</small></h1>
        </div>

          <form class="form-horizontal" action="<%=basePath%>singleItem/${getMethod}">

            <div class="control-group">
              <label class="control-label" for="form-field-1">单品编号</label>
              <div class="controls">
                <input type="text" id="form-field-1" name= "id" value="${pd.id}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">单品全名</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="name" value="${pd.name}">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">单品短名称</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="shortName" value="${pd.shortName}">
              </div>
            </div>
            <div class="control-group">
            <label class="control-label" for="form-field-1">商品富文本描述</label>
            <div class="controls">
				<textarea rows="" cols="" name="richText" >${pd.richText}</textarea>
            </div>
          </div>
            <div class="control-group">
              <label class="control-label" for="form-field-1">生产商</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="manufactor" value="${pd.manufactor}">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">单品类型</label>
              <div class="controls" id = "typeChoose">
                <select name="typeId" id="chooseType">
                <option value="0">默认类型</option>
                </select>
              </div>
            </div>


            <div class="control-group">
              <label class="control-label" for="form-field-1">保质期限</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="shelfLifeData" value="${pd.shelfLifeData}">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" for="form-field-1">库存条件</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="storageCon" value="${pd.storageCon}">
              </div>
            </div>

            <div class="control-group">
			  <label class="control-label" for="form-field-1">单品条码</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="barCode" value="${pd.barCode}">
              </div>
			</div>
         
            <div class="control-group">
				<label class="control-label" for="form-field-1">计价单位</label>
              <div class="controls">
              <select name="uovId" id="chooseUov">
              	<option value="0">默认计价单位</option>
              </select>
              </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="form-field-1">计价规格</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="specs" value="${pd.specs}">
              </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="form-field-1">正常价格</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="normalPrice" value="${pd.normalPrice}">元
              </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="form-field-1">划线价格</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="linePrice" value="${pd.linePrice}">元
              </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="form-field-1">会员价格</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="membePrice" value="${pd.membePrice}">元
              </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="form-field-1">单品大图</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="bigPicture" value="${pd.bigPicture}">
              </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="form-field-1">单品小图</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="smallPicture" value="${pd.smallPicture}">
              </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="form-field-1">单品短视频</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="videoUrl" value="${pd.videoUrl}">
              </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="form-field-1">单品状态</label>
              <div class="controls">
                <select id="chooseState" name="stateId">
                	<option value="0">默认状态</option>
                </select>
              </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="form-field-1">备注</label>
              <div class="controls">
                <input type="text" id="form-field-1" name="remarks" value="${pd.remarks}">
              </div>
			</div>

		<div class="control-group" >
		<button class="btn btn-primary saved" style="margin-left:18%" >保存</button>
		<input type="button" class="btn" onclick="javascript:window.history.go(-1)" value="取消">
        </div>
        </form>
        
      </div>

    </div>
    <script src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>static/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/js/bootbox.min.js"></script>
  <!--   <script src="richText/bootstrap-wysiwyg.js" type="text/javascript"></script>
    <script src="richText/jquery.hotkeys.js" type="text/javascript"></script> -->
    <script>
      /* 获取单品类型 */
      function getType(paId,broEle){
		  queryData = {}
		  if(paId==0){
			  queryData['typeHierarchy'] = paId
		  }else if(paId!='all'){
			  queryData['typeAscriptionId'] = paId
		  }
    	  res = false
    	  $.ajax({
    			 url : '<%=basePath%>singleType/getAllIdName',
  			 	 data:queryData,
    			 success : function(data){
    				 var aim;
    				 if(paId==0||paId=='all'){
    					 aim = $('#chooseType');
    				 }else{
    					 if(broEle.siblings().size()>0){
    						 aim = broEle.next()
    					 }else{
    						 broEle.after("<select id='chooseType"+paId+"' name='typeId'></select>")
    						 broEle.removeAttr('name')
    						 aim = broEle.next()
    					 }
    					 console.log(aim)
    				 }
    				 aim.html("<option value=''>-请选择-</option>");
    				 $.each(data,function(i,v){
    					 var userType = ${pd.typeId!=None?pd.typeId:"'no'"}
    					 if(userType==v.typeId){
    						aim.append("<option value='"+v.typeId+"'selected>"+v.typeName+"</option>")
    					 }else{
    						aim.append("<option value='"+v.typeId+"'>"+v.typeName+"</option>")
    					 }
    					 
    				 })
    					 
    			 }
    		 })
    	return res
      }
      $(function(){
    	  $(top.hangge());
    	  var method = '${getMethod}'
    	  if(method=='insert'){
    		  /* 拉取顶层单品类型信息 */
        	  getType(0)
    	  }else{
    		  getType('all')
    		  $('#chooseType').attr('readonly','readonly')
    		  $('#chooseType').after('<a href="#" onclick="return false" id="reChoose">重新选择类型</a>')
    		  $('#reChoose').click(function(){
    			  $('#chooseType').removeAttr('readonly')
    			  getType(0)
    			  $('#reChoose').remove()
    		  })
    	  }
    	  
    	  $('#typeChoose select').change(function(){
    		  var parId = $(this).val()
    		  getType(parId,$(this))
    	  })
   	
   		 
   		 
   		 /* 拉取单品状态信息 */
   		 $.ajax({
   			 url : '<%=basePath%>singleState/getAllIdName',
   			 success : function(data){
   				 $('#chooseState').html("<option value=''>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var userType = ${pd.stateId!=none?pd.stateId:"'no'"}
   					 if(userType==v.stateId){
   						$('#chooseState').append("<option value='"+v.stateId+"'selected>"+v.stateName+"</option>")
   					 }else{
   						$('#chooseState').append("<option value='"+v.stateId+"'>"+v.stateName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
   		 
   		 /* 拉取单品计价规格信息 */
   		 $.ajax({
   			 url : '<%=basePath%>singleItem/getAllUov',
   			 success : function(data){
   				 $('#chooseUov').html("<option value=''>-请选择-</option>");
   				 $.each(data,function(i,v){
   					 var userType = ${pd.uovId!=none?pd.uovId:"'no'"}
   					 if(userType==v.uovId){
   						$('#chooseUov').append("<option value='"+v.uovId+"'selected>"+v.uovName+"</option>")
   					 }else{
   						$('#chooseUov').append("<option value='"+v.uovId+"'>"+v.uovName+"</option>")
   					 }
   					 
   				 })
   					 
   			 }
   		 })
	})





    </script>
    </body>
</html>
