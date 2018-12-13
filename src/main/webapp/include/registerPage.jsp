<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script>
$(function(){
	
	<c:if test="${!empty msg}">
	$("span.errorMessage").html("${msg}");
	$("div.registerErrorMessageDiv").css("visibility","visible");		
	</c:if>
	
	$(".registerForm").submit(function(){
		if(0==$("#name").val().length){
			$("span.errorMessage").html("请输入用户名");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if(0==$("#password").val().length){
			$("span.errorMessage").html("请输入密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if(0==$("#repeatpassword").val().length){
			$("span.errorMessage").html("请输入重复密码");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		
		if($("#password").val() !=$("#repeatpassword").val()){
			$("span.errorMessage").html("重复密码不一致");
			$("div.registerErrorMessageDiv").css("visibility","visible");			
			return false;
		}		

		return true;
	});
})
</script>
<style>
	div.registerDiv{
		margin: 10px 20px;
		text-align: center;
	}
	table.registerTable{
		color: #3C3C3C;
		font-size: 16px;
		table-layout: fixed;
		margin-top: 50px;
	}
	table.registerTable td{
		padding: 10px 30px;
	}
	table.registerTable input{
		border: 1px solid #DEDEDE;
		width: 213px;
		height: 36px;
		font-size: 14px;
	}
	table.registerTable button{
		width: 170px;
		height: 36px;
		border-radius: 2px;
		color: white;
		background-color: #00a6c4;
		border-width: 0px;
	}
	td.registerTip{
		font-weight: bold;
	}
	td.registerTableLeftTD{
		width: 300px;
		text-align: right;
	}
	td.registerTableRightTD{
		width: 300px;
		text-align: left;
	}
	td.registerButtonTD{
		text-align: center;
	}
</style>

<form method="post" action="${pageContext.request.contextPath}/user/foreregister" class="registerForm">


<div class="registerDiv">
	<div class="registerErrorMessageDiv">
		<div class="alert alert-danger" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
		  	<span class="errorMessage"></span>
		</div>		
	</div>

	
	<table class="registerTable" align="center">
		<tr>
			<td  class="registerTip registerTableLeftTD">注册</td>
			<td></td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">账号</td>
			<td  class="registerTableRightTD"><input id="name" name="name" placeholder="输入账号" > </td>
		</tr>

		<tr>
			<td class="registerTableLeftTD">登陆密码</td>
			<td class="registerTableRightTD"><input id="password" name="password" type="password"  placeholder="输入密码" > </td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">密码确认</td>
			<td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入密码" > </td>
		</tr>
				
		<tr>
			<td colspan="2" class="registerButtonTD">
				<a href="registerSuccess.jsp"><button>提   交</button></a>
			</td>
		</tr>				
	</table>
</div>
</form>