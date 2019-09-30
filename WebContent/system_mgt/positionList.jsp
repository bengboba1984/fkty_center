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
<title>Position Management</title>
</head>
<body>
	<div class="easyui-layout" style="height:800px;">
		<div data-options="region:'north',title:'<s:text name="common.searchCriteria"></s:text>',collapsible:false" style="height:20%">
				<table style="width:100%">
					<tr>
						<td colspan="4" align="right">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"	onClick="doSearch()"><s:text name="common.search"></s:text></a>
						</td>
					</tr>
					<tr>
						<td class="panel-header" style="width:25%" align="center"><s:text name="system.position.positionName"></s:text></td>
						<td style="width:25%"> <input id="positionName" class="easyui-textbox" style="width:90%"></td>
						<td class="panel-header" style="width:25%" align="center"><s:text name="system.department.departmentName"></s:text></td>
						<td style="width:25%">
							<input id="departmentName" class="easyui-textbox" style="width:90%">
						</td>
					</tr>
					
				</table>
				
		</div>
		<div data-options="region:'center'" style="height:80%">
					<table id="dg" class="easyui-datagrid" title="<s:text name="system.position.gridListTitle"></s:text>"
							style="width: 100%; height:100%" remoteSort="false"
							data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'position_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
<!-- 				<th data-options="field:'positionID',hidden:true,width:100">Position -->
<!-- 					ID</th> -->
				<th
					data-options="field:'positionName',sortable:'true',width:160,editor:{type:'validatebox',options:{required:true}}"><s:text name="system.position.positionName" /></th>
				<th
					data-options="field:'departmentID',sortable:'true',width:160,
                         formatter:function(value,row){
                            return row.departmentName;
                        }, 
                        editor:{
                            type:'combobox',
                            options:{
                            	valueField:'departmentID',
                                textField:'departmentName',
                                method:'post',
                                editable:false,
                                url:'position_departmentList.action',
                                required:true
                            }
                        }"><s:text name="system.position.department" />
					</th>
				<th data-options="field:'positionID',width:80,align:'center',formatter:formatOper"><s:text name="common.operator" /></th>

			</tr>
		</thead>
	</table>
		</div>
	
</div>	

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()"><s:text name="common.append" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()"><s:text name="common.remove" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()"><s:text name="common.accept" /></a>
		<%-- <a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()"><s:text name="common.reject" /></a> --%>
	</div>

	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">Module List</div>

           <ul id="tt"></ul>

    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" id="saveRoleButton" class="easyui-linkbutton c6" iconCls="icon-ok" style="width:90px"><s:text name="common.save" /></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px"><s:text name="common.cancel" /></a>
    </div>
	




	<script type="text/javascript">
	
	function doSearch(){
		$('#dg').datagrid('load',{
			positionName: $('#positionName').val(),
			departmentName:$("#departmentName").val()
		    });
	 }
		function saveRole(val){
			var parameterList = new Object();
			var checkedModuleList=$('#tt').tree('getChecked');
			parameterList["saveModuleList"]=JSON.stringify(checkedModuleList);
			parameterList["positionID"]=val;
			$.post("position_assignRole.action", parameterList,
					function(response) {
						if(response.error==undefined){
							$.messager.alert("提示", "保存成功！");
							$('#tt').tree({url:'position_ModuleList.action?positionID='+val});
						}else{
							$.messager.alert("提示", "保存失败！\n"+response.error);
						}	
					}, "JSON").error(function() {
							$.messager.alert("提示", "保存失败！");
						});
			
			
			
		}
		function assignModule(val){
			$('#dlg').dialog('open').dialog('setTitle','Assign Module');
			$('#tt').tree({  
				url:'position_ModuleList.action?positionID='+val,
				checkbox:'true',
				animate:'true',
				method:'post',
				onlyLeafCheck:'true'
// 				loadFilter: function(rows){
// 					        return convert(rows);
// 					    }
			}); 
			$('#saveRoleButton').attr("onclick","saveRole("+val+");");
		}
		function formatOper(val,row,index){
			var flag=true;
			var rows=$('#dg').datagrid('getChanges', 'inserted');
 			for(var i=0; i<rows.length; i++){
 				if(index==$('#dg').datagrid('getRowIndex', rows[i])){
 					flag=false;
 					break;
 				}	
 			}			
			if(flag)
				return '<a href="#" onclick="assignModule('+val+')">权限</a>';
			else 
				return '';
		}
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				var ed = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'departmentID'
				});
				var departmentName = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['departmentName'] = departmentName;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
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
				$('#dg').datagrid('getColumnOption', 'positionID').formatter=function(val,row,index){return '';}
				$('#dg').datagrid('appendRow', {});
				editIndex = $('#dg').datagrid('getRows').length - 1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
						editIndex);
				$('#dg').datagrid('getColumnOption', 'positionID').formatter=formatOper;
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
				$.post("position_accept.action", effectRow,
						function(response) {
							
								$.messager.alert("提示", "保存成功！");
								$("#dg").datagrid('acceptChanges');
								 $('#dg').datagrid({url: 'position_list.action'});
							
						}, "JSON").error(function() {
					$.messager.alert("提示", "保存失败！");
					$('#dg').datagrid('rejectChanges');
				});
			 
				 
			}
		}
	</script>


</body>
</html>