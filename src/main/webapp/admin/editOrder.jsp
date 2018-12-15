<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑订单</title>


<script>
    $(function(){

        $("#editForm").submit(function(){
            if(!checkEmpty("name","分类名称"))
                return false;

            return true;
        });
    });

</script>

<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="admin_order_list">所有订单</a></li>
        <li class="active">编辑订单</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑订单</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_order_update"  enctype="multipart/form-data">
                <table class="editTable">
                    <tr>
                        <td>订单状态</td>
                        <td><input  id="status" name="status" value="${c.status}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>订单金额</td>
                        <td><input  id="total" name="total" value="${c.total}" type="number" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>游客姓名</td>
                        <td><input  id="receiver" name="receiver" value="${c.receiver}" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${c.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
