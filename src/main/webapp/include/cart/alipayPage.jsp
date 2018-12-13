<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<div class="aliPayPageDiv">

	
	<div>
		<span class="confirmMoneyText">扫一扫付款（元）</span>
		<span class="confirmMoney">
		￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>
		
	</div>
	<div>
		<img class="aliPayImg" src="img/site/alipay2wei.png">
	</div>

	
	<div>
		<a href="payed.jsp"><button class="confirmPay">假装扫码成功</button></a>
	</div>

</div>