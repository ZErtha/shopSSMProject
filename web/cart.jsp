<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>惠多多商城购物车</title>
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

        font {
            color: #3164af;
            font-size: 18px;
            font-weight: normal;
            padding: 0 10px;
        }
    </style>
</head>

<body>
<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>

<div class="container">
    <c:if test="${empty cart}">
        <div style="text-align:center">
            <img alt="" src="images/购物车空了.jpg"><br><br>
            <a href="productList?currentPage=1">赶紧去购物</a>
        </div>
    </c:if>
    <c:if test="${!empty cart}">
        <div class="row">

            <div style="margin:0 auto; margin-top:10px;width:950px;">
                <strong style="font-size:16px;margin:5px 0;">订单详情</strong>
                <table class="table table-bordered">
                    <tbody>
                    <tr class="warning">
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${cart.cartItems}" var="entry">
                        <tr class="active">
                            <td width="60" width="40%">
                                <input type="hidden" name="id" value="22">
                                <img src="${entry.value.product.pimage}" width="70" height="60">
                            </td>
                            <td width="30%">
                                <a target="_blank">${entry.value.product.pname}</a>
                            </td>
                            <td width="20%">
                                ￥${entry.value.product.shopPrice}
                            </td>
                            <td width="10%">
                                <form action="modifyCartBuyNum" method="get">
                                    <input type="hidden" name="pid" value="${entry.value.product.pid}">
                                    <input type="text" name="buyNum" value="${entry.value.buyNum}" maxlength="4"
                                           size="5">
                                    <input type="submit" value="修改">
                                </form>
                            </td>
                            <td width="15%">
                                <span class="subtotal">￥${entry.value.subTotal}</span>
                            </td>
                            <td>
                                <a href="deleteCartByProduct?pid=${entry.value.product.pid}" class="delete">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div style="margin-right:130px;">
            <div style="text-align:right;">
                <em style="color:#ff6600;">
                    <c:if test="${empty user}">
                        登录后确认是否享有优惠&nbsp;&nbsp;
                    </c:if>
                </em>&nbsp; 商品总价: <strong style="color:#ff6600;">￥${cart.total}</strong>
            </div>
            <div style="text-align:right;margin-top:10px;margin-bottom:10px;">
                <a href="cleanCart" id="clear" class="clear">清空购物车</a>
                <a href="createOrder">
                    <input type="submit" width="100" value="结算" name="submit" border="0" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
                </a>
            </div>
        </div>
    </c:if>

</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>