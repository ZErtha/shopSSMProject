<%--
  Created by IntelliJ IDEA.
  User: z
  Date: 2023/4/27
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>管理员登录</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <!-- Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!--font-awesome 核心我CSS 文件-->
    <%--    <link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">--%>
    <!-- 在bootstrap.min.js 之前引入 -->
    <script src="<%=path %>/js/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=path %>/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="<%=path %>/css/adminLogin.css">
    <script type="text/javascript">
        function toIndex(){
            window.location.href = "<%=path %>/index";
        }
    </script>

</head>
<body>
<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="<%=path %>/adminLogin">
            <h3 class="form-title">管理员登录</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <!--        用户名图标和用户名输入框            -->
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <input class="form-control" type="text" name="username" id="username" placeholder="请输入用户名" required autofocus>
                </div>
                <!--        密码图标和密码输入框                -->
                <div class="form-group">
                    <i class="fa fa-key" aria-hidden="true"></i>
                    <input class="form-control " type="password" name="password" id="password" placeholder="请输入密码" required>
                </div>
                <!--         登录按钮           -->
                <div class="form-group">
                    <input class="btn btn-success pull-left" type="button" onclick="toIndex()" value="回到商城"/>
                    <input type="submit" value="进入管理中心" class="btn btn-success pull-right">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
