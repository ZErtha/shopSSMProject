<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员信息</title>
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

    <script type="text/javascript">
        $(document).ready(function () {

            $("#confirmpwd").focusout(function () {

                if ($("#confirmpwd").val() != $("#Password").val()) {
                    $("#error").show();
                    $("#confirmpwd").val("");
                    $("#submit").attr("disabled", "true")
                } else {
                    $("#error").hide();
                    $("#submit").removeAttr("disabled");
                }

            })


        })
    </script>
</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>

<div class="container"
     style="width: 100%; background: url('image/regist_bg.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8"
             style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
            <font>会员信息</font>USER REGISTER
            <form class="form-horizontal" style="margin-top: 5px;" action="modifyUserInfo" method="post">
                <div class="form-group">
                    <input type="hidden" name="uid" value="${user.uid}"/>
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" disabled="disabled"
                               placeholder="请输入用户名" name="username" value="${user.username}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="Password"
                               placeholder="请输入密码" name="password" value="${user.password}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmedPwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmedPwd"
                               placeholder="请输入确认密码" value="${user.password}">
                        <font id="error" style="color:red;display:none">两个密码不一致，请确认后重新输入</font>
                    </div>

                </div>

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name"
                               placeholder="请输入姓名" name="name" value="${user.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="UserEmail" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="UserEmail"
                               placeholder="Email" name="email" value="${user.email }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="UserTelephone" class="col-sm-2 control-label">手机号码</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="UserTelephone"
                               placeholder="请输入手机号码" name="telephone" value="${user.telephone}">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="UserSex" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline"> <input type="radio"
                                                            name="sex" id="UserSex" value="男"
                                                            <c:if test="${user.sex=='男'}">checked="checked"</c:if>
                        >
                            男
                        </label> <label class="radio-inline"> <input type="radio"
                                                                     name="sex" id="UserSex2" value="女"
                                                                     <c:if test="${user.sex=='女'}">checked="checked"</c:if>>
                        女
                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="UserDate" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <%--传入的user.birthday是Date时间类型，需要转换--%>
                        <fmt:formatDate var="dateTest" value="${user.birthday}" pattern="yyyy-MM-dd"/>
                        <input type="date" class="form-control" id="UserDate" name="birthday" value="${dateTest}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="UserAddress" class="col-sm-2 control-label">地址</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="UserAddress"
                               placeholder="请输入常用收货地址" name="address" value="${user.address}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" width="100" value="修改" name="submit" id="submit"
                               style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                        <input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/></div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




