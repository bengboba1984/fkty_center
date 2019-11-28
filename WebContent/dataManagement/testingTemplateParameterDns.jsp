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
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">查询次数（1~100）</font></td>
           <td style="width: 50%">
           <input id="packetCount" class="easyui-textbox" type="text"  value="${packetCount}" style="width:40%;"/> </td>
       </tr>
       <tr>
			<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">忽略次数（0~99）</font></td>
           <td style="width: 50%">
           <input id="ignoreCount" class="easyui-textbox" type="text"  value="${ignoreCount}" style="width:40%;" /> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">单次发包数量（1~10000）</font></td>
           <td style="width: 50%">
           <input id="roundItemCount" class="easyui-textbox" type="text"  value="${roundItemCount}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">包超时（sec），1~100</font></td>
           <td style="width: 50%">
           <input id="packetTimeout" class="easyui-textbox" type="text"  value="${packetTimeout}" style="width:40%;"/> </td>
       </tr>
       <tr>
				<td class="panel-header" style="width: 30%" align="center"><font color="#ffffff">查询间隔（ms），20~10000</font></td>
           <td style="width: 50%">
           <input id="spacingTime" class="easyui-textbox" type="text" value="${spacingTime}"  style="width:40%;"/> </td>
       </tr>
      
       </table>
     <script type="text/javascript">
     function checkValue(obj){
    	 var reg = /^\d+$/g;
         return reg.test(value);
     }
    function makeDnsUrl(){
		var url = "";
		var packetCount = $('#packetCount').val();
		var ignoreCount = $('#ignoreCount').val();
		var roundItemCount = $('#roundItemCount').val();
		var packetTimeout = $('#packetTimeout').val();
		var spacingTime = $('#spacingTime').val();
		
		var testingTemplateParameterId = $('#testingTemplateParameterId').val();
		if (testingTemplateParameterId != null && testingTemplateParameterId != "") {
			url = url + "testingTemplateParameterId=" + testingTemplateParameterId + "&";
		}
		if (packetCount != null && packetCount != "") {
			url = url + "packetCount=" + packetCount + "&";
		}
		if (ignoreCount != null && ignoreCount != "") {
			url = url + "ignoreCount=" + ignoreCount + "&";
		}
		if (roundItemCount != null && roundItemCount != "") {
			url = url + "roundItemCount=" + roundItemCount + "&";
		}
		if (packetTimeout != null && packetTimeout != "") {
			url = url + "packetTimeout=" + packetTimeout + "&";
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