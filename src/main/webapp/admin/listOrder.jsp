<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	$("button.orderPageCheckOrderItems").click(function(){
		var oid = $(this).attr("oid");
		$("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
	});
});

</script>
<style>
	.Connection{
		display: inline-block;
		position: relative;
		margin: 0 5px 0 0;
	}
</style>

<title>订单管理</title>

<div class="workingArea">
	<h1 class="label label-info" >订单管理</h1>
	<br/>
	<a  class="btn btn-success" href="admin_export_order_list">导出</a>
	<a class="btn btn-success"  href="admin_print_order_list">打印</a>
	<br/>
	<br/>
	<h1 class="label label-info" >条件查询</h1>
	<form action="select_order_list">
		<div>
			<div class="Connection">
				<div class="cell-left">
					状态：<input name="status" type="text" class="common-input" value="waitReview">
				</div>
			</div>

			<div class="Connection">
				<div class="cell-left">
					最低金额：<input name="min" type="number" class="common-input">
				</div>
			</div>
			<div class="Connection">
				<div class="cell-left">
					最高金额：<input name="max" type="number" class="common-input">
				</div>
			</div>

		</div>
		<div class="Connection">
			<div class="cell-left">
				<input type="hidden" name="id" value="${o.id}">
				<button type="submit" class="btn btn-success" style="margin: 0px">提 交</button>
			</div>
		</div>
		</table>
	</form>

	<h1 class="label label-info" >超过三天未评论订单</h1>
	<br>
	<a class="btn btn-success"  href="outdate_order">筛选</a>
	<br>

	<br>
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover1  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>状态</th>
					<th>金额</th>
					<th width="100px">房间数量</th>
					<th width="100px">买家名称</th>
					<th>支付时间</th>
					<th>编辑</th>
					<th>删除</th>
					<th>查看</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${os}" var="o">
					<tr>
						<td>${o.id}</td>
						<td>${o.status}</td>
						<td>￥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
						<td align="center">${o.totalNumber}</td>
						<td align="center">${o.user.name}</td>
						<td><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><a href="admin_order_edit?id=${o.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
						<td><a deleteLink="true" href="admin_order_delete?id=${o.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>

						<td>
							<button oid="${o.id}" class="orderPageCheckOrderItems btn btn-primary btn-xs" style="align-top:0px"  >查看详情</button>
						</td>
					</tr>
					<tr class="orderPageOrderItemTR"  oid=${o.id}>
						<td colspan="10" align="center">
							
							<div  class="orderPageOrderItem">
								<table width="800px" align="center" class="orderPageOrderItemTable">
									<c:forEach items="${o.orderItems}" var="oi">
										<tr>
											<td align="left">
												<img width="40px" height="40px" src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
											</td>
											
											<td>
												<a href="foreproduct?pid=${oi.product.id}">
													<span>${oi.product.name}</span>
												</a>											
											</td>
											<td align="right">
											
												<span class="text-muted">${oi.number}间</span>												
											</td>
											<td align="right">
											
												<span class="text-muted">单价：￥${oi.product.promotePrice}</span>												
											</td>

										</tr>
									</c:forEach>
								
								</table>
							</div>
						
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<%--<div class="pageDiv">--%>
		<%--<%@include file="../include/admin/adminPage.jsp" %>--%>
	<%--</div>--%>
	<%--<div class="panel panel-warning addDiv">--%>
		<%--<div class="panel-heading">新增订单</div>--%>
		<%--<div class="panel-body">--%>
			<%--<form method="post" id="addForm" action="admin_user_add">--%>
				<%--<table class="addTable">--%>
					<%--<tr>--%>
						<%--<td>状态</td>--%>
						<%--<td><input id="status" name="status" type="text"--%>
								   <%--class="form-control"></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>金额</td>--%>
						<%--<td><input id="total" name="total" type="text"--%>
								   <%--class="form-control"></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>房间数量</td>--%>
						<%--<td><input id="totalNumber" name="totalNumber" type="text"--%>
								   <%--class="form-control"></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>买家名称</td>--%>
						<%--<td><input id="name" name="name" type="text"--%>
								   <%--class="form-control"></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td>支付时间</td>--%>
						<%--<td><input id="payDate" name="payDate" type="text"--%>
								   <%--class="form-control"></td>--%>
					<%--</tr>--%>

					<%--<tr class="submitTR">--%>
						<%--<td colspan="2" align="center">--%>
							<%--<input type="hidden" name="id" value="${o.id}">--%>
							<%--<button type="submit" class="btn btn-success">提 交</button>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%--</table>--%>
			<%--</form>--%>
		<%--</div>--%>

	<%--</div>--%>


</div>


