<%--
  Created by IntelliJ IDEA.
  User: z
  Date: 2023/4/27
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <!-- 引入样式文件和动态控制 -->
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="<%=path %>/js/jquery-1.11.3.min.js"></script>
  <script src="<%=path %>/js/bootstrap.min.js"></script>
  <!--主要样式控制-->
  <link href="<%=path %>/css/admin.css" rel="stylesheet">

  <title>商城管理平台</title>

</head>
<body>
<!--顶部导航栏部分-->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=path %>/index" target="_top"><span class="glyphicon glyphicon-home"></span> 回到首页</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li role="presentation">
          <a href="<%=path %>/user_info.jsp" target="_top">当前用户：<span class="badge">${user.username}</span></a>
        </li>
        <li>
          <a href="admin/adminLogin.jsp">
            <span class="glyphicon glyphicon-log-out"></span>退出登录</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- 中间主体内容部分 -->
<div class="pageContainer">
  <!-- 左侧导航栏 -->
  <div class="pageSidebar">
    <ul class="nav nav-stacked nav-pills">
      <li role="presentation" class="active">
        <a href="<%=path %>/adminCategoryList" target="mainFrame" >分类管理</a>
      </li>
      <li role="presentation">
        <a href="<%=path %>/adminProductList?currentPage=1" target="mainFrame">商品管理</a>
      </li>
      <li role="presentation">
        <a href="<%=path %>/adminAllOrderList?currentPage=1" target="mainFrame">订单管理</a>
      </li>

    </ul>
  </div>

  <!-- 左侧导航和正文内容的分隔线 -->
  <div class="splitter"></div>
  <!-- 正文内容部分 -->
  <div class="pageContent">
    <iframe src="<%=path %>/adminCategoryList" id="mainFrame" name="mainFrame"
            frameborder="0" width="100%" height="100%" frameBorder="0">
    </iframe>
  </div>

</div>
<!-- 底部页脚部分 -->
<div class="footer">
  <p class="text-center">
    2023 &copy; 商城管理平台
  </p>
</div>

<script type="text/javascript">
  $(".nav li").click(function() {
    $(".active").removeClass('active');
    $(this).addClass("active");
  });

</script>
</body>
</html>
