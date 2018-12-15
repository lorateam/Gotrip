<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<html lang="ja">
<head>
	<!-- Basic Page Needs
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<meta charset="utf-8">
	<title>GoTrip</title>
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Mobile Specific Metas
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- FONT
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link href="css/font-awesome.min.css" rel="stylesheet">

	<!-- CSS
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/skeleton.css">

	<!-- Favicon
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link rel="icon" type="image/png" href="images/favicon.png">
	<link rel="stylesheet" href="css/swiper.min.css">
</head>
<body>
<header id="top-head">
	<div class="container">
		<div class="row">
			<div class="three columns">
				<div id="mobile-head">
					<h1 id="title"><a href="index.jsp"><img src="images/logo.png" alt="GoTrip" ></a></h1>
					<div id="nav-toggle">
						<div>
							<span></span>
							<span></span>
							<span></span>
						</div>
					</div><!--#navToggle END-->
				</div>
			</div>
			<div class="six columns">
				<nav id="global_navi">
					<ul>
						<li><a href="${pageContext.request.contextPath}/forestory">故事/体验</a></li>
						<li><a href="${pageContext.request.contextPath}/home">房源</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/admin_category_list">管理</a></li>
						<c:if test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/register.jsp">免费注册</a></li>
						</c:if>
						<c:if test="${!empty user}">
							<li><a href="${pageContext.request.contextPath}/forebought">${user.name}</a></li>
							<li><a href="${pageContext.request.contextPath}/forelogout">退出</a></li>
						</c:if>
						<c:if test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/login.jsp">请登录</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
			<div class="three columns">
				<a href="#" class="header_contact">联系</a>
				<div class="top-number"><p><i class="fa fa-phone-square"></i>13928712412</p></div>
			</div>
		</div>
	</div>
</header>
</body>
</html>
