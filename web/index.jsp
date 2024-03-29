<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>惠多多(HDD.COM)-正品低价、品质保障、配送及时、轻松购物！</title>
		<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
<%--		<link rel="stylesheet" href="css/index.css" type="text/css" />--%>
		<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container-fluid">

			<!-- 引入header.jsp -->
			<jsp:include page="/header.jsp"></jsp:include>

			<!-- 轮播图 -->
			<div class="container-fluid">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- 轮播图的中的小点 -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>
					<!-- 轮播图的轮播图片 -->
					<div class="carousel-inner" role="listbox">

						<div class="item active">
							<img src="image/index/1.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>

						<div class="item">
							<img src="image/index/2.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>

						<div class="item">
							<img src="image/index/3.jpg">
							<div class="carousel-caption">
								<!-- 轮播图上的文字 -->
							</div>
						</div>
					</div>

					<!-- 上一张 下一张按钮 -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			
			<!-- 热门商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>热门商品&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				<div class="col-md-12">
					

				<c:forEach items="${hotProductList}" var="hotProduct">
					<div class="col-md-2" style="text-align:center;height:210px;padding:10px 0px;">
						<a href="productInfo?pid=${hotProduct.pid}">
							<img src="${hotProduct.pimage }" width="130" height="130" style="display: inline-block;">
						</a>
						<p style="height:26px;"><a href="productInfo?pid=${hotProduct.pid}" style='color:#666'>${hotProduct.pname}</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${hotProduct.shopPrice}</font></p>
					</div>
	           </c:forEach>
					
				</div>
			</div>
			
			<!-- 广告条 -->
            <div class="container-fluid">
				<img src="products/hao/ad.jpg" width="100%"/>
			</div>
			
			<!-- 最新商品 -->
			<div class="container-fluid">
				<div class="col-md-12">
					<h2>最新商品&nbsp;&nbsp;<img src="img/title2.jpg"/></h2>
				</div>
				
				<div class="col-md-12">
					
				<c:forEach items="${newProductList}" var="newProduct">
					<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
						<a href="productInfo?pid=${newProduct.pid}">
							<img src="${newProduct.pimage}" width="130" height="130" style="display: inline-block;">
						</a>
						<p style="height:26px;"><a href="productInfo?pid=${newProduct.pid}" style='color:#666'>${newProduct.pname}</a></p>
						<p><font color="#E4393C" style="font-size:16px">&yen;${newProduct.shopPrice}</font></p>
					</div>
	            </c:forEach>
					
				</div>
			</div>			
			
			<!-- 引入footer.jsp -->
			<jsp:include page="/footer.jsp"></jsp:include>
			
		</div>
	</body>

</html>