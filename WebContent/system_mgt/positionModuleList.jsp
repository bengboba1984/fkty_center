<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="ra.com.system_mgt.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<title>Position Management</title>
<%
	User u = (User) session.getAttribute("loginUser");
	if (u == null) {
		response.sendRedirect("login.action");
	} else {

		boolean isAdmin = ("admin".equals(u.getUserName()));
	}
%>
<script type="text/javascript">

function onClickRow() {
	var row = $('#dg').datagrid('getSelected');
    if (row){
        //$.messager.alert('Info', row.positionID);
        $('#ttt').tree({
    		url:'position_menu_list.action?positionId='+row.positionID,
    				idField : 'id',
    				treeField : 'text',
    				
    				columns : [ [ {
    					title : 'Position Module Setting',
    					field : 'text',
    					width : 180
    				} ] ]

    			});
    }
}

$(function(){
$('#content1').panel({
	    
	    tools:[{
	        iconCls:'icon-save',
	        handler:function(){getCheckedModule()}
	    }]
	}); 
		});
		function getCheckedModule() {
			var row = $('#dg').datagrid('getSelected');
			if(row==null){
				alert("请先选择角色!");
				return false;
			}
			
			var nodes = $('#ttt').tree('getChecked');
			var v = '';
			for (var i = 0; i < nodes.length; i++) {
				//if (s != '')
					//s += ',';
				//s += nodes[i].text;
				
				if(nodes[i].id!='') {
					if (v != '') v += ',';
					v += nodes[i].id;
				}
			}
			if(v==''){
			alert("请选择菜单!");
			return false;
			}
			$.post("position_module_save.action?positionId="+row.positionID+"&moduleIds="+v, null,
					function(response) {
						if(response.error==undefined){
							$.messager.alert("提示", "保存成功！");
						}else{
							$.messager.alert("提示", "保存失败！\n"+response.error);
							$('#ttt').tree({
					    		url:'position_menu_list.action?positionId='+row.positionID,
					    				idField : 'id',
					    				treeField : 'text',
					    				
					    				columns : [ [ {
					    					title : 'Position Module Setting',
					    					field : 'text',
					    					width : 180
					    				} ] ]

					    			}); 
						}	
					}, "JSON").error(function() {
							$.messager.alert("提示", "保存失败！");
							$('#ttt').tree({
					    		url:'position_menu_list.action?positionId='+row.positionID,
					    				idField : 'id',
					    				treeField : 'text',
					    				
					    				columns : [ [ {
					    					title : 'Position Module Setting',
					    					field : 'text',
					    					width : 180
					    				} ] ]

					    			}); 
						});
		}
	</script>
</head>
<body>
	<div class="easyui-layout" style="height:600px;" >
		<div region="west" split="true" title="岗位列表" style="width: 20%;">

			<div data-options="region:'center'" style="height: 100%" >
				<table id="dg" class="easyui-datagrid" 
					style="width: 100%; height: 100%" 
					data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'role_list.action',method: 'post',onClickRow: onClickRow">
					<thead>
						<tr>
						<th
								data-options="field:'positionID',sortable:'true',hidden:'true',width:10"><s:text
									name="system.position.positionID" /></th>
							<th
								data-options="field:'positionName',sortable:'true',width:160"><s:text
									name="system.position.positionName" /></th>

						</tr>
					</thead>
				</table>
			</div>

		</div>

		<div id="content1" region="center" title="功能列表"
			style="padding: 5px;">
			<div class="easyui-panel" style="padding: 5px">
				<ul id="ttt" class="easyui-tree"
					data-options="animate:true,checkbox:true"></ul>
			</div>

		</div>
	</div>

	
</body>
</html>