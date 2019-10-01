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
<script type="text/javascript" src="js/datagrid-groupview.js"></script>
<title></title>
</head>
<body>
<input type="hidden" name = "moduleId" id = "moduleId" value="${moduleId}"/>
<input type="hidden" id = "action" value=""/>
	<div class="easyui-layout" style="height:750px;" data-options="border:false">
			<table id="dg" class="easyui-datagrid" title="" style="width: 100%; height:99%" remoteSort="false"
							data-options="fitColumns:'true',iconCls: 'icon-edit',singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'testing_template_data_list.action',method: 'post'">
		<thead>
			<tr>
				<th data-options="field:'testingTemplateId',sortable:'true',hidden:'true'">编号</th>
				<th data-options="field:'templateName',sortable:'true'">名称</th>
				<th data-options="field:'testTypeValue',sortable:'true'">测试类型</th>
				<th data-options="field:'testType',hidden:'true'">User ID</th>
				<th data-options="field:'rankClass',sortable:'true'">排序级别</th>
				<th data-options="field:'testTimeout',sortable:'true'">测试超时</th>
				<th data-options="field:'testExecuteCount',sortable:'true'">执行次数</th>
				<th data-options="field:'testInterval',sortable:'true'">测试周期</th>
				<th data-options="field:'testInterval',sortable:'true'">测试时长</th>
				<th data-options="field:'testInterval',sortable:'true'">链接功能</th>
				<th data-options="field:'description',sortable:'true'">描述</th>
				<th data-options="field:'testingTemplateGroupId',hidden:'true',width:10">testingTemplateGroupId</th>
			</tr>
		</thead>
	</table>
</div>	

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="add"
			data-options="iconCls:'icon-add',plain:true,disabled:true" onclick="append()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="delete"
			data-options="iconCls:'icon-remove',plain:true,disabled:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="attribute"
			data-options="iconCls:'icon-save',plain:true,disabled:true" onclick="edit()">属性</a>
		 <a href="javascript:void(0)" class="easyui-linkbutton" id="refresh"
			data-options="iconCls:'icon-undo',plain:true,disabled:true" onclick="refresh()">刷新</a> 
	</div>

<div id="dlg-function" class="easyui-dialog"  closed="true"  style="width:500px">
        <ul class="easyui-tree" id="treeGroup" >
            <li>
                <span>所有授权的测试模板</span>
                <ul>
                    <li data-options="state:'closed'">
                        <span id="-1">网络层</span>
                        <ul>
                            <li id="28" >
                                <span>PING测试-基于ICMP协议的网络基础性能测试</span>
                            </li>
                            <li id="29">
                                <span>Traceroute测试-测试路由解析路径和相关网络性能</span>
                            </li>
                            <li id="30">
                                <span>网速测试-测试网络上下行带宽</span>
                            </li>
                        </ul>
                    </li>
                    <li data-options="state:'closed'">
                        <span id="-1">业务层</span>
                        <ul>
                            <li id="31">DNS解析测试-测试DNS服务器解析相关性能</li>
                            <li id="32">Web浏览测试-测试网页访问质量和相关性能</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div id="dlg-basic" class="easyui-dialog" style="width:800px"
            closed="true" buttons="#dlg-warning-buttons">
        <form id="fm-warning" method="post" novalidate style="margin:0;padding:20px 50px"> 
        <input type="hidden" name="testingTemplateId" id="testingTemplateId" value="${testingTemplateId}" />
        <div class="easyui-tabs" style="width:700px;height:500px" fit="true">
        <div title="基础信息" style="padding:0px">
            <table style="width: 100%">
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">排序级别</td>
           <td style="width: 50%">
           <input id="rankClass" class="easyui-numberspinner" type="text" data-options="labelPosition:'top',increment:1"  value="1000" /> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center">组</td>
           <td style="width: 50%">
           <input id="testGroupId" class="easyui-combobox" value="${testGroupId}"
           data-options="url:'test_group_list.action?showAllFlag=1',
												method:'post',
                    						  	valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	required:'true'" ></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center">测试类型</td>
           <td style="width: 50%">
           <input id="testType" class="easyui-combobox"  value="${testType}"
								data-options="url:'test_type_list.action',
												method:'post',
                    						  	valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	required:'true'" ></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center">名称</td>
           <td style="width: 50%">
           <input id="templateName" class="easyui-textbox" type="text" value=""  /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center">描述</td>
           <td style="width: 50%">
           <input id="description" class="easyui-textbox" type="text" value="" /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center">测试超时</td>
           <td style="width: 50%">
           <input id="testTimeout" class="easyui-numberspinner" type="text" data-options="labelPosition:'top',increment:1"  value="20"/>秒</td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center">测试周期</td>
           <td style="width: 50%">
           <input id="testInterval" class="easyui-numberspinner" type="text" data-options="labelPosition:'top',increment:1" value="1" />分钟</td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center">执行次数</td>
           <td style="width: 50%">
           <input id="testExecuteCount" class="easyui-numberspinner" data-options="labelPosition:'top',increment:1" type="text" value="1" />次</td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center">测试时长</td>
           <td style="width: 50%">
           <input id="testingDuration" class="easyui-textbox" type="text" value="无限制" /></td>
       </tr>
    </table>
        </div>
        <div title="测试参数" style="padding:0px;width:700px;height:500px">
            <iframe id="iframe_test" scrolling="auto" frameborder="0"  style="width:100%;height:90%"></iframe>
        </div>
    </div>     
        
        </form>
    </div>
    <div id="dlg-warning-buttons">
    	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" data-options="disabled:true" id="targetButton" onclick="settingTestTarget()" style="width:90px">测试目标</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveTestingTemplate()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelBasic()" style="width:90px">取消</a>
    </div>
<div id="dlg-test-target" class="easyui-dialog" style="width:800px" 
            closed="true" buttons="#dlg-test-target-buttons">
            <div class="easyui-layout" data-options="fit:false" style="width:100%;height:600px;" >
                <div data-options="region:'north',split:false" style="width:100%;height:515px;" border="false" >
                <table class="easyui-datagrid" title="" style="height:500px;border:1px" id="targetGrid"
            	data-options="
            	url:'testing_template_target_data_list.action',
                singleSelect:true,
                collapsible:false,
                rownumbers:false,
                fitColumns:true,
                view:groupview,
                groupField:'targetTypeValue',
                groupFormatter:function(value,rows){
                    return value + '  (' + rows.length + '项)';
                }
            ">
	       	 <thead>
	            <tr>
	                <th data-options="field:'name',width:80">名称</th>
	                <th data-options="field:'nodeIp',width:100">目标</th>
	                <th data-options="field:'sortLevel',width:80,align:'right'">排序</th>
	                <th data-options="field:'active',width:80,formatter:formatActive,align:'right'">有效</th>
	                <th data-options="field:'targetType',hidden:'true',align:'right'">type</th>
	                <th data-options="field:'testingTemplateTargetId',hidden:'true',align:'right'">testingTemplateTargetId</th>
	                
	            </tr>
	        </thead>
   		 </table>
          </div>
          <div data-options="region:'center'" style="width:100%;height:20px"  border="false" >
               <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="addTargetType()" style="width:90px">添加类别</a>
        	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="deleteTestTarget()" style="width:90px">删除</a></div>
          </div>
    </div>
    <div id="dlg-test-target-buttons">
    	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="showAddTargetDiv()" style="width:90px">增加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="cancelTarget('dlg-test-target')" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelTarget('dlg-test-target')" style="width:90px">取消</a>
    </div>
	<div id="dlg-tartget-type" class="easyui-dialog"  closed="true"  style="width:500px">
        <table style="width: 100%">
       		<tr>
				<td class="panel-header" style="width: 20%" align="center">类别名字</td>
           <td style="width: 60%">
           <input id="add_targetType" class="easyui-textbox" type="text"  />
           <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveTargetType()" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelTargetType('dlg-test-target')" style="width:90px">返回</a> </td>
       </tr>
       </table>
    </div>
    <div id="dlg-testing-target-add" class="easyui-dialog"  buttons="#dlg-testing-target-add-buttons" closed="true"  style="width:500px">
       <table style="width: 100%">
       <tr>
			<td class="panel-header" style="width: 30%" align="center">类别</td>
           <td style="width: 50%">
           <input id="targetType" class="easyui-combobox"  style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	method:'post',
                    						  	url:'tartget_type_list.action'" > </td>
       	</tr>
       	<tr>
			<td class="panel-header" style="width: 30%" align="center">名称</td>
           <td style="width: 50%">
           <input id="name" class="easyui-textbox" type="text"  /> </td>
       	</tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center">目标</td>
           <td style="width: 50%">
           <input id="nodeIp" class="easyui-textbox" type="text"  /> </td>
       	</tr>
       	<tr>
			<td class="panel-header" style="width: 30%" align="center">排序</td>
           <td style="width: 50%">
           <input id="sortLevel" class="easyui-textbox" type="text"  /> </td>
       	</tr>
       	<tr>
			<td class="panel-header" style="width: 30%" align="center">有效</td>
           <td style="width: 50%">
           <input id="active" class="easyui-combobox" value="1" style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	data:[{'key':'1','value':'是'},{'key':'0','value':'否'}]" ></td>
       	</tr>
       </table>
    </div>
    <div id="dlg-testing-target-add-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="addTargetData()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancelTarget('dlg-testing-target-add')" style="width:90px">取消</a>
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
	
	$('#treeGroup').tree({
		onClick: function(node){ 
			if(node.id>0){
				$('#testType').combobox('setValue', node.id);
				$("#testType").combobox('readonly',true);
				var url ='';
				if(node.id=='28'){
					url='testing_template_arameter_ping_show.action';
				}else if(node.id=='29'){
					url='testing_template_arameter_trace_show.action';
				}else if(node.id=='30'){
					url='testing_template_arameter_speed_show.action';
				}else if(node.id=='31'){
					url='testing_template_arameter_dns_show.action';
				}else if(node.id=='32'){
					url='testing_template_arameter_web_show.action';
				}
				$("#iframe_test").attr('src',url);
				distplayTargetButton();
				$('#dlg-basic').dialog('open').dialog('center').dialog('setTitle','新建项 - 测试模板');
				$('#dlg-function').dialog('close');
			}
						
		}
	});
	function distplayTargetButton(){
		if($('#testingTemplateId').val() == null || $('#testingTemplateId').val() == ''
			|| $('#testingTemplateId').val() == 'null'){
			$("#targetButton").linkbutton("disable");
		}else{
			$('#targetButton').linkbutton('enable');
		}
	}
	function doSearch(){
		$('#dg').datagrid('load',{
			positionName: $('#positionName').val()
		    });
	 }
	function append() {
		$('#action').val("insert");
		$('#dlg-function').dialog('open').dialog('center').dialog('setTitle','选择测试模板');
	}
	
	function cancelBasic(){
		$('#dlg-basic').dialog('close');
		$('#treeGroup').dialog('close');
	}
	function cancelTarget(divName){
		//$('#dlg-test-target').dialog('close');
		$('#'+divName).dialog('close');
		
	}
	function settingTestTarget(){
		$('#targetGrid').datagrid('load');
		$('#dlg-test-target').dialog('open').dialog('center').dialog('setTitle','测试目标管理');
	}
	function addTargetType() {
		$('#dlg-tartget-type').dialog('open').dialog('center').dialog('setTitle','添加类别');
	}
	function showAddTargetDiv() {
		$('#dlg-testing-target-add').dialog('open').dialog('center').dialog('setTitle','增加测试目标');
	}
	function cancelTargetType(){
		$('#dlg-tartget-type').dialog('close');
	}
	function addTargetData(){
		var targetType=$('#targetType').combobox('getValue');
		var name = $('#name').val();
		var nodeIp = $('#nodeIp').val();
		var  sortLevel = $('#sortLevel').val();
		var active=$('#active').combobox('getValue');
		//var item =  {"targetTypeValue":targetType,"targetType":targetType,"name":name,"nodeIp":nodeIp,"sortLevel":sortLevel,"active":active};alert(3);
		 var url ='testing_target_insert.action?targetType='+targetType+'&';
		if (name != null && name != "") {
			url = url + "name=" + name + "&";
		}
		if (nodeIp != null && nodeIp != "") {
			url = url + "nodeIp=" + nodeIp + "&";
		}
		if (sortLevel != null && sortLevel != "") {
			url = url + "sortLevel=" + sortLevel + "&";
		}
		if (active != null && active != "") {
			url = url + "active=" + active + "&";
		}
		url = encodeURI(url);
		var effectRow = new Object();
		$.post(encodeURI(url),
				effectRow,function(response) {
			if (response.success=='success') {
				alert('新增测试目标成功！');
				$('#testingTemplateId').val(response.templateId);
				$('#targetGrid').datagrid('load',{
					testingTemplateId: response.templateId
			    	});
				cancelTarget('dlg-testing-target-add');
			} else{
			alert('新增测试目标失败！' + response.error);
		}
	}, "JSON").error(function() {
		alert("新增测试目标失败！");
	}); 
		
		//data.push(item);alert(4);
		//$('#targetGrid').datagrid('reload');
		//$('#targetGrid').datagrid('appendRow',{targetType:targetType,name:name,nodeIp:nodeIp,sortLevel:sortLevel,active:active});
		
	}
	function deleteTestTarget(){
		 var row = $('#targetGrid').datagrid('getSelected');
         if (row){
        	 var url = "testing_target_delete.action?testingTemplateTargetId="+row.testingTemplateTargetId;
        	 url = encodeURI(url);
     		var effectRow = new Object();
     		$.post(encodeURI(url),
     				effectRow,function(response) {
     			if (response.success=='success') {
     				alert('删除测试目标成功！');
     				$('#targetGrid').datagrid('load',{
     					testingTemplateId:$('#testingTemplateId').val()
     			    	});
     			} else{
     			alert('删除测试目标失败！' + response.error);
     		}
     	}, "JSON").error(function() {
     		alert("删除测试目标失败！");
     	}); 
         }else{
        	 alert("请先选择测试目标！");
        	 return false;
         }
	}
	function saveTargetType(){
		var url ='testing_target_type_insert.action?targetType='+$('#add_targetType').val();
		url = encodeURI(url);
		var effectRow = new Object();
		$.post(encodeURI(url),
				effectRow,function(response) {
			if (response.success=='success') {
				$("#targetType").combobox('reload');
				alert('新增类别成功！');
				cancelTargetType();
			} else{
			alert('新增类别失败！' + response.error);
		}
	}, "JSON").error(function() {
	alert("保存类别失败！");
	});
}
	function saveTestingTemplate(){
		var url = "";
		var action =  $('#action').val();
		var mess = "新增";
		if (action == 'update') {
			mess = "更新";
		}
		/*if ($('#testingTemplateId').val() == null || $('#testingTemplateId').val() == ''
				|| $('#testingTemplateId').val() == 'null') {
			action = "insert";
		} else {
			action = "update";
		} */
		url = encodeURI(makeUrl(action));
		var effectRow = new Object();
		$.post(encodeURI(url),function(response) {
							if (response.success=='success') {
								var table ='';
								var subUrl = '';
								if(response.testType=='28'){
									table='ping';
									subUrl = window.frames["iframe_test"].window.makePingUrl('');
								}else if(response.testType=='29'){
									table='trace';
									subUrl = window.frames["iframe_test"].window.makeTraceUrl('');
								}else if(response.testType=='30'){
									table='speed';
									subUrl = window.frames["iframe_test"].window.makeSpeedUrl('');
								}else if(response.testType=='31'){
									table='dns';
									subUrl = window.frames["iframe_test"].window.makeDnsUrl('');
								}else if(response.testType=='32'){
									table='web';
									subUrl = window.frames["iframe_test"].window.makeWebUrl('');
								}
								
								if (action == 'update') {
									url = "testing_template_arameter_"+table+"_update.action?";
								} else {
									url = "testing_template_arameter_"+table+"_insert.action?";
								}
								url = url + "testingTemplateId= "+response.templateId+"&";
								url = url + subUrl;alert(url);
								$.post(encodeURI(url),
										effectRow,function(response2) {
													if (response2.success=='success') {
														alert(mess+'成功！');
														$('#dg').datagrid('load');
														$('#dlg-basic').dialog('close');
													}else{
														alert(mess+'失败！' + response2.error);
													}
												}, "JSON").error(function() {
													 $.post('testing_template_removeit.action',{
								                    	   testingTemplateId:response.templateId,
								                       		testType:response.testType},function(result){
								                           //do nothing
								                       },'json');
											alert(mess+"失败！");

										});
							}else{
								alert(mess+'失败！' + response.error);
							}
						}, "JSON").error(function() {
					alert(mess+"失败！");

				});
		
	}	
	function makeUrl(todo){
		var url = "";
		if (todo == 'update') {
			url = "testing_template_update.action?testingTemplateId="
					+ $('#testingTemplateId').val() + "&";
		} else {
			url = "testing_template_insert.action?testingTemplateId="
				+ $('#testingTemplateId').val() + "&";
		}
		var templateName = $('#templateName').val();
		var description = $('#description').val();
		var testTimeout = $('#testTimeout').numberspinner('getValue');
		var testInterval = $('#testInterval').numberspinner('getValue');
		var testExecuteCount = $('#testExecuteCount').numberspinner('getValue');
		var rankClass = $('#rankClass').numberspinner('getValue');
		var testingDuration = $('#testingDuration').val();
		
		var testGroupId=$('#testGroupId').combobox('getValue');
		var testType = $('#testType').combobox('getValue');
		if (templateName != null && templateName != "") {
			url = url + "templateName=" + templateName + "&";
		}
		if (testGroupId != null && testGroupId != "") {
			url = url + "testGroupId=" + testGroupId + "&";
		}
		if (testType != null && testType != "") {
			url = url + "testType=" + testType + "&";
		}
		if (description != null && description != "") {
			url = url + "description=" + description + "&";
		}

		if (testTimeout != null && testTimeout != "") {
			url = url + "testTimeout=" + testTimeout + "&";
		}
		if (testInterval != null && testInterval != "") {
			url = url + "testInterval=" + testInterval + "&";
		}
		if (testExecuteCount != null && testExecuteCount != "") {
			url = url + "testExecuteCount=" + testExecuteCount + "&";
		}
		if (rankClass != null && rankClass != "") {
			url = url + "rankClass=" + rankClass + "&";
		}
		if (testingDuration != null && testingDuration != "") {
			url = url + "testingDuration=" + testingDuration + "&";
		}
		url = url.substr(0, url.length - 1);
		return url;
	}
	function refresh(){
		
		$('#dg').datagrid('load');
		
	}
	
	function removeit() {
		
		 var row = $('#dg').datagrid('getSelected');
           if (row){
	               $.messager.confirm('Confirm','确定要删除选中的测试模板吗?',function(r){
	                   if (r){
	                       $.post('testing_template_removeit.action',{
	                    	   testingTemplateId:row.testingTemplateId,
	                       		testType:row.testType},function(result){
	                           if (result.success=='success'){
	                           		alert("删除成功");
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
	function formatActive(val,row){
        if (val =='1'){
            return '是';
        } else {
            return '否';
        }
    }
	function edit(){
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#action').val("update");
        	$('#templateName').textbox('setValue',row.templateName);
    		$('#description').textbox('setValue',row.description);
    		$('#testTimeout').numberspinner('setValue',row.testTimeout);
    		$('#testInterval').numberspinner('setValue',row.testInterval);
    		$('#testExecuteCount').numberspinner('setValue',row.testExecuteCount);
    		$('#rankClass').numberspinner('setValue',row.rankClass);
    		$('#testingDuration').val(row.testingDuration);
    		$('#testingTemplateId').val(row.testingTemplateId);
    		$('#testGroupId').combobox('setValue',row.testingTemplateGroupId);
    		$('#testType').combobox('setValue',row.testType);
    		if(row.testType=='28'){
				url='testing_template_arameter_ping_edit_show.action?testingTemplateId='+row.testingTemplateId;
			}else if(row.testType=='29'){
				url='testing_template_arameter_trace_edit_show.action?testingTemplateId='+row.testingTemplateId;
			}else if(row.testType=='30'){
				url='testing_template_arameter_speed_edit_show.action?testingTemplateId='+row.testingTemplateId;
			}else if(row.testType=='31'){
				url='testing_template_arameter_dns_edit_show.action?testingTemplateId='+row.testingTemplateId;
			}else if(row.testType=='32'){
				url='testing_template_arameter_web_edit_show.action?testingTemplateId='+row.testingTemplateId;
			}
    		$("#iframe_test").attr('src',url);
    		distplayTargetButton();
			$('#dlg-basic').dialog('open').dialog('center').dialog('setTitle','编辑项 - 测试模板');
        }else{
       	 alert("请先选择测试模板！");
       	 return false;
        }
	}
	</script>


</body>
</html>