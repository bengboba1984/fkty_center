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
<title>Department Management</title>
</head>
<body>
	<table id="dg" class="easyui-datagrid" title="Row Editing"
		style="width: 348px; height: auto" remoteSort='false'
		data-options="rownumbers:true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#tb',url: 'department_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'departmentID',hidden:'true',width:150">Department
					ID</th>
				<th
					data-options="field:'departmentName',sortable:'true',width:160,align:'right',editor:{type:'validatebox',options:{required:true}}"><s:text name="system.department.departmentName" /></th>
				<th
					data-options="field:'departmentManager',sortable:'true',width:160,
                        formatter:function(value,row){
                            return row.departmentManagerName;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                            	valueField:'userID',
                                textField:'userName',
                                method:'post',
                                editable:false,
                                url:'department_userlist.action'
                            }
                        }"><s:text name="system.department.departmentManager" /></th>


			</tr>
		</thead>
	</table>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()"><s:text name="common.append" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()"><s:text name="common.remove" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()"><s:text name="common.accept" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()"><s:text name="common.reject" /></a>

	</div>

	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				var ed = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'departmentManager'
				});
				var managerName = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['departmentManagerName'] = managerName;
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
				$.post("department_accept.action", effectRow,
						function(response) {
							
								$.messager.alert("提示", "保存成功！");
								$("#dg").datagrid('acceptChanges');
								 $('#dg').datagrid({url: 'department_list.action'});
							
						}, "JSON").error(function() {
					$.messager.alert("提示", "保存失败！");
					$('#dg').datagrid('rejectChanges');
				});
			 
				 
			}
		}
	</script>


</body>
</html>