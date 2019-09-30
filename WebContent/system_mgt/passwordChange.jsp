<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="ra.com.system_mgt.model.User"%>
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
$(function(){
	$('#ff').form({
		success:function(data){
			var t=jQuery.parseJSON(data);
			if(t.error==undefined){
				$.messager.alert('æç¤º', t.success,'info');
			}else{
				$.messager.alert('æç¤º', t.error,'warning');
			}
		}
	});		
});
</script>
</head>
<body>

	<div class="easyui-panel" title="修改密码" style="width: 400px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post" action="passwor_change.action">
				<table cellpadding="5">
					<tr>
						<td>旧密码:</td>
						<td><input class="easyui-textbox" type="password" name="oldPassword"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>新密码:</td>
						<td><input class="easyui-textbox" type="password" name="newPassword"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>确认新密码:</td>
						<td><input class="easyui-textbox" type="password" name="reNewPassword"
							data-options="required:true"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">OK</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<script>
		function submitForm() {
			if($("#ff input[name=newPassword]").val()==$("#ff input[name=reNewPassword]").val()){
				$('#ff').form('submit');
			}else{
				$.messager.alert("error", "输入的新密码不一致,请修改");
			}
			
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>
