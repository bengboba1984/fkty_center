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
			if($('#visibleSearch').combobox('getValue')=='Y'){
				$("#dg").datagrid('hideColumn', 'departureDate');
				}else{
					$("#dg").datagrid('showColumn', 'departureDate');
				}
			$('#dg').datagrid('load',{
				userIDSearch: $('#userIDSearch').val(),
				userNameSearch: $('#userNameSearch').val(),
				hireDateSearch: $('#hireDateSearch').datebox('getValue'),
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
		
		function onClickRow(index,rowValue) {
			if(rowValue.visible=='Y'){
				$("#restore").linkbutton("disable");
				$('#delete').linkbutton('enable');
				$('#save').linkbutton('enable');
			}else{
				$("#delete").linkbutton("disable");
				$("#save").linkbutton("disable");
				$('#restore').linkbutton('enable');
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

		$(function(){
			
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
				<td class="panel-header" style="width:25%" align="center">时间</td>
				<td style="width:25%"><input id="userIDSearch" class="easyui-textbox" style="width:90%"></td>
				<td class="panel-header" style="width:25%" align="center">测试类型</td>
				<td style="width:25%"><input id="userNameSearch" class="easyui-textbox" style="width:90%"></td>
				<td class="panel-header" style="width:25%" align="center">装维单位</td>
				<td style="width:25%"><input id="userNameSearch" class="easyui-textbox" style="width:90%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:25%" align="center">装维人员</td>
				<td style="width:25%"><input id="hireDateSearch" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:30%"></input></td>
				<td class="panel-header" style="width:25%" align="center">测试序列号</td>
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
			<td class="panel-header" style="width:25%" align="center">宽带帐号</td>
				<td style="width:25%"><input id="userNameSearch" class="easyui-textbox" style="width:90%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:25%" align="center">STBID</td>
				<td style="width:25%"><input id="userIDSearch" class="easyui-textbox" style="width:90%"></td>
				<td colspan="2" align="right">
					<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()"><s:text name="common.search"></s:text></a>
				</td>
				<td colspan="2" align="right">
					<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()">导出</a>
				</td>
			</tr>
		</table>
	</div>

	<div data-options="region:'center'" style="height:79%">
		<table id="dg" class="easyui-datagrid" title="<s:text name="system.user.userList" />"
		style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'testing_result_template_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'userID',hidden:'true',width:10">result ID</th>
				<th
					data-options="field:'userName',sortable:'true',align:'center'">测试帐号</th>
				<th
					data-options="field:'fullName',sortable:'true',align:'center',
						editor:{type:'validatebox'}">时间</th>		
				<th
					data-options="field:'gender',sortable:'true',align:'center'">工号</th>
				<th
					data-options="field:'hireDate',sortable:'true',align:'center'">单位</th>
				
				<th data-options="field:'workId',sortable:'true',align:'center',width:'10%'">宽带帐号</th>	
				<th
					data-options="field:'departmentId',sortable:'true',align:'center',width:'20%'">STBID</th>
				<th data-options="field:'position',sortable:'true',align:'center',editor:'text'">测试类型</th>
				<th data-options="field:'jobTitle',sortable:'true',align:'center',editor:'text'">PING</th>
				<th data-options="field:'phoneNumber',sortable:'true',align:'center',editor:{type:'numberbox'}">TRACE</th>
				
				<th data-options="field:'phoneNumber',sortable:'true',align:'center',editor:{type:'numberbox'}">测速</th>
				<th data-options="field:'phoneNumber',sortable:'true',align:'center',editor:{type:'numberbox'}">WEB</th>
				<th data-options="field:'phoneNumber',sortable:'true',align:'center',editor:{type:'numberbox'}">DNS</th>
			</tr>
		</thead>
	</table>
	</div>
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