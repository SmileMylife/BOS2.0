<%--
  Created by IntelliJ IDEA.
  User: Smile_Mylife
  Date: 2017/8/14
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>shiro权限控制</title>
</head>
<body>
    <shiro:hasPermission name="courier:add">
        <button>添加</button>
    </shiro:hasPermission>
    <shiro:hasPermission name="courier:edit">
        <button>编辑</button>
    </shiro:hasPermission>
</body>
</html>
