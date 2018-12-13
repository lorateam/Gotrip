<%--
  Created by IntelliJ IDEA.
  User: Bolvvv
  Date: 2018/7/10
  Time: 22:56
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
    <table class="table table-striped table-bordered table-hover  table-condensed">
        <thead>
        <tr class="success">
            <th>ID</th>
            <th>用户名称</th>
            <th>密码</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${us!=null && fn:length(us) != 0}">
            <c:forEach items="${us}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.name}</td>
                    <td>${u.password}</td>
                </tr>
            </c:forEach>
        </c:if>
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
