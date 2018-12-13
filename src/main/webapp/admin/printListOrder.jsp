<%--
  Created by IntelliJ IDEA.
  User: Bolvvv
  Date: 2018/7/10
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/include/admin/adminHeader.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="listDataTableDiv">
    <button class="btn btn-success" onclick="beforePrint();window.print();afterPrint();" class="no_print" id="printButton">打印</button>
    <table class="table table-striped table-bordered table-hover1  table-condensed">
        <thead>
        <tr class="success">
            <th>ID</th>
            <th>状态</th>
            <th>金额</th>
            <th width="100px">房间数量</th>
            <th width="100px">买家名称</th>
            <th>支付时间</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    window.onbeforeprint=beforePrint;
    window.onafterprint=afterPrint;
    //打印之前隐藏不想打印出来的信息
    function beforePrint(){
        $('#printButton').hide(); //隐藏按钮
    }
    //打印之后将隐藏掉的信息再显示出来
    function afterPrint() {
        $('#printButton').show(); //显示面按钮
    }
</script>
</html>
