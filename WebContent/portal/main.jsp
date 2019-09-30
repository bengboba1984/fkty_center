<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="ra.com.system_mgt.model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/sidemenu_style.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<title>华数装维助手管理平台</title>

<%
User u=(User)session.getAttribute("loginUser"); 
if(u==null){
	response.sendRedirect("login.action");
}else{

boolean isAdmin=("admin".equals(u.getUserName()));
}
%>
<style type="text/css" rel="stylesheet">

.spanClass{
    color: #ffffff;
    font-size:20px;
    font-weight:bold;
}
</style>

<script>
	function addTab(title, url) {
		var tablist=$('#tabList').tabs('tabs');
		if(tablist.length>5){
			$('#tabList').tabs('close', 1);
		}
		if ($('#tabList').tabs('exists', title)) {
			$('#tabList').tabs('select', title);
			return false;
			//var tab = $('#tabList').tabs('getSelected');  
			//tab.panel('refresh', url);
		} else {

			var content = '<iframe scrolling="auto" frameborder="0"  style="width:100%;height:100%;"></iframe>';
			$('#tabList').tabs('add', {
				title : title,
				content : content,
				//href:url,
				closable : true
			});
			$('#tabList').tabs("getTab",title).find("iframe")[0].src=url;
		}
		
	}
	function logout(){
		$.messager.confirm('退出', '确定退出吗?', 
			function(r){
				if (r){
					location.href="logout.action";
				}
			});

	}
	$(function(){
		/* $('#menuDiv').panel({
		    tools:[{
		        iconCls:'icon-back',
		        handler:function(){toggle()}
		    }]
		});  */
		$.ajax({
            url: 'user_menu_list.action?userId=<%=u.getUserID()%>',
            type: 'post',
	 		dataType: 'json',
		 	error: function(){alert('加载失败');},
            success: function (result) {
            	initSideMenu(result);
            }
        });
					 /*  $('#side-menu').sidemenu({
				            data: data,
				            onSelect: onSideMenuSelect,
				            border: false
				        });	 */
		<%-- $('#tt').tree({
			url:'user_menu_list.action?userId=<%=u.getUserID()%>',
			idField:'id',    
		    treeField:'text', 
		    onClick: function(node){
		    	if(node.url!=null&&node.url!="#"){
				var temp = $('#cc').layout('panel','center');
	            temp.panel('setTitle',node.text);
				$("#iframe").attr('src',node.url);
		    	}
			},
		    columns:[[    
		        {title:'menu',field:'text',width:180}  
		    ]]    
					
		}); --%>
	   
	 
	});
	 
	

	
   function onSideMenuSelect(node){
	   if(node.url!=null&&node.url!="#"){
			var temp = $('#cc').layout('panel','center');
           temp.panel('setTitle',node.text);
			$("#iframe").attr('src',node.url);
	    	}
   } 
   function toggle(){
       var opts = $('#side-menu').sidemenu('options');
       $('#side-menu').sidemenu(opts.collapsed ? 'expand' : 'collapse');
       opts = $('#side-menu').sidemenu('options');
       $('#side-menu').sidemenu('resize', {
           width: opts.collapsed ? 60 : 200
       });
       if(opts.collapsed){
    	   $('#menuDiv').layout('resize', {
               width: 60 
           });
       }else{
    	   $('#menuDiv').layout('resize', {
               width: 200
           });
       }
       
   }
   function initSideMenu(obj){
	   $('#side-menu').sidemenu({
           data: obj,
           onSelect: onSideMenuSelect,
           border: false,
           width: 200
       });
   }
   
</script>


</head>

<body>
	<div id="cc" class="easyui-layout" style="width: 100%; height: 100%;" data-options="fit:false">
	 <div data-options="region:'north'" title="" id="content"  style="height: 9%;background:#000;">
	 <table>
		 <tr>
			 <td style="width:15%;" ><img src="./image/logo.png" style="height:70px;"/></td>
			 <td style="width:70%;" ><span class="text">华数装维助手管理平台</span></td>
			 <td style="width:9%;" class="inputMain"><br/><font class="spanClass">您好 <s:property value="#session.loginUser.userName"/> <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-logout',plain:true" onclick="logout()"></a></font></td>
	 </tr></table> 
	 </div>
		<!-- <div title="功能模组" id="menuDiv" style="width:207;height: 90%" data-options="region:'west',hideCollapsedContent:false,collapsible:false"> -->
		<div  id="menuDiv" style="width:207;height: 90%;padding: 0px;" data-options="region:'west',hideCollapsedContent:false,collapsible:false">
			<div class="easyui-panel" style="padding:0px" data-options="border:false">
        		 <ul class="easyui-tree" id="tt" data-options="animate:true,lines:false"></ul>
        		<div id="side-menu" ></div>  
    		</div>
    		
		</div> 
		
                
		<div region="center" style="" title="主页" id="mainCenter" > 
			<!-- <div id="tabList" class="easyui-tabs" fit="true" >
				<div class="easyui-layout" title="主页" style="width: 100%; height: 100%;">
				
				</div>
			</div> -->
			<iframe id="iframe" scrolling="no" frameborder="0"  style="width:100%;height:98%;"></iframe>
		</div>
	</div>
</body>
</html>