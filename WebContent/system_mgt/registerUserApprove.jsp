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

<script type="text/javascript">
	var editIndex = undefined;
		function doSearch(){
			
			$('#dg').datagrid('load',{
				userIDSearch: $('#userIDSearch').val(),
				userNameSearch: $('#userNameSearch').val()
		    	});
		} 
		
		function endEditing() {
			if (editIndex == undefined) {
				return true;
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				var ed = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'positionID'
				});
				var positionName = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['positionName'] = positionName;
				//$('#dg').datagrid('endEdit', editIndex);
				/* editIndex = undefined;
				return true; */
			} else {
				return false;
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				ed = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'registerStatusKey'
				});
				var registerStatusValue = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['registerStatusValue'] = registerStatusValue;
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

		function reject() {
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}

		function accept() {
			if (endEditing()) {

				var updated = $('#dg').datagrid('getChanges', "updated");
				var effectRow = new Object();
				
				if (updated.length) {
					effectRow["updated"] = JSON.stringify(updated);
				}
				$.post("register_user_approve.action", effectRow,
						function(response) {
							if(response.error==undefined){
								$.messager.alert("提示", "保存成功！");
								$("#dg").datagrid('acceptChanges');
								$('#dg').datagrid({url: 'register_user_list.action'});
							}else{
								$.messager.alert("提示", "保存失败！\n"+response.error);
								$('#dg').datagrid('rejectChanges');
							}	
						}, "JSON").error(function() {
								$.messager.alert("提示", "保存失败！");
								$('#dg').datagrid('rejectChanges');
							});
			 
				 
			}
		}
		
		$(function(){
			
		});
		
	</script>
<title>用户审核</title>
</head>
<body>
<div class="easyui-layout" style="height:800px;">
	<div data-options="region:'north',title:'<s:text name="common.searchCriteria" />',collapsible:false" style="height:15%">
		<table style="width:100%">
			<tr>
				<td colspan="4" align="right">
					<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search'" onClick="doSearch()"><s:text name="common.search"></s:text></a>
				</td>
			</tr>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td class="panel-header" style="width:25%" align="center">单位全称</td>
				<td style="width:25%"><input id="userIDSearch" class="easyui-textbox" style="width:90%"></td>
				<td class="panel-header" style="width:25%" align="center"><s:text name="system.user.userName" /></td>
				<td style="width:25%"><input id="userNameSearch" class="easyui-textbox" style="width:90%"></td>
			</tr>
		</table>
	</div>

	<div data-options="region:'center'" style="height:87%">
		<table id="dg" class="easyui-datagrid" title="<s:text name="system.user.userList" />"
		style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'register_user_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'userID',hidden:'true',width:10">User ID</th>
				<th data-options="field:'userName',sortable:'true',align:'center'"><s:text name="system.user.userName" /></th>
				<th data-options="field:'companyName',sortable:'true',align:'center'">单位全称</th>
				<th data-options="field:'position',sortable:'true',align:'center'">职务</th>
				<th data-options="field:'jobTitle',sortable:'true',align:'center'">职称</th>
				<th data-options="field:'telephoneNumber',sortable:'true',align:'center'">工作电话</th>
				<th data-options="field:'phoneNumber',sortable:'true',align:'center'">手机</th>
				<th data-options="field:'email',sortable:'true',align:'center'">电子邮件</th>
				<th data-options="field:'purposeData',sortable:'true',align:'center'">数据用途</th>
				<th data-options="field:'positionID',sortable:'true',align:'center',width:'20%',
                         formatter:function(value,row){
                            return row.positionName;
                        }, 
                        editor:{
                            type:'combobox',
                            options:{
                            	valueField:'key',
                                textField:'value',
                                method:'post',
                                editable:false,
                                url:'user_positionList.action',
                                required:true                     
                            }
                        }"><s:text name="system.user.position" />
				</th>
			<th data-options="field:'registerStatusKey',sortable:'true',align:'center',width:'20%',
                         formatter:function(value,row){
                            return row.registerStatusValue;
                        }, 
                        editor:{
                            type:'combobox',
                            options:{
                            	valueField:'key',
                                textField:'value',
                                method:'post',
                                editable:false,
                                url:'register_status_list.action',
                                required:true                     
                            }
                        }">审核
				</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="save" 
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"  
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>	
			
	</div>

</body>
</html>