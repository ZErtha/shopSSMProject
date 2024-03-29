<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
			}
		</script>
</HEAD>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 20px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="18%">序号</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">删除</td>
							</tr>
						   <c:forEach items="${productList}" var="product">
							  <tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 20px" align="center"
									width="18%">${product.pid}</td>
								<td style="CURSOR: hand; HEIGHT: 20px" align="center"
									width="17%"><img width="40" height="45" src="${product.pimage}"></td>
								<td style="CURSOR: hand; HEIGHT: 20px" align="center"
									width="17%">${product.pname}</td>
								<td style="CURSOR: hand; HEIGHT: 20px" align="center"
									width="17%">${product.shopPrice}</td>
								<td style="CURSOR: hand; HEIGHT: 20px" align="center"
									width="17%">
									 <c:if test="${product.isHot==0}">否</c:if>
									 <c:if test="${product.isHot==1}">是</c:if>
							    </td>
								<td align="center" style="HEIGHT: 20px"><a
									href="${ pageContext.request.contextPath }/ProductInfoServlet?pid=${product.pid}&admin=admin">
										<img
										src="${pageContext.request.contextPath}/images/i_edit.gif"
										border="0" style="CURSOR: hand">
								</a></td>

								<td align="center" style="HEIGHT: 20px"><a href="DeleteProductServlet?pid=${product.pid}"> <img
										src="${pageContext.request.contextPath}/images/i_edit.gif"
										border="0" style="CURSOR: hand">
								  </a></td>
							  </tr>
						  </c:forEach>	
						</table>
					</td>
				</tr>

			</TBODY>
		</table>
<%--		分页--%>
		<div style="width: 380px; margin: 0 auto; margin-top: 5px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
			<c:if test="${currentPage==1}">
			  <li class="disabled"><a href="#" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
		    </c:if>
		    <c:if test="${currentPage!=1}">
			  <li><a href="adminProductList?currentPage=${currentPage-1}" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
		    </c:if>
			
			
			<c:forEach begin="1" end="${totalPage}" var="pageNum">
			   <c:if test="${currentPage==pageNum}">
			      <li class="active"><a href="adminProductList?currentPage=${pageNum}">${pageNum }</a></li>
			   </c:if>
			   <c:if test="${currentPage!=pageNum }">
			      <li><a href="adminProductList?currentPage=${pageNum}">${pageNum}</a></li>
			   </c:if>
			</c:forEach>
			
			<c:if test="${currentPage!=totalPage}">
			    <li><a href="adminProductList?currentPage=${currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
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
	</form>
</body>
</HTML>

