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
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
			if($('#visibleSearch').combobox('getValue')=='Y'){
				$("#dg").datagrid('hideColumn', 'departureDate');
				}else{
					$("#dg").datagrid('showColumn', 'departureDate');
				}
			$('#dg').datagrid('load',{
				userIDSearch: $('#userIDSearch').val(),
				userNameSearch: $('#userNameSearch').val(),
				
				visibleSearch:$('#visibleSearch').combobox('getValue')
		    	});
		} 
		
		function myparser(s){
			if (s!=""&&s!=undefined ){
				var ss=s.split('-');
				var y=parseInt(ss[0],10);
				var m=parseInt(ss[1],10);
				var d=parseInt(ss[2],10);
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}

		}
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);

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
				
				var ed1 = $('#dg').datagrid('getEditor', {
					index : editIndex,
					field : 'departmentId'
				});
				var departmentName = $(ed1.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['departmentName'] = departmentName;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index,rowValue) {
			if(rowValue.visible=='Y'){
				$("#restore").hide();
				$('#delete').show();//.linkbutton('enable');
				$('#save').linkbutton('enable');
			}else{
				$("#delete").hide();
				$("#save").linkbutton("disable");
				$('#restore').show();//.linkbutton('enable');
				$('#dg').datagrid('rejectChanges');
				editIndex = undefined;
			}
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index);
					 if(rowValue.visible=='Y'){
						$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
						editIndex = index;
					 }else{
						 editIndex = undefined; 
					 }
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
			/* $('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
					editIndex);
			editIndex = undefined; */
			
			 var row = $('#dg').datagrid('getSelected');
	           if (row){
		               $.messager.confirm('Confirm','确定要禁用用户吗?',function(r){
		                   if (r){
		                       $.post('user_removeit.action',{userName:row.userID},function(result){
		                           if (result.success){
		                        	   editIndex = undefined;
		                           		alert("禁用成功");
		                               $('#dg').datagrid('reload');    // reload 
		                           } else {
		                               $.messager.show({    // show error message
		                                   title: 'Error',
		                                   msg: result.errorMsg
		                               });
		                           }
		                       },'json');
		                   }
		               });
	           }
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
				$.post("user_accept.action", effectRow,
						function(response) {
							if(response.error==undefined){
								$.messager.alert("提示", "保存成功！");
								$("#dg").datagrid('acceptChanges');
								$('#dg').datagrid('reload'); 
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
		function restoreUser(){
			
	      	 var row = $('#dg').datagrid('getSelected');
	           if (row){
		               $.messager.confirm('Confirm','确定要还原用户吗?',function(r){
		                   if (r){
		                       $.post('user_restore.action',{userName:row.userID},function(result){
		                           if (result.success){
		                           	alert("还原成功");
		                               $('#dg').datagrid('reload');    // reload 
		                           } else {
		                               $.messager.show({    // show error message
		                                   title: 'Error',
		                                   msg: result.errorMsg
		                               });
		                           }
		                       },'json');
		                   }
		               });
	           }
	      }
		
		$(function(){
			$('#restore').hide();
			$('#delete').hide();
			if($('#visibleSearch').combobox('getValue')=='Y'){
			$("#dg").datagrid('hideColumn', 'departureDate');
			}
		});
		
	</script>
<title><s:text name="portal.System_Management.userManagement"/></title>
</head>
<body>
<input type="hidden" name = "moduleId" id = "moduleId" value="${moduleId}"/>
<div class="easyui-layout" style="height:750px;">
	<div data-options="region:'north',title:'<s:text name="common.searchCriteria" />',collapsible:false" style="height:21%">
		<table style="width:100%">
			<tr>
				<td colspan="4" align="right">
					<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()"><s:text name="common.search"></s:text></a>
				</td>
			</tr>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td class="panel-header" style="width:25%" align="center">工号</td>
				<td style="width:25%"><input id="userIDSearch" class="easyui-textbox" style="width:90%"></td>
				<td class="panel-header" style="width:25%" align="center">帐号</td>
				<td style="width:25%"><input id="userNameSearch" class="easyui-textbox" style="width:90%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:25%" align="center"><s:text name="system.user.visible" /></td>
				<td style="width:25%"><input class="easyui-combobox" name="visibleSearch" id="visibleSearch" style="width:60%" value="${visibleSearch}"
						data-options="valueField:'key',
                                	  textField:'value',
									  required:true,
									  panelHeight: 'auto',
									  data:[
									{'key':'Y','value':'Y'},
									{'key':'A','value':'ALL'},
									{'key':'N','value':'N'}
								]"/></td>
			
			</tr>
		</table>
	</div>

	<div data-options="region:'center'" style="height:79%">
		<table id="dg" class="easyui-datagrid" title="<s:text name="system.user.userList" />"
		style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'user_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'userID',hidden:'true',width:10">User ID</th>
				<th
					data-options="field:'userName',sortable:'true',align:'center',
						editor:{type:'validatebox',options:{required:true}}">帐号</th>
				<th
					data-options="field:'fullName',sortable:'true',align:'center',
						editor:{type:'validatebox'}">姓名</th>		
								
				<th
					data-options="field:'hiddenPositionID', hidden:'true'">
					hidden</th>
				<th data-options="field:'workId',sortable:'true',align:'center',width:'10%',editor:'text'">工号</th>	
				<th
					data-options="field:'departmentId',sortable:'true',align:'center',width:'20%',
                         formatter:function(value,row){
                            return row.departmentName;
                        }, 
                        editor:{
                            type:'combobox',
                            options:{
                            	valueField:'key',
                                textField:'value',
                                method:'post',
                                editable:false,
                                url:'show_department_list.action',
                                required:true                     
                            }
                        }">单位
				</th>
				
				<th
					data-options="field:'positionID',sortable:'true',align:'center',width:'20%',
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
                                multiple:true,
                                url:'user_positionList.action',
                                required:true                     
                            }
                        }"><s:text name="system.user.position" />
				</th>
				
				<th
					data-options="field:'telephoneNumber',align:'center',
						editor:{type:'numberbox'}">
					工作电话
				</th>
				<!--<th
					data-options="field:'address',sortable:'true',align:'center',
						editor:'text'">
					<s:text name="system.user.address" />
				</th>
				 <th
					data-options="field:'IDNumber',sortable:'true',align:'center',
						editor:'text'">
					<s:text name="system.user.idNumber" />
				</th>-->
				<th
					data-options="field:'email',sortable:'true',align:'center',
					editor:{type:'validatebox',options:{email: '请输入正确格式的电子邮件',validType:'email'}}">
					<s:text name="system.user.email" />
				</th>
				<th
					data-options="field:'visible',sortable:'true',align:'center'">
					<s:text name="system.user.visible" />
				</th>
				<th
					data-options="field:'departureDate',sortable:'true',align:'center'">
					<s:text name="system.user.departureDate" />
				</th>
				<th
					data-options="field:'memo',sortable:'true',align:'center',
						editor:'text'">
					<s:text name="system.user.memo" />
				</th>
			</tr>
		</thead>
	</table>
	</div>
</div>


	

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="add"
			data-options="iconCls:'icon-add',plain:true,disabled:true" onclick="append()"><s:text name="common.append" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="save" 
			data-options="iconCls:'icon-save',plain:true,disabled:true" onclick="accept()"><s:text name="common.accept" /></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="delete"
			data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">禁用</a>	
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" id="restore"  onclick="restoreUser()">还原</a>	
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
	
	</script>


</body>
</html>