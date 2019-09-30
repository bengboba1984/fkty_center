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
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">参考点IP（，分割多个）</font></td>
           <td style="width: 50%">
           <input id="hostIps" class="easyui-textbox" type="text"  value="${hostIps}" style="width:40%;"/> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">任务最长测试时间</font></td>
           <td style="width: 50%">
           <input id="maxTestTime" class="easyui-textbox" type="text"  value="${maxTestTime}" style="width:40%;" /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">任务最短测试时间</font></td>
           <td style="width: 50%">
           <input id="minTestTime" class="easyui-textbox" type="text"  value="${minTestTime}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">连续稳定次数</font></td>
           <td style="width: 50%">
           <input id="continueTimes" class="easyui-textbox" type="text"  value="${continueTimes}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">速度稳定抖动（KB）</font></td>
           <td style="width: 50%">
           <input id="jitterThroughput" class="easyui-textbox" type="text" value="${jitterThroughput}"  style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">请求超时（ms）</font></td>
           <td style="width: 50%">
           <input id="requestTimeout" class="easyui-textbox" type="text" value="${requestTimeout}" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">请求缓冲大小（Bytes）</font></td>
           <td style="width: 50%">
           <input id="requestPieceSize" class="easyui-textbox" type="text" value="${requestPieceSize}" style="width:40%;"/></td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">上传大小（KB）</font></td>
           <td style="width: 50%">
	           <input id="payloadSize" class="easyui-textbox" type="text" value="${payloadSize}" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">	下载大小（KB）</font></td>
           <td style="width: 50%">
	           <input id="downloadSize" class="easyui-textbox" type="text" value="${downloadSize}" style="width:40%;"/></td>
       </tr>
       </table>
    <script type="text/javascript">
    function makeSpeedUrl(){
		var url = "";
		var hostIps = $('#hostIps').val();
		var maxTestTime = $('#maxTestTime').val();
		var minTestTime = $('#minTestTime').val();
		
		var continueTimes = $('#continueTimes').val();
		var jitterThroughput = $('#jitterThroughput').val();
		var requestTimeout = $('#requestTimeout').val();
		var requestPieceSize = $('#requestPieceSize').val();
		var payloadSize = $('#payloadSize').val();
		var downloadSize = $('#downloadSize').val();

		if (hostIps != null && hostIps != "") {
			url = url + "hostIps=" + hostIps + "&";
		}
		if (maxTestTime != null && maxTestTime != "") {
			url = url + "maxTestTime=" + maxTestTime + "&";
		}
		if (minTestTime != null && minTestTime != "") {
			url = url + "minTestTime=" + minTestTime + "&";
		}
		if (continueTimes != null && continueTimes != "") {
			url = url + "continueTimes=" + continueTimes + "&";
		}

		if (jitterThroughput != null && jitterThroughput != "") {
			url = url + "jitterThroughput=" + jitterThroughput + "&";
		}
		if (requestTimeout != null && requestTimeout != "") {
			url = url + "requestTimeout=" + requestTimeout + "&";
		}
		if (requestPieceSize != null && requestPieceSize != "") {
			url = url + "requestPieceSize=" + requestPieceSize + "&";
		}
		if (payloadSize != null && payloadSize != "") {
			url = url + "payloadSize=" + payloadSize + "&";
		}
		if (downloadSize != null && downloadSize != "") {
			url = url + "downloadSize=" + downloadSize + "&";
		}
		url = url.substr(0, url.length - 1);
		
		return url;
	}
    </script>
</body>
</html>