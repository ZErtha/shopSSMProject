<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${product.pname}-惠多多</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css" />
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>

<body>
<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div
                style="border: 1px solid #e4e4e4; width: 930px; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
            <ol class="breadcrumb">
                <li><a href="index">首页</a></li>
                <c:forEach items="${categoryList}" var="category">
                    <c:if test="${product.cid==category.cid}">
                        <li><a href="productList?currentPage=1&cid=${product.cid}">${category.cname}</a></li>
                    </c:if>
                </c:forEach>
                <li><a>${product.pname}</a></li>
            </ol>

        </div>

        <div style="margin: 0 auto; width: 950px;">
            <div class="col-md-6">
                <img style="opacity: 1; width: 400px; height: 350px;" title=""
                     class="medium"
                     src="${product.pimage}">
            </div>

            <div class="col-md-6">
                <div>
                    <strong>${product.pname}</strong>
                </div>
                <div
                        style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
                    <div>编号：${product.pid}</div>
                </div>

                <div style="margin: 10px 0 10px 0;">
                    亿家价: <strong style="color: #ef0101;">￥：${product.shopPrice}</strong> 参 考 价：
                    <del>￥${product.marketPrice }</del>
                </div>

                <div style="margin: 10px 0 10px 0;">
                    促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
                             style="background-color: #f07373;">限时抢购</a>
                </div>

                <div
                        style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #fffee6;">
                    <div style="margin: 5px 0 10px 0;">白色</div>
                    <%--form表单是可以提交数辉 到后台的控制器controller接收数据的地址
                     就是action 就是目标地址衣单的作用就是可以提交很多个数据
                     购物车的需求就是告诉后端要加什么商品 这个商品加几个--%>
                    <form action="addCart" method="get">
                        <%--产品编号要添加到购物车的产品的编号 hidden是隐藏的意思产品编号放到表单中
                         然后不显不出来hidden周性这个pid的作用就是 诉系统当前的要添加的是哪个商品--%>
                        <input type="hidden" name="pid" value="${product.pid}">
                        <%--要添加几个商品 购买数量:--%>
                        <div
                                style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
                            购买数量: <input id="quantity" name="buyNum" value="1"
                                             maxlength="4" size="10" type="text">
                        </div>

                        <div style="margin: 20px 0 10px 0;; text-align: center;">
                            <input
                                    style="background: url('./images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0); height: 36px; width: 127px;"
                                    value="加入购物车" type="submit">&nbsp;收藏商品
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div style="width: 950px; margin: 0 auto;">
            <div
                    style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
                <strong>商品介绍</strong>
            </div>

            <div>
                <textarea rows="10" cols="50">${product.pdesc}</textarea>
            </div>

            <div style="background-color: #d3d3d3; width: 900px;">
                <table class="table table-bordered">
                    <tbody>
                    <tr class="active">
                        <th><strong>商品评论</strong></th>
                    </tr>
                    <tr class="warning">
                        <th>暂无商品评论信息 <a>[发表商品评论]</a></th>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</div>


<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>