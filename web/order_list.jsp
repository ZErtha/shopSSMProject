<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--使用fmt函数需在jsp中引入--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的订单</title>
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
    <c:if test="${empty orderList}">
        <div style="text-align:center">
            <img alt="" src="images/购物车空了.jpg"><br><br>
            <a href="productList?currentPage=1">暂无订单，赶紧去购物</a>
        </div>
    </c:if>
    <c:if test="${!empty orderList}">
        <div class="row">
            <div style="margin: 0 auto; margin-top: 10px; width: 950px;text-align:center;">
                <strong>我的订单</strong>
                <table class="table table-bordered">
                        <%--					<c:forEach>标签，需要与el表达式联合使用--%>
                        <%--					< c: forEach>标签的语法定义如下所示。--%>
                        <%--					< c: forEach var="每个变量名字"   items="要迭代的list"   varStatus="每个对象的状态"--%>
                        <%--					begin="循环从哪儿开始"    end="循环到哪儿结束"    step="循环的步长">--%>
                        <%--					循环要输出的东西--%>
                        <%--				</ c: forEach>--%>
                    <c:forEach items="${orderList}" var="order">
                        <tbody>
                        <tr class="success">
                            <th colspan="5">订单编号:${order.oid}</th>
                        </tr>
                        <tr class="warning">
                            <th>图片</th>
                            <th>商品</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>小计</th>
                        </tr>
                            <%--						items要进行迭代的集合--%>
                        <c:forEach items="${order.orderItems}" var="orderItem">
                            <tr class="active">
                                <td width="60" width="40%"><input type="hidden" name="id"
                                                                  value="22"> <img src="${orderItem.product.pimage}"
                                                                                   width="70"
                                                                                   height="60"></td>
                                <td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
                                <td width="15%">￥${orderItem.product.shopPrice}</td>
                                <td width="15%">${orderItem.count}</td>
                                <td width="15%"><span class="subtotal">￥${orderItem.subtotal}</span></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>合计:&nbsp;&nbsp;<font style="color:red">￥${order.total}</font></td>
                            <td><fmt:formatDate value="${order.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>操作</td>
                            <td>
                                <a href="manageOrder?state=${order.state}&oid=${order.oid}">
                                    <c:if test="${order.state==0}">去付款</c:if>
                                    <c:if test="${order.state==1}">商家未发货，点击催单</c:if>
                                    <c:if test="${order.state==2}">确定收货</c:if>
                                    <c:if test="${order.state==3}">去评价</c:if>
                                    <c:if test="${order.state==4}">查看订单详情</c:if>
                                </a>
                                <br>
                                <a href="assessOrder?oid=${order.oid}">
                                    <c:if test="${order.state==4 && empty order.assess && order.assess eq null }">
                                        去评价
                                    </c:if>
                                </a>
                            </td>
                            <td>
                                <c:if test="${order.state==4}">交易成功</c:if>
                                <c:if test="${order.state==5}">订单已取消</c:if>
                                <c:if test="${not empty order.assess && !(order.assess eq null) }">
                                    已评价
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>

            </div>
        </div>
        <!--分页 -->
        <div style="width: 380px; margin: 0 auto; margin-top: 50px;">
            <ul class="pagination" style="text-align: center; margin-top: 10px;">
                <c:if test="${currentPage==1}">
                    <li class="disabled"><a href="#" aria-label="Previous"><span
                            aria-hidden="true">&laquo;</span></a></li>
                </c:if>
                <c:if test="${currentPage!=1}">
                    <li><a href="OrderListServlet?currentPage=${currentPage-1}" aria-label="Previous"><span
                            aria-hidden="true">&laquo;</span></a></li>
                </c:if>

                <c:forEach begin="1" end="${totalPage}" var="pageNum">
                    <c:if test="${currentPage==pageNum}">
                        <li class="active"><a href="OrderListServlet?currentPage=${pageNum}">${pageNum }</a></li>
                    </c:if>
                    <c:if test="${currentPage!=pageNum }">
                        <li><a href="OrderListServlet?currentPage=${pageNum}">${pageNum}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${currentPage!=totalPage}">
                    <li><a href="OrderListServlet?currentPage=${currentPage+1}" aria-label="Next"> <span
                            aria-hidden="true">&raquo;</span>
                    </a>
                    </li>
                </c:if>

                <c:if test="${currentPage==totalPage}">
                    <li class="disabled"><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                    </a>
                    </li>
                </c:if>
            </ul>
        </div>
        <!-- 分页结束 -->
    </c:if>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>