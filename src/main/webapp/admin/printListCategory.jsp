<%--
  Created by IntelliJ IDEA.
  User: Bolvvv
  Date: 2018/7/10
  Time: 21:09
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
    <SCRIPT>
        bdhtml=window.document.body.innerHTML;  //获取到页面html
        sprnstr="<!--startprint-->";
        eprnstr="<!--endprint-->"; //找到两个标签
        prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //切除标签以外的东西
        window.document.body.innerHTML=prnhtml;
        window.print();
        window.document.body.innerHTML=bdhtml;//将页面重新改回原始样式
    </SCRIPT>
</head>
<body>
<div class="listDataTableDiv">
    <button class="btn btn-success" onclick="beforePrint();window.print();afterPrint();" class="no_print" id="printButton">打印</button>
    <table class="table table-striped table-bordered table-hover  table-condensed">
        <thead>
        <tr class="success">
            <th>ID</th>
            <th>图片</th>
            <th>分类名称</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${thecs}" var="c">
            <tr>
                <td>${c.id}</td>
                <td><img height="40px" src="/img/category/${c.id}.jpg"></td>
                <td>${c.name}</td>
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
