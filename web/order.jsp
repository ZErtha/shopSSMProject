<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单详情</title>
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
        <div style="margin: 0 auto; margin-top: 10px; width: 950px;">
            <strong>订单详情</strong>
            <table class="table table-bordered">
                <tbody>
                <tr class="warning">
                    <th colspan="5">订单编号:${order.oid}</th>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <c:forEach items="${order.orderItems}" var="orderItem">
                    <tr class="active">
                        <td width="60" width="40%"><input type="hidden" name="id"
                                                          value="22"> <img src="${orderItem.product.pimage }" width="70"
                                                                           height="60"></td>
                        <td width="30%"><a target="_blank"> ${orderItem.product.pname }</a></td>
                        <td width="20%">￥${orderItem.product.shopPrice }</td>
                        <td width="10%">${orderItem.count }</td>
                        <td width="15%"><span class="subtotal">￥${orderItem.subtotal }</span></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="text-align: right; margin-right: 120px;">
            商品金额: <strong style="color: #ff6600;">￥${order.total}</strong>
        </div>

    </div>

    <div style="width:100%;text-align:center">
        <div><label>地&nbsp;&nbsp;&nbsp;址</label>
            <input type="text" disabled="disabled"
                   value="${order.address}">
        </div>

        <div><label>收货人</label>
            <input type="text" disabled="disabled"
                   value="${order.name}">
        </div>

        <div><label>电&nbsp;&nbsp;&nbsp;话</label>
            <input type="text" disabled="disabled"
                   value="${order.telephone}">
        </div>
        <hr/>
        <div>
            <label>评价</label>
            <div>
                <textarea disabled="disabled" cols="50" rows="5">${order.assess}</textarea>
            </div>
        </div>

        <hr/>
        <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>


    </div>

</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>