<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/include/admin/adminHeader.jsp" %>
<%@include file="/include/admin/adminNavigator.jsp" %>

<script>
</script>

<title>用户管理</title>


<div class="workingArea">
    <h1 class="label label-info">用户管理</h1>

    <br>
    <br>
    <a href="admin_export_user_list">导出</a>
    <a href="admin_print_user_list">打印</a>
    <div class="listDataTableDiv">
        <c:if test="${us==null || fn:length(us) == 0}">
            <tr>
                <td colspan="4">还没用户注册，快来抢购吧！</td>
            </tr>
        </c:if>
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>用户名称</th>
                <th>密码</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${us!=null && fn:length(us) != 0}">
                <c:forEach items="${us}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.password}</td>
                        <td><a href="admin_user_edit?id=${u.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a deleteLink="true" href="admin_user_delete?id=${u.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>

                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%--<%@include file="/include/admin/adminPage.jsp" %>--%>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增用户</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_user_add">
                <table class="addTable">
                    <tr>
                        <td>用户名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>用户密码</td>
                        <td><input id="password" name="password" type="text"
                                   class="form-control"></td>
                    </tr>

                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${u.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>
