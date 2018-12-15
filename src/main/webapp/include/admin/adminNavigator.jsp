<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<div class="navitagorDiv">
	<meta charset="utf-8">
	<title>GoTrip</title>
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Mobile Specific Metas
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- FONT
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">

	<!-- CSS
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skeleton.css">

	<!-- Favicon
    –––––––––––––––––––––––––––––––––––––––––––––––––– -->
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
	</head>
	<body>
	<header id="top-head">
		<div class="container">
			<div class="row">
				<div class="three columns">
					<div id="mobile-head">
						<h1 id="title"><a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/images/logo.png" alt="GoTrip" ></a></h1>
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
							<li><a href="${pageContext.request.contextPath}/admin/admin_category_list">房源管理</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/admin_user_list">用户管理</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/admin_order_list">订单管理</a></li>
							<li><a href="${pageContext.request.contextPath}/register.jsp">免费注册</a></li>
						</ul>
					</nav>
				</div>
				<div class="three columns">
					<a href="#" class="header_contact">联系</a>
					<div class="top-number"><p><i class="fa fa-phone-square"></i>12346</p></div>
				</div>
			</div>
		</div>
	</header>
</div>