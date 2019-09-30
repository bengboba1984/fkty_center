<!DOCTYPE html>
<html>
<head>
    <title>Role Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="css/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>

    <div class="easyui-panel" style="padding:5px">
        <ul id="tt" class="easyui-tree" data-options="url:'role_moduleList.action',onlyLeafCheck:true,method:'get',animate:true,checkbox:true"></ul>
    </div>
    <script type="text/javascript">
        function getChecked(){
            var nodes = $('#tt').tree('getChecked');
            var s = '';
            for(var i=0; i<nodes.length; i++){
                if (s != '') s += ',';
                s += nodes[i].text;
            }
            alert(s);
        }
    </script>
</body>
</html>
