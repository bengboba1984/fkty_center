<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
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
<title>Role Management</title>
</head>
<body>
<input type="hidden" name = "moduleId" id = "moduleId" value="${moduleId}"/>
	<div class="easyui-layout" style="height:800px;">
		<div data-options="region:'north',title:'<s:text name="common.searchCriteria"></s:text>',collapsible:false" style="height:20%">
				<table style="width:100%">
					<tr>
						<td colspan="4" align="right">
							<a href="javascript:void(0)" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()"><s:text name="common.search"></s:text></a>
						</td>
					</tr>
					<tr>
						<td class="panel-header" style="width:25%" align="center">角色名称</td>
						<td style="width:25%"> <input id="positionName" class="easyui-textbox" style="width:90%"></td>
						<td colspan="2" align="right">&nbsp;</td>
					</tr>
					
				</table>
				
		</div>
		<div data-options="region:'center'" style="height:80%">
					<table id="dg" class="easyui-datagrid" title="角色列表"
							style="width: 100%; height:100%" remoteSort="false"
							data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'role_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
<!-- 				<th data-options="field:'positionID',hidden:true,width:100">Position -->
<!-- 					ID</th> -->
				<th
					data-options="field:'positionName',sortable:'true',width:160,editor:{type:'validatebox',options:{required:true}}">角色名称</th>

			</tr>
		</thead>
	</table>
		</div>
	
</div>	

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="add"
			data-options="iconCls:'icon-add',plain:true,disabled:true" onclick="append()"><s:text name="common.append" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="delete"
			data-options="iconCls:'icon-remove',plain:true,disabled:true" onclick="removeit()"><s:text name="common.remove" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="save"
			data-options="iconCls:'icon-save',plain:true,disabled:true" onclick="accept()"><s:text name="common.accept" /></a>
		 <a href="javascript:void(0)" class="easyui-linkbutton" id="cancle"
			data-options="iconCls:'icon-undo',plain:true,disabled:true" onclick="reject()"><s:text name="common.reject" /></a> 
	</div>




	<script type="text/javascript">
	$.ajax({
        url: 'action_button_flag.action?moduleId='+$('#moduleId').val(),
        type: 'post',
 		dataType: 'json',
        success: function (result) {
        	var v  = result.roleValue.split(",");
        	$.each( v, function(i, field){
        		$('#'+field).linkbutton('enable')
        	});
			}
    });
	function doSearch(){
		$('#dg').datagrid('load',{
			positionName: $('#positionName').val()
		    });
	 }
		
		
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			
				
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			
		}
		function onClickRow(index) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append() {
			if (endEditing()) {
				
				$('#dg').datagrid('appendRow', {});
				editIndex = $('#dg').datagrid('getRows').length - 1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
						editIndex);
				
			}
		}
		function removeit() {
			if (editIndex == undefined) {
				return
			}
			$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
					editIndex);
			editIndex = undefined;
		}

		function reject() {
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}

		function accept() {
			if (endEditing()) {

				var inserted = $('#dg').datagrid('getChanges', "inserted");
				var deleted = $('#dg').datagrid('getChanges', "deleted");
				var updated = $('#dg').datagrid('getChanges', "updated");
				var effectRow = new Object();
				if (inserted.length) {
					effectRow["inserted"] = JSON.stringify(inserted);
				}
				if (deleted.length) {
					effectRow["deleted"] = JSON.stringify(deleted);
				}
				if (updated.length) {
					effectRow["updated"] = JSON.stringify(updated);
				}
				$.post("role_accept.action", effectRow,
						function(response) {
							
								$.messager.alert("提示", "保存成功！");
								$("#dg").datagrid('acceptChanges');
								 $('#dg').datagrid({url: 'role_list.action'});
							
						}, "JSON").error(function() {
					$.messager.alert("提示", "保存失败！");
					$('#dg').datagrid('rejectChanges');
				});
			 
				 
			}
		}
	</script>


</body>
</html>