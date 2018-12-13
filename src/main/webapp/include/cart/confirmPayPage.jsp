<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<div class="confirmPayPageDiv">
	<div class="confirmPayImageDiv">


		<%--<div class="confirmPayTime3">--%>
			<%--yyyy-MM-dd HH:mm:ss --%>
		<%--</div>--%>
		

	</div>
	<div class="confirmPayOrderInfoDiv">
		<div class="confirmPayOrderInfoText">我已入住，同意支付宝付款</div>
	</div>
	<div class="confirmPayOrderItemDiv">
		<div class="confirmPayOrderItemText">订单信息</div>
		<table class="confirmPayOrderItemTable">
			<thead>
				<th colspan="2">房间</th>
				<th width="120px">单价</th>		
				<th width="120px">数量</th>		
				<th width="120px">房间总价 </th>

			</thead>
			<c:forEach items="${o.orderItems}" var="oi">
				<tr>
					<td><img width="50px" src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg"></td>
					<td class="confirmPayOrderItemProductLink">
						<a href="#nowhere">${oi.product.name}</a>
					</td>
					<td>￥<fmt:formatNumber type="number" value="${oi.product.orignalPrice}" minFractionDigits="2"/></td>
					<td>1</td>
					<td><span class="conformPayProductPrice">￥<fmt:formatNumber type="number" value="${oi.product.promotePrice}" minFractionDigits="2"/></span></td>

				</tr>
			</c:forEach>
		</table>
		
		<div class="confirmPayOrderItemText pull-right">
			实付款： <span class="confirmPayOrderItemSumPrice">￥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></span>
		</div>
		
		
	</div>
	<div class="confirmPayOrderDetailDiv">
		
		<table class="confirmPayOrderDetailTable">
			<tr>
				<td>订单编号：</td>
				<td>${o.orderCode} <img width="23px" src="img/site/comformPayFlow.png"></td>
			</tr>
			<tr>
				<td>卖家昵称：</td>
				<td>GoTrip <span class="confirmPayOrderDetailWangWangGif"></span></td>
			</tr>

			<tr>
				<td>成交时间：</td>
				<td><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</table>
		
	</div>
	<div class="confirmPayButtonDiv">
		<center>
		<a href="${pageContext.request.contextPath}/foreorderConfirmed?oid=${o.id}"><button class=" confirmPay">确认支付</button></a>
		</center>
	</div>
</div>