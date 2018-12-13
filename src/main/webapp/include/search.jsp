<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>

		<style>

			div.searchDiv1{
				background-color: #ffffff;
				width: 400px;
				margin: 50px auto;
				padding: 1px;
				height: 40px;
				display: block;
			}
			div.searchDiv1 input{
				width: 275px;
				border: 1px solid;
				height: 36px;
				margin: 1px;
				outline:none;
			}
			div.searchDiv1 button{
				width: 110px;
				border: 1px solid transparent;
				background-color: #55bac4;
				color: white;
				font-size: 20px;
				font-weight: bold;
			}


			body{
				font-size:12px;
				font-family:Arial;
			}
			a{
				color:#999;
			}
			a:hover{
				text-decoration:none;
				color:#C40000;
			}

		</style>

</head>
<body>

	<form action="foresearch" method="post" >
		<div class="searchDiv1" align="center">
			<input name="keyword" type="text" value="${param.keyword}" placeholder="派大星的小屋 ">
			<button  type="submit" class="${pageContext.request.contextPath}/searchButton">搜索</button>
		</div>
	</form>
</body>
</html>