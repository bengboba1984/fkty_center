<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<style type="text/css" rel="stylesheet">

</style>
<script>
	function submitForm() {
				var username = encodeURI($("#userName").val());
				var password = encodeURI($("#password").val());
				$.ajax({
					type : "post",
					dataType : "html",
					url : "login_doLogin.action",
					data : {
						userName : username,
						password : password
					},
					success : function(msg) {
						if (msg == "fail") {
							$.messager.alert('Warning Message', '用户名密码错误', 'warning')
							return ;
						}
						$('#loginForm').submit();
					}
				});
			}
	function clearForm() {
				$('#loginForm').form('clear');
			}
</script>	
</head>
<body>
<section class="outer">  
        <section class="content">
            <div class="clearFix login_header">
                <!-- <a id="logo" href="/">CMCC</a> -->
                <span class="text">华数装维助手管理平台</span>
            </div>
            <div class="login_banner">
                <ul class="banner_lf" data-parallel="">
                    <li class="layer left-image hover-image" data-zindex="50" style="transform: translateZ(50px);z-index:1;list-style:none">
                        <img src="./image/lfpic1.png">
                    </li>
                    <li class="layer left-image hover-image" data-zindex="100" style="transform: translateZ(100px);z-index:2;list-style:none">
                        <img src="./image/lfpic2.png">
                    </li>
                    <li class="layer left-image hover-image" data-zindex="150" style="transform: translateZ(150px);z-index:3;list-style:none">
                        <img src="./image/lfpic3.png">
                    </li>
                </ul>
                <div class="loginBox">
                    <h4>用户登录</h4>
                    <form class="formBox"  id="loginForm" method="post" action="login_menu.action">
                    
                        <div class="inputBox inputUser clearFix">
                            <span>用户名 ：</span>
                            <p class="clearFix">
                                <i class="user"></i>
                                <input id="userName" type="text"></input>
                            </p>
                        </div>
                        <div class="inputBox clearFix">
                            <span>密&nbsp&nbsp&nbsp码 ：</span>
                            <p class="clearFix">
                                <i class="password"></i>
                                <input id="password"  type="password" name="password" ></input>
                            </p>
                        </div>
                       
                        <p class="tip"><span></span></p>
                        </form>
                    <input class="btn" type="button" value="登 录" onclick="submitForm()">
                </div>
            </div>
        </section>
        <footer class="foot">
            <div>
                <p>技术支持：飞思达技术（北京）有限公司</p>
                <p>浙江华数 版权所有 WASU.COM  Copyright © 2018-2020 All Rights Reserved</p>
            </div>
        </footer>
    </section>
    </body>
</html>