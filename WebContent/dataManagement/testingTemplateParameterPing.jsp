<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>

<body>
<table style="width: 100%">
<input type="hidden"  id="testingTemplateParameterId" value="${testingTemplateParameterId}" />
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">发包数量(1~20000)</font></td>
           <td style="width: 50%">
           <input id="packetCount" class="easyui-textbox" type="text"  value="${packetCount}" style="width:40%;"/> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">包间隔(ms),10~100000</font></td>
           <td style="width: 50%">
           <input id="spaceingTime" class="easyui-textbox" type="text"  value="${spaceingTime}" style="width:40%;" /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">包超时(sec),0.1~100</font></td>
           <td style="width: 50%">
           <input id="packetTimeout" class="easyui-textbox" type="text"  value="${packetTimeout}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">数据包大小(byte)</font></td>
           <td style="width: 50%">
           <input id="payloadSize" class="easyui-textbox" type="text"  value="${payloadSize}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">数据内容(0~255)</font></td>
           <td style="width: 50%">
           <input id="payloadData" class="easyui-textbox" type="text" value="${payloadData}"  style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">TTL(1~255)</font></td>
           <td style="width: 50%">
           <input id="maxTtl" class="easyui-textbox" type="text" value="${maxTtl}" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">TOS(0~255)</font></td>
           <td style="width: 50%">
           <input id="tos" class="easyui-textbox" type="text" value="${tos}" style="width:40%;"/></td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">保存IP结果</font></td>
           <td style="width: 50%">
           		<input id="saveIpResult" class="easyui-combobox" value="${saveIpResult}" name="saveIpResult" style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	data:[{'key':'true','value':'是'},{'key':'false','value':'否'}]" >
	           
           </td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">100%丢包视为失败</font></td>
           <td style="width: 50%">
           		<input id="allLossAsFail" class="easyui-combobox" value="${allLossAsFail}" name="allLossAsFail" style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	data:[{'key':'true','value':'是'},{'key':'false','value':'否'}]" >
	       </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">双向时延</font></td>
           <td style="width: 50%">
           <input id="roundTrip" class="easyui-combobox"  name="roundTrip" value="${roundTrip}" style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	data:[{'key':'true','value':'是'},{'key':'false','value':'否'}]" >
	       </td>
       </tr>
       </table>
    <script type="text/javascript">
    function makePingUrl(){
		var url = "";
		var packetCount = $('#packetCount').val();
		var spaceingTime = $('#spaceingTime').val();
		var packetTimeout = $('#packetTimeout').val();
		
		var payloadSize = $('#payloadSize').val();
		var payloadData = $('#payloadData').val();
		var maxTtl = $('#maxTtl').val();
		var tos = $('#tos').val();
		var saveIpResult=$('#saveIpResult').combobox('getValue');
		var allLossAsFail=$('#allLossAsFail').combobox('getValue');
		var roundTrip=$('#roundTrip').combobox('getValue');
		
		/* var testGroupId=$('#testGroupId').combobox('getValue');
		var testType = $('#testType').combobox('getValue'); */

		var testingTemplateParameterId = $('#testingTemplateParameterId').val();
		if (testingTemplateParameterId != null && testingTemplateParameterId != "") {
			url = url + "testingTemplateParameterId=" + testingTemplateParameterId + "&";
		}
		if (packetCount != null && packetCount != "") {
			url = url + "packetCount=" + packetCount + "&";
		}
		if (spaceingTime != null && spaceingTime != "") {
			url = url + "spaceingTime=" + spaceingTime + "&";
		}
		if (packetTimeout != null && packetTimeout != "") {
			url = url + "packetTimeout=" + packetTimeout + "&";
		}
		if (payloadSize != null && payloadSize != "") {
			url = url + "payloadSize=" + payloadSize + "&";
		}

		if (payloadData != null && payloadData != "") {
			url = url + "payloadData=" + payloadData + "&";
		}
		if (maxTtl != null && maxTtl != "") {
			url = url + "maxTtl=" + maxTtl + "&";
		}
		if (tos != null && tos != "") {
			url = url + "tos=" + tos + "&";
		}
		if (saveIpResult != null && saveIpResult != "") {
			url = url + "saveIpResult=" + saveIpResult + "&";
		}
		if (allLossAsFail != null && allLossAsFail != "") {
			url = url + "allLossAsFail=" + allLossAsFail + "&";
		}
		if (roundTrip != null && roundTrip != "") {
			url = url + "roundTrip=" + roundTrip + "&";
		}
		url = url.substr(0, url.length - 1);
		
		return url;
	}
    </script>
</body>
</html>