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
<input type="hidden" name="testingTemplateParameterId" id="testingTemplateParameterId" value="${testingTemplateParameterId}" />
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">数据长度（1~1400）</font></td>
           <td style="width: 50%">
           <input id="payloadSize" class="easyui-textbox" type="text"  value="${payloadSize}" style="width:40%;"/> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">协议类型</font></td>
           <td style="width: 50%">
           <input id="protocolType" class="easyui-combobox"  name="protocolType" style="width:40%;" value="${protocolType}"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	method:'post',
                    						  	url:'protocol_type_list.action'" ></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">最大跳数（1~30）</font></td>
           <td style="width: 50%">
           <input id="maxHops" class="easyui-textbox" type="text"  value="${maxHops}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">每跳间隔（ms）,20~10000</font></td>
           <td style="width: 50%">
           <input id="replyTimeout" class="easyui-textbox" type="text"  value="${replyTimeout}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">TOS(0~255)</font></td>
           <td style="width: 50%">
           <input id="warningValue5" class="easyui-textbox" type="text" value="0"  style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">单跳发包个数（1~255）</font></td>
           <td style="width: 50%">
           <input id="packetCount" class="easyui-textbox" type="text" value="${packetCount}" style="width:40%;"/></td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">单跳发包间隔（ms）,0~100000</font></td>
           <td style="width: 50%">
           <input id="spacingTime" class="easyui-textbox" type="text" value="${spacingTime}" style="width:40%;"/></td>
       </tr>
       </table>
    <script type="text/javascript">
    function makeTraceUrl(){
		var url = "";
		var payloadSize = $('#payloadSize').val();
		var protocolType=$('#protocolType').combobox('getValue');
		var maxHops = $('#maxHops').val();
		var replyTimeout = $('#replyTimeout').val();
		
		var packetCount = $('#packetCount').val();
		var spacingTime = $('#spacingTime').val();
		
		if (payloadSize != null && payloadSize != "") {
			url = url + "payloadSize=" + payloadSize + "&";
		}
		if (protocolType != null && protocolType != "") {
			url = url + "protocolType=" + protocolType + "&";
		}
		if (maxHops != null && maxHops != "") {
			url = url + "maxHops=" + maxHops + "&";
		}
		if (replyTimeout != null && replyTimeout != "") {
			url = url + "replyTimeout=" + replyTimeout + "&";
		}

		if (packetCount != null && packetCount != "") {
			url = url + "packetCount=" + packetCount + "&";
		}
		if (spacingTime != null && spacingTime != "") {
			url = url + "spacingTime=" + spacingTime + "&";
		}
		
		url = url.substr(0, url.length - 1);
		
		return url;
	}
    </script>
</body>
</html>