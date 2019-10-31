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
				testingDateBegin: $('#testingDateBegin').datebox('getValue'),
				testingDateEnd: $('#testingDateEnd').datebox('getValue'),
				testTypeSearch:$('#testTypeSearch').combobox('getValue'),
				accountSearch:$('#accountSearch').val(),
				testerSearch:$('#testerSearch').val(),
				stbIDSearch:$('#stbIDSearch').val()
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
		
		}
		function doDownload(){
			
			
		}
		function removeit(){
			var checkedItems = $('#dg').datagrid('getChecked');
			var ids=' ';
			$.each(checkedItems, function(index, item){
				ids +=item.fileId+",";
			}); 
			if(ids.length==1){
				alert("请选择需要删除的文件");
				return false;
			}
			ids = ids.substr(0,ids.length-1);
			$.ajax({
		        url: 'ftp_file_delete.action',
		        type: 'post',
		        data:{
		        	fileId: ids
			 },
			 dataType: 'json',
			 error: function(){alert('删除文件失败');},
		        success: function (result) {
		        	if(result.success=="success"){
		        	$.messager.alert("提示", "删除成功！");
		        	}else if(result.warning=="warning"){
		        	$.messager.alert("提示", result.mess);
		        	}
		        }
		    }); 
		}
		
		
	</script>
<title><s:text name="portal.System_Management.userManagement"/></title>
</head>
<body>
<input type="hidden" name = "moduleId" id = "moduleId" value="${moduleId}"/>
<form id="fileList" method="post">
<div class="easyui-layout" style="height:750px;">
	<div data-options="region:'north',title:'<s:text name="common.searchCriteria" />',collapsible:false" style="height:21%">
		<table style="width:100%">
			<tr>
			<td colspan="6"  align="right">
				<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()"><s:text name="common.search"></s:text></a>
				
					<a href="#" class="easyui-linkbutton" id="download" data-options="iconCls:'icon-download'" onClick="doDownload()">下载选中的文件</a>
					
					<a href="javascript:void(0)" class="easyui-linkbutton" id="delete" data-options="iconCls:'icon-remove'" onclick="removeit()"><s:text name="common.remove" /></a>
				</td></tr>
			<tr>
				<td class="panel-header" style="width:10%" align="center">时间</td>
				<td style="width:25%"><input id="testingDateBegin" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:40%"></input>--
				<input id="testingDateEnd" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:40%"></input></td>
				<td class="panel-header" style="width:10%" align="center">抓包类型</td>
				<td style="width:15%"><input class="easyui-combobox"  id="testTypeSearch" style="width:80%" value="${testTypeSearch}"
						data-options="url:'file_group_list.action?showAllFlag=1',
												method:'post',
                    						  	valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto'"/></td>
				<td class="panel-header" style="width:10%" align="center">装维单位</td>
				<td style="width:15%"><input id="111" class="easyui-textbox" style="width:80%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:10%" align="center">装维人员工号</td>
				<td style="width:25%"><input id="testerSearch" class="easyui-textbox" style="width:80%"></td>
				<td class="panel-header" style="width:10%" align="center">STBID</td>
				<td style="width:15%"><input id="stbIDSearch" class="easyui-textbox" style="width:80%"></td>
			<td class="panel-header" style="width:10%" align="center">宽带帐号</td>
				<td style="width:25%"><input id="accountSearch" class="easyui-textbox" style="width:80%"></td>
			</tr>
			
		</table>
	</div>

	<div data-options="region:'center'" style="height:79%">
		<table id="dg" class="easyui-datagrid" title="文件列表"
		style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls:'icon-edit',singleSelect:false,pagination:true,rownumbers:true,url:'download_ftp_file_data_list.action',method:'post',onClickRow:onClickRow">
		<thead>
			<tr>
				<th data-options="field:'fileId'"  checkbox="true"></th>
				<th data-options="field:'type',sortable:'true',align:'center'">抓包类型</th>
				<th data-options="field:'account',sortable:'true',align:'center'">宽带帐号</th>
				<th data-options="field:'createdDate',sortable:'true',align:'center'">时间</th>		
				<th data-options="field:'tester',sortable:'true',align:'center'">工号</th>
				<th data-options="field:'stbId',sortable:'true',align:'center',width:'20%'">STBID</th>
				<th data-options="field:'fileName',sortable:'true',align:'center'">文件名</th>
			</tr>
		</thead>
	</table>
	</div>
</div> 
</form>
<script type="text/javascript">
if ($.fn.datagrid){
    $.fn.datagrid.defaults.pageSize = 25;//这里一定要用datagrid.defaults.pageSize，用pagination.defaults.pageSize一直不行
    $.fn.datagrid.defaults.pageList = [15,25,35,45,55];//这里一定要有，不然上面的也不起效
}
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