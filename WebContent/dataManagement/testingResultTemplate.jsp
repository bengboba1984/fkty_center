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
				departmentSearch:$('#departmentSearch').val(),
				stbidSearch:$('#stbidSearch').val()
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
			var url="testing_result_template_download.action?testingDateBegin="+$('#testingDateBegin').datebox('getValue')+'&testingDateEnd='+$('#testingDateEnd').datebox('getValue')
			+'&testTypeSearch='+$('#testTypeSearch').combobox('getValue')+'&accountSearch='+$('#accountSearch').val()+'&testerSearch='+$('#testerSearch').val()
			+'&departmentSearch='+$('#departmentSearch').val()+'&stbidSearch='+$('#stbidSearch').val();
			window.open(url);
			
		}
		
		
	</script>
<title><s:text name="portal.System_Management.userManagement"/></title>
</head>
<body>
<input type="hidden" name = "moduleId" id = "moduleId" value="${moduleId}"/>
<div class="easyui-layout" style="height:750px;">
	<div data-options="region:'north',title:'<s:text name="common.searchCriteria" />',collapsible:false" style="height:21%">
		<table style="width:100%">
			<tr><td colspan="4"></td></tr>
			<tr>
				<td class="panel-header" style="width:10%" align="center">时间</td>
				<td style="width:25%"><input id="testingDateBegin" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:40%"></input>--
				<input id="testingDateEnd" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" style="width:40%"></input></td>
				<td class="panel-header" style="width:10%" align="center">测试类型</td>
				<td style="width:15%"><input class="easyui-combobox"  id="testTypeSearch" style="width:80%" value="${testTypeSearch}"
						data-options="url:'test_group_list.action?showAllFlag=1',
												method:'post',
                    						  	valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto'"/></td>
				<td class="panel-header" style="width:10%" align="center">装维单位</td>
				<td style="width:15%"><input id="departmentSearch" class="easyui-textbox" style="width:80%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:10%" align="center">装维人员</td>
				<td style="width:25%"><input id="testerSearch" class="easyui-textbox" style="width:80%"></td>
				<td class="panel-header" style="width:10%" align="center">测试序列号</td>
				<td style="width:15%"><input id="userNameSearch" class="easyui-textbox" style="width:80%"></td>
			<td class="panel-header" style="width:10%" align="center">宽带帐号</td>
				<td style="width:25%"><input id="accountSearch" class="easyui-textbox" style="width:80%"></td>
			</tr>
			<tr>
				<td class="panel-header" style="width:10%" align="center">STBID</td>
				<td style="width:15%"><input id="stbidSearch" class="easyui-textbox" style="width:80%"></td>
				<td colspan="4" align="right">
					<a href="#" class="easyui-linkbutton" id="search" data-options="iconCls:'icon-search',disabled:true" onClick="doSearch()"><s:text name="common.search"></s:text></a>
				
					<a href="#" class="easyui-linkbutton" id="download" data-options="iconCls:'icon-download',disabled:true" onClick="doDownload()">导出</a>
				</td>
			</tr>
		</table>
	</div>

	<div data-options="region:'center'" style="height:79%">
		<table id="dg" class="easyui-datagrid" title="详单列表"
		style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls: 'icon-edit',pagination:true,singleSelect: true,rownumbers:true,toolbar: '#tb',url: 'testing_result_template_list.action',method: 'post',onClickRow: onClickRow">
		<thead>
			<tr>
				<th data-options="field:'testingResultId',hidden:'true',width:10">result ID</th>
				<th data-options="field:'resultSeq',sortable:'true',align:'center'">测试帐号</th>
				<th data-options="field:'testingDate',sortable:'true',align:'center'">时间</th>		
				<th data-options="field:'tester',sortable:'true',align:'center'">工号</th>
				<!-- <th data-options="field:'hireDate',sortable:'true',align:'center'">单位</th> -->
				<th data-options="field:'account',sortable:'true',align:'center',width:'10%'">宽带帐号</th>	
				<th data-options="field:'stbId',sortable:'true',align:'center',width:'20%'">STBID</th>
				<th data-options="field:'testingTemplateGroupId',sortable:'true',align:'center'">测试类型</th>
				<th data-options="field:'woNumber',sortable:'true',align:'center',formatter:formatWoNumber">工单号</th>
				<th data-options="field:'resultPingId',sortable:'true',align:'center',formatter:formatPingValue">PING</th>
				<th data-options="field:'resultTraceId',sortable:'true',align:'center',formatter:formatTraceValue">TRACE</th>
				<th data-options="field:'resultSpeedId',sortable:'true',align:'center',formatter:formatSpeedValue">测速</th>
				<th data-options="field:'resultWebId',sortable:'true',align:'center',formatter:formatWebValue">WEB</th>
				<th data-options="field:'resultDnsId',sortable:'true',align:'center',formatter:formatDnsValue">DNS</th>
			</tr>
		</thead>
	</table>
	</div>
</div>
<div id="dlg-ping" class="easyui-dialog" style="width:600px" closed="true" >
   <div style="width:500px" fit="true">
        <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">平均时延</td>
	           	<td style="width: 50%"><span id="avgDelay"></span> </td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">平均抖动</td>
	           	<td style="width: 50%"><span id="avgJitter" ></span> </td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">主机IP</td>
	           	<td style="width: 50%"><span id="hostIp"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">丢包率</td>
	           	<td style="width: 50%"><span id="lossPercent" ></span></td>
       		</tr>
    	</table>
     </div>
</div>
<div id="dlg-trace" class="easyui-dialog" style="width:600px" closed="true" >
   <div style="width:500px" fit="true">
        <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">平均时延</td>
	           	<td style="width: 50%"><span id="avgDelay1"></span> </td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">平均抖动</td>
	           	<td style="width: 50%"><span id="avgJitter11" ></span> </td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">路由跳数</td>
	           	<td style="width: 50%"><span id="hopCount1"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">主机IP</td>
	           	<td style="width: 50%"><span id="hostIp1" ></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">丢包率</td>
	           	<td style="width: 50%"><span id="lossPercent1"></span> </td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">附加子项数</td>
	           	<td style="width: 50%"><span id="subItemCount1" ></span> </td>
       		</tr>
       		
    	</table>
     </div>
</div>
<div id="dlg-speed" class="easyui-dialog" style="width:600px" closed="true" >
   <div style="width:500px" fit="true">
        <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">下载峰值速率</td>
	           	<td style="width: 50%"><span id="downloadMaxThroughput3"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">下载平均速率</td>
	           	<td style="width: 50%"><span id="downloadThroughput3"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">上传峰值速率</td>
	           	<td style="width: 50%"><span id="uploadMaxThroughput3"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">上传平均速率</td>
	           	<td style="width: 50%"><span id="uploadThroughput3"></span></td>
       		</tr>
    	</table>
     </div>
</div>
<div id="dlg-dns" class="easyui-dialog" style="width:600px" closed="true" >
   <div style="width:500px" fit="true">
        <table style="width: 100%">
	      <tr>
				<td class="panel-header" style="width: 30%" align="center">记录数</td>
	           	<td style="width: 50%"><span id="numberOfAnswers4"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">解析时间</td>
	           	<td style="width: 50%"><span id="resolveTime4"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">成功率</td>
	           	<td style="width: 50%"><span id="successPercent4"></span></td>
       		</tr>
    	</table>
     </div>
</div>
<div id="dlg-web" class="easyui-dialog" style="width:600px" closed="true" >
   <div style="width:500px" fit="true">
        <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">目标IP</td>
	           	<td style="width: 50%"><span id="hostIp5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">请求URL</td>
	           	<td style="width: 50%"><span id="nodeIp5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">解析时间</td>
	           	<td style="width: 50%"><span id="resolveTime5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">连接时间</td>
	           	<td style="width: 50%"><span id="connectTime5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">首字节时间</td>
	           	<td style="width: 50%"><span id="firstByteTime5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">首页加载时间</td>
	           	<td style="width: 50%"><span id="firstPageTime5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">综合质量</td>
	           	<td style="width: 50%"><span id="meanQuality5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">响应码</td>
	           	<td style="width: 50%"><span id="responseCode5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">吞吐率</td>
	           	<td style="width: 50%"><span id="throughput5"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">总时间</td>
	           	<td style="width: 50%"><span id="totalTime5"></span></td>
       		</tr>
    	</table>
     </div>
</div> 
<div id="dlg-trace-sub" class="easyui-dialog" style="width:600px;height:400px" closed="true" >
		<table id="dg-sub" class="easyui-datagrid" style="width: 100%; height: 100%" remoteSort="false"
		data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers:true,url: 'testing_result_template_trace_sub_list.action',method: 'post'">
		<thead>
			<tr>
				<th data-options="field:'loadIndex',sortable:'true',align:'center',width:'25%'">序号</th>
				<th data-options="field:'hostIp',sortable:'true',align:'center',width:'25%'">节点</th>		
				<th data-options="field:'avgDelay',sortable:'true',align:'center',width:'25%'">平均时延</th>
				<th data-options="field:'lossPercent',sortable:'true',align:'center',width:'25%'">丢包率</th>
			</tr>
		</thead>
	</table>
</div>  
<div id="dlg-woNumber" class="easyui-dialog" style="width:850px;height:550px" closed="true" >
<div class="easyui-layout" style="width:99.9%;height:99%">
    <div id="p" data-options="region:'west'" title="" style="width:70%;padding:0px;height:550px">
       <div id="woNumberTab" class="easyui-tabs" data-options="tabWidth:95,tabHeight:500,tabPosition:'left',headerWidth:100" style="width:100%;height:100%">
        <div id="templatType3" title="手动测试报告" style="padding:10px">
        	工单号: <span id="woNumber1"></span>
	     <div style="margin-bottom:10px"></div>
	        <div class="easyui-panel" id="dnsPanel"  title ="DNS测试" colsed="true" style="width: 99%;height:100px;">
		     <table style="width: 100%">
		     <tr>
		     	<td class="panel-header" style="width: 30%" align="center">记录数</td>
		     	<td class="panel-header" style="width: 30%" align="center">解析时间ms</td>
		     	<td class="panel-header" style="width: 30%" align="center">成功率%</td>
		     </tr>
		     <tr>
		     <td style="width: 30%" align="center"><span id="numberOfAnswersDns"></span></td>
		     <td style="width: 30%"  align="center"><span id="resolveTimeDns"></span></td>
		     <td style="width: 30%"  align="center"><span id="successPercentDns"></span></td>
		     </tr>
		     </table>
	     </div>
	     <div style="margin-bottom:10px"></div>
	     <div class="easyui-panel" id="speedPanel" title="宽带测试"  colsed="true" style="width: 99%;height:100px">
		     <table style="width: 100%">
		     <tr>
		     	<td class="panel-header" style="width: 45%" align="center">上行速率Mbps</td>
		     	<td class="panel-header" style="width: 45%" align="center">下行速率Mbps</td>
		     </tr>
		     <tr>
		     <td style="width: 45%" align="center"><span id="uploadMaxThroughputSpeed"></span></td>
		     <td style="width: 45%" align="center"><span id="downloadMaxThroughputSpeed"></span></td>
		     </tr>
		     </table>
	     </div>
	     <div style="margin-bottom:10px"></div>
	     <div class="easyui-panel" id="webPanel" title="网页测试" colsed="true" style="width: 99%;height:100px">
		     <table style="width: 100%">
		     <tr>
		     	<td class="panel-header" style="width: 25%" align="center">解析时延ms</td>
		     	<td class="panel-header" style="width: 25%" align="center">连接时延ms</td>
		     	<td class="panel-header" style="width: 25%" align="center">首包时延ms</td>
		     	<td class="panel-header" style="width: 25%" align="center">首页时间ms</td>
		     </tr>
		     <tr>
		     <td style="width: 25%" align="center"><span id="resolveTimeWeb"></span></td>
		     <td style="width: 25%" align="center"><span id="connectTimeWeb"></span></td>
		     <td style="width: 25%" align="center"><span id="firstByteTimeWeb"></span></td>
		     <td style="width: 25%" align="center"><span id="firstPageTimeWeb"></span></td>
		     </tr>
		     </table>
	     </div>
	     <div style="margin-bottom:10px"></div>
	     <div class="easyui-panel" id="tracePanel" title="TRACE测试" colsed="true" style="width: 99%;height:100px">
		     <table style="width: 100%">
		     <tr>
		     	<td class="panel-header" style="width: 30%" align="center">时延ms</td>
		     	<td class="panel-header" style="width: 30%" align="center">丢包率%</td>
		     	<td class="panel-header" style="width: 30%" align="center">抖动ms</td>
		     </tr>
		     <tr>
		     <td style="width: 30%" align="center"><span id="avgDelayTrace"></span></td>
		     <td style="width: 30%" align="center"><span id="lossPercentTrace"></span></td>
		     <td style="width: 30%" align="center"><span id="avgJitterTrace"></span></td>
		     </tr>
		     </table>
	     </div>
	      <div style="margin-bottom:10px"></div>
	     <div class="easyui-panel" id="pingPanel"  title ="PING测试" colsed="true" style="width: 99%;height:100px">
		     <table style="width: 100%">
		     <tr>
		     	<td class="panel-header" style="width: 30%" align="center">时延ms</td>
		     	<td class="panel-header" style="width: 30%" align="center">丢包率%</td>
		     	<td class="panel-header" style="width: 30%" align="center">抖动ms</td>
		     </tr>
		     <tr>
		     <td style="width: 30%" align="center"><span id="avgDelayPing"></span></td>
		     <td style="width: 30%" align="center"><span id="lossPercentPing"></span></td>
		     <td style="width: 30%" align="center"><span id="avgJitterPing"></span></td>
		     </tr>
		     </table>
	     </div>
           
        </div>
       
    </div>
 </div>
 <div data-options="region:'center'" title="" style="padding:10px">
     <div class="easyui-panel" title="用户信息"   style="width: 99%">
     <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">用户名称</td>
	           	<td style="width: 50%"><span id="userName"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">用户地址</td>
	           	<td style="width: 50%"><span id="userAdress"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">联系电话</td>
	           	<td style="width: 50%"><span id="userTel"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">签约带宽</td>
	           	<td style="width: 50%"><span id="userAccount2"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">宽带帐号</td>
	           	<td style="width: 50%"><span id="userAccount"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">接入方式</td>
	           	<td style="width: 50%"><span id="userFun"></span></td>
       		</tr>
       </table>
    </div>
    <div style="margin-bottom:10px"></div>
    <div class="easyui-panel" title="装维人员信息" style="width: 99%">
    <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">姓名</td>
	           	<td style="width: 50%"><span id="testerName"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">工号</td>
	           	<td style="width: 50%"><span id="testerAccount"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">联系电话</td>
	           	<td style="width: 50%"><span id="testerTel"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">所属公司</td>
	           	<td style="width: 50%"><span id="testerCompany"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">所属部门</td>
	           	<td style="width: 50%"><span id="testerDepartment"></span></td>
       		</tr>
       </table>
    </div>
    <div style="margin-bottom:10px"></div>
    <div class="easyui-panel" title="设备信息" style="width: 99%">
    <table style="width: 100%">
	       <tr>
				<td class="panel-header" style="width: 30%" align="center">盒子ID</td>
	           	<td style="width: 50%"><span id="deviceSeq1"> </span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">联网方式</td>
	           	<td style="width: 50%"><span id="ottConnectType1"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">WAN IP</td>
	           	<td style="width: 50%"><span id="boxIP1"></span></td>
       		</tr>
       		<tr>
				<td class="panel-header" style="width: 30%" align="center">手机版本</td>
	           	<td style="width: 50%"><span id="androidVersion1"></span></td>
       		</tr>
       </table>
    </div>  
  </div>
</div>
</div>
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
	function formatPingValue(val,row){
        if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openPingDiv('+val+')" style="color:red;">详情</a>';
        } else {
            return val;
        }
		
	}
	function formatDnsValue(val,row){
        if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openDnsDiv('+val+')" style="color:red;">详情</a>';
        } else {
            return val;
        }
		
	}
	function formatTraceValue(val,row){
        if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openTraceDiv('+val+')" style="color:red;">详情</a>';
        } else {
            return val;
        }
		
	}
	function formatWebValue(val,row){
        if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openWebDiv('+val+')" style="color:red;">详情</a>';
        } else {
            return val;
        }
		
	}
	function formatSpeedValue(val,row){
        if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openSpeedDiv('+val+')" style="color:red;">详情</a>';
        } else {
            return val;
        }
		
	}
	function formatWoNumber(val,row){
		if (val!=null&&val!=''){
            return '<a href="#" class="easyui-linkbutton"  onClick="openWoNumberDiv('+row.testingResultId+')" style="color:red;">'+val+'</a>';
        } else {
            return val;
        }
	}
	function openTraceSubDiv(traceId){
		$('#dg-sub').datagrid('load',{
			targetId: traceId
	    	});
		$('#dlg-trace-sub').dialog('open').dialog('center').dialog('setTitle','详情Sub下钻');
	}
	function openPingDiv(id){
		$.ajax({
	        url: 'testing_result_template_ping_list.action',
	        type: 'post',
	        data:{
	        	targetId: id
		 },
		 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	result = result.result;
		$('#avgDelay').text(result.avgDelay+' ms');
		$('#avgJitter').text(result.avgJitter+' ms');
		$('#hostIp').text(result.hostIp);
		$('#lossPercent').text(result.lossPercent+'  %');
		$('#dlg-ping').dialog('open').dialog('center').dialog('setTitle','详情下钻');
	        }
	    }); 
		
	}
	function openTraceDiv(id){
		/* var url= 'testing_result_template_trace_list.action?targetId='+id;
		var effectRow = new Object();
		$.post(url,
				effectRow,function(response) {
			if (response.success=='success') {
				var result = response.result;
				$('#avgDelay1').text(result.avgDelay+' ms');
	        	$('#avgJitter11').text(result.avgJitter+' ms');
	        	$('#hopCount1').text(result.hopCount);
	        	$('#hostIp1').text(result.hostIp);
	        	$('#lossPercent1').text(result.lossPercent+'  %');
	        	//$('#subItemCount1').text(result.subItemCount);
	        	$('#subItemCount1').html('<a href="#" class="easyui-linkbutton" onClick="openTraceSubDiv('+result.resultTraceId+')" ><span id="subItemCount1" > '+result.subItemCount+'</span></a>');
	        	
				$('#dlg-trace').dialog('open').dialog('center').dialog('setTitle','详情下钻');
			} else{
			$.messager.alert('详情失败');
		}
	}, "JSON").error(function() {
		$.messager.alert("详情失败","详情失败！");
	}); */
		$.ajax({
	        url: 'testing_result_template_trace_list.action',
	        type: 'post',
	        data:{
	        	targetId: id
		 },
		 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	result = result.result;
	        	$('#avgDelay1').text(result.avgDelay+' ms');
	        	$('#avgJitter11').text(result.avgJitter+' ms');
	        	$('#hopCount1').text(result.hopCount);
	        	$('#hostIp1').text(result.hostIp);
	        	$('#lossPercent1').text(result.lossPercent+'  %');
	        	$('#subItemCount1').html('<a href="#" class="easyui-linkbutton" onClick="openTraceSubDiv('+result.resultTraceId+')" ><span id="subItemCount1" > '+result.subItemCount+'</span></a>');
	        	
				$('#dlg-trace').dialog('open').dialog('center').dialog('setTitle','详情下钻');
		
	        }
	    });  
		
	}
	function openDnsDiv(id){
		$.ajax({
	        url: 'testing_result_template_dns_list.action',
	        type: 'post',
	        data:{
	        	targetId: id
	 },
	 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	result = result.result;
	        	$('#numberOfAnswers4').text(result.numberOfAnswers);
	        	$('#resolveTime4').text(result.resolveTime+' ms');
	        	$('#successPercent4').text(result.successPercent+'  %');
		$('#dlg-dns').dialog('open').dialog('center').dialog('setTitle','详情下钻');
	        }
	    }); 
		
	}
	function openWebDiv(id){
		$.ajax({
	        url: 'testing_result_template_web_list.action',
	        type: 'get',
	        data:{
	        	targetId: id
	 },
	 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	result = result.result;
	        	$('#hostIp5').text(result.hostIp);
	        	$('#nodeIp5').text(result.nodeIp);
	        	$('#resolveTime5').text(result.resolveTime+' ms');
	        	$('#connectTime5').text(result.connectTime+' ms');
	        	$('#firstByteTime5').text(result.firstByteTime+' ms');
	        	$('#firstPageTime5').text(result.firstPageTime+' ms');
	        	$('#meanQuality5').text(result.meanQuality);
	        	$('#responseCode5').text(result.responseCode);
	        	$('#throughput5').text(result.throughput+' KB/S');
	        	$('#totalTime5').text(result.totalTime+' s');
		$('#dlg-web').dialog('open').dialog('center').dialog('setTitle','详情下钻');
	        }
	    }); 
		
	}
	function openSpeedDiv(id){
		
		$.ajax({
	        url: 'testing_result_template_speed_list.action',
	        type: 'post',
	        data:{
	        	targetId: id
	 },
	 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	result = result.result;
	        	$('#downloadMaxThroughput3').text(result.downloadMaxThroughput+' Mbps');
	        	$('#downloadThroughput3').text(result.downloadThroughput+' Mbps');
	        	$('#uploadMaxThroughput3').text(result.uploadMaxThroughput+' Mbps');
	        	$('#uploadThroughput3').text(result.uploadThroughput+' Mbps');
	    		$('#dlg-speed').dialog('open').dialog('center').dialog('setTitle','详情下钻');
	        }
	    }); 
		
	}
	function openWoNumberDiv(id){
		$.ajax({
	        url: 'testing_result_template_detail.action',
	        type: 'post',
	        data:{
	        	targetId: id
		 },
		 dataType: 'json',
		 error: function(){alert('加载失败');},
	        success: function (result) {
	        	//templatType3
	        	
	        	$('#userAccount').text(result.result.account);
	        	
	        	var titles = $('#woNumberTab').find('.tabs-header:first').find('.tabs-title');
	        	titles.eq(0).text(result.result.testingTemplateGroupId+'报告');
	        	// $('#templatType3').text(result.result.testingTemplateGroupId);
	        	if(result.traceNull=='null'){
	        		//隐藏trace
	        		$('#tracePanel').panel('close');
	        	}else{
	        		$('#tracePanel').panel('open');
	        		$('#avgDelayTrace').text(result.trace.avgDelay);
	        		$('#avgJitterTrace').text(result.trace.avgJitter);
	        		$('#lossPercentTrace').text(result.trace.lossPercent);
	        	}
	        	if(result.webNull=='null'){
	        		//隐藏web 
	        		$('#webPanel').panel('close');
	        	}else{
	        		$('#webPanel').panel('open');
	        		$('#resolveTimeWeb').text(result.web.resolveTime);
	        		$('#connectTimeWeb').text(result.web.connectTime);
	        		$('#firstByteTimeWeb').text(result.web.firstByteTime);
	        		$('#firstPageTimeWeb').text(result.web.firstPageTime);
	        	}
	        	if(result.dnsNull=='null'){
	        		//隐藏ping 
	        		$('#dnsPanel').panel('close');
	        	}else{
	        		$('#dnsPanel').panel('open');
	        		$('#numberOfAnswersDns').text(result.dns.numberOfAnswers);
	        		$('#resolveTimeDns').text(result.dns.resolveTime);
	        		$('#successPercentDns').text(result.dns.successPercent);
	        	}
	        	if(result.speedNull=='null'){
	        		//隐藏ping 
	        		$('#speedPanel').panel('close');
	        	}else{
	        		$('#speedPanel').panel('open');
	        		$('#uploadMaxThroughputSpeed').text(result.speed.uploadMaxThroughput);
	        		$('#downloadMaxThroughputSpeed').text(result.speed.downloadMaxThroughput);
	        	}
	        	if(result.pingNull=='null'){
	        		//隐藏ping 
	        		$('#pingPanel').panel('close');
	        	}else{
	        		$('#pingPanel').panel('open');
	        		$('#avgDelayPing').text(result.ping.avgDelay);
	        		$('#avgJitterPing').text(result.ping.avgJitter);
	        		$('#lossPercentPing').text(result.ping.lossPercent);
	        	}
	        	if(result.testerInfoNull=='null'){
	        		
	        	}else{
	        		$('#testerName').text(result.testerInfo.userName);
	        		$('#testerAccount').text(result.testerInfo.workId);
	        		$('#testerTel').text(result.testerInfo.telephoneNumber);
	        		$('#testerDepartment').text(result.testerInfo.departmentName);
	        	}
	        	$('#deviceSeq1').text(result.result.deviceSeq);
	        	$('#ottConnectType1').text(result.result.ottConnectType);
	        	$('#androidVersion1').text(result.result.androidVersion);
	        	$('#boxIP1').text(result.result.boxIP);
	        	$('#woNumber1').text(result.result.woNumber);
	        	
	        	$('#dlg-woNumber').dialog('open').dialog('center').dialog('setTitle','工单信息');
	        }
	    }); 
		
}
		
</script>
</body>
</html>