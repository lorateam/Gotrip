<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<script>
$(function(){
	
	<c:if test="${!empty msg}">
	$("span.errorMessage").html("${msg}");
	$("div.loginErrorMessageDiv").show();		
	</c:if>
	
	$("form.loginForm").submit(function(){
		if(0==$("#name").val().length||0==$("#password").val().length){
			$("span.errorMessage").html("请输入账号密码");
			$("div.loginErrorMessageDiv").show();			
			return false;
		}
		return true;
	});
	
	$("form.loginForm input").keyup(function(){
		$("div.loginErrorMessageDiv").hide();	
	});
	
	
	
	var left = window.innerWidth/2+162;
	$("div.loginSmallDiv").css("left",left);
})
</script>

</head>
<style>
	div.simpleLogo{
		padding: 32px 0px;
	}
	img.loginBackgroundImg{
		display: block;
		margin: 0px auto;
	}
	div.loginSmallDiv{
		background-color: white;
		position: absolute;
		right: 30%;
		top: 180px;
		width: 350px;
		height: 400px;
		padding: 60px 25px 80px 25px;
	}
	div.login_acount_text{
		color: #3C3C3C;
		font-size: 16px;
		font-weight: bold;
	}
	div.loginInput{
		border: 1px solid #CBCBCB;
		margin: 20px 0px;
	}
	div.loginInput span.loginInputIcon{
		margin: 0px;
		background-color: #CBCBCB;
		width: 40px;
		height: 40px;
		display:inline-block;
	}
	span.loginInputIcon span.glyphicon{
		font-size: 22px;
		position: relative;
		left: 9px;
		top: 9px;
		color: #606060;
	}
	div.loginInput input{
		display: inline-block;
		border: 0px solid transparent;
		width: 244px;
		height: 30px;
		position: relative;
		left: 6px;
		top: 6px;
	}
	body{
		font-size: 12px;
		font-family: Arial;
	}
	a{
		color:#999;
	}
	a:hover{
		text-decoration:none;
		color: #00a6c4;
	}
	button.redButton{
		color: white;
		background-color: #00a6c4;
		font-size: 14px;
		font-weight: bold;
	}
</style>


<%--<div id="loginDiv">--%>

	<%--<img src="images/login1.png" class="loginBackgroundImg" id="loginBackgroundImg">--%>
	<%--<div class="loginSmallDiv" id="loginSmallDiv">--%>
		<%--<div class="login_acount_text">账户登录</div>--%>
		<%--<div class="loginInput ">--%>
                <%--<span class="loginInputIcon ">--%>
                    <%--<span class=" glyphicon glyphicon-user"></span>--%>
                <%--</span>--%>
			<%--<input type="text" placeholder="手机/会员名/邮箱" name="name" id="name">--%>
		<%--</div>--%>
		<%--<div class="loginInput ">--%>
                <%--<span class="loginInputIcon ">--%>
                    <%--<span class=" glyphicon glyphicon-lock"></span>--%>
                <%--</span>--%>
			<%--<input type="password" placeholder="密码" name="password" id="password">--%>
		<%--</div>--%>
		<%--<div>--%>
			<%--<a href="#nowhere" class="notImplementLink">忘记登录密码</a>--%>
			<%--<a class="pull-right" href="#signup.html">免费注册</a>--%>
		<%--</div>--%>
		<%--<div style="margin-top:20px">--%>
			<%--<button type="button" class="btn btn-block redButton">登录</button>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</div>--%>


<div id="loginDiv" style="position: relative">



	<img id="loginBackgroundImg" class="loginBackgroundImg" src="images/login1.png">

	<form class="loginForm" action="forelogin" method="post">
		<div id="loginSmallDiv" class="loginSmallDiv">
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger" >
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				  	<span class="errorMessage"></span>
				</div>
			</div>

			<div class="login_acount_text">账户登录</div>
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
			</div>

			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码" type="text">
			</div>

			<div>
				<a class="notImplementLink" href="#nowhere">忘记登录密码</a>
				<a href="register.jsp" class="pull-right">免费注册</a>
			</div>
			<div style="margin-top:20px">
				<button class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>
	</form>


</div>