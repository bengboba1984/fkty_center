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
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">最大下载（KB），1~1024000</font></td>
           <td style="width: 50%">
           <input id="maxDownloadSize" class="easyui-textbox" type="text"  value="${maxDownloadSize}" style="width:40%;"/> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">最多下载线程（1~100）</font></td>
           <td style="width: 50%">
           <input id="maxThreadCount" class="easyui-textbox" type="text"  value="${maxThreadCount}" style="width:40%;" /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">	最多下载子项（1~2000）</font></td>
           <td style="width: 50%">
           <input id="maxSubCount" class="easyui-textbox" type="text"  value="${maxSubCount}" style="width:40%;"/> </td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">	最多存储子项（1~2000）</font></td>
           <td style="width: 50%">
           <input id="maxSubSaveCount" class="easyui-textbox" type="text"  value="${maxSubSaveCount}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">	单个元素超时（秒），1~1000</font></td>
           <td style="width: 50%">
           <input id="itemTimeout" class="easyui-textbox" type="text"  value="${itemTimeout}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">TOS（0~255）</font></td>
           <td style="width: 50%">
           <input id="warningValue5" class="easyui-textbox" type="text" value="0"  style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">启用DNS缓存</font></td>
           <td style="width: 50%">
           <input id="useDnsCache" class="easyui-combobox" value="${useDnsCache}" style="width:40%;"
								data-options="valueField:'key',
												textField:'value',
                    						  	panelHeight:'auto',
                    						  	data:[{'key':'1','value':'是'},{'key':'0','value':'否'}]" ></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">分析HTML内容</font></td>
           <td style="width: 50%">
           <input id="taskInterval" class="easyui-textbox" type="text" value="是" style="width:40%;"/></td>
       </tr>
        <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">用户浏览器</font></td>
           <td style="width: 50%">
	           <input id="userAgent" class="easyui-textbox" type="text" value="${userAgent}" style="width:40%;"/></td>
       </tr>
        <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">页面资料加载深度</font></td>
           <td style="width: 50%">
	           <input id="maxPageDepth" class="easyui-textbox" type="text" value="${maxPageDepth}" style="width:40%;"/>层深度</td>
       </tr>
        <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">主测试结果</font></td>
           <td style="width: 50%">
           		<input id="taskInterval2" class="easyui-textbox" type="text" value="第一个非跳转页面" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">有效返回码（,分割多个）</font></td>
           <td style="width: 50%">
           <input id="validResponseCodes" class="easyui-textbox" type="text" value="${validResponseCodes}" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">	元素加载门限（%）,1~100</font></td>
           <td style="width: 50%">
           <input id="taskInterval1" class="easyui-textbox" type="text" value="80%" style="width:40%;"/></td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">页面打开时限（s）,1~10000</font></td>
           <td style="width: 50%">
           <input id="taskInterval" class="easyui-textbox" type="text" value="30" style="width:40%;"/></td>
       </tr>
       </table>
    <script type="text/javascript">
    function makeWebUrl(){
		var url = "";
		var maxDownloadSize = $('#maxDownloadSize').val();
		var maxThreadCount = $('#maxThreadCount').val();
		var maxSubCount = $('#maxSubCount').val();
		
		var maxSubSaveCount = $('#maxSubSaveCount').val();
		var itemTimeout = $('#itemTimeout').val();
		var useDnsCache = $('#useDnsCache').combobox('getValue');
		var userAgent = $('#userAgent').val();
		var maxPageDepth = $('#maxPageDepth').val();
		var validResponseCodes = $('#validResponseCodes').val();

		var testingTemplateParameterId = $('#testingTemplateParameterId').val();
		if (testingTemplateParameterId != null && testingTemplateParameterId != "") {
			url = url + "testingTemplateParameterId=" + testingTemplateParameterId + "&";
		}
		if (maxDownloadSize != null && maxDownloadSize != "") {
			url = url + "maxDownloadSize=" + maxDownloadSize + "&";
		}
		if (maxThreadCount != null && maxThreadCount != "") {
			url = url + "maxThreadCount=" + maxThreadCount + "&";
		}
		if (maxSubCount != null && maxSubCount != "") {
			url = url + "maxSubCount=" + maxSubCount + "&";
		}
		if (maxSubSaveCount != null && maxSubSaveCount != "") {
			url = url + "maxSubSaveCount=" + maxSubSaveCount + "&";
		}

		if (itemTimeout != null && itemTimeout != "") {
			url = url + "itemTimeout=" + itemTimeout + "&";
		}
		if (useDnsCache != null && useDnsCache != "") {
			url = url + "useDnsCache=" + useDnsCache + "&";
		}
		if (userAgent != null && userAgent != "") {
			url = url + "userAgent=" + userAgent + "&";
		}
		if (maxPageDepth != null && maxPageDepth != "") {
			url = url + "maxPageDepth=" + maxPageDepth + "&";
		}
		if (validResponseCodes != null && validResponseCodes != "") {
			url = url + "validResponseCodes=" + validResponseCodes + "&";
		}
		url = url.substr(0, url.length - 1);
		
		return url;
	}
    </script>
</body>
</html>