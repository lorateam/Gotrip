<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>

<html lang="ja">

<body>
<%@include file="include/search.jsp"%>

<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="swiper-slide">

			<div class="slider">
				<img src="images/head1.jpg" alt="slider1" >
			</div>
		</div>

		<div class="swiper-slide">

			<div class="slider">
				<img src="images/head2.jpg" alt="slider1" >
			</div>
		</div>

		<div class="swiper-slide">

			<div class="slider">
				<img src="images/head3.jpg" alt="slider1" >
			</div>
		</div>

	</div>
	<div class="swiper-pagination"></div>
	<div class="swiper-scrollbar"></div>

</div>

<section id="concept">
	<div class="container">
		<div class="row">
			<div class="six columns">
				<img src="images/room2.jpg" alt="客室" />
			</div>
			<div class="six columns">
				<div class="center wow fadeInDown">
					<h3 class="concept">搜索房源</h3>
					<p>在这里，我们将提供名最优质的房源</p>
					<a class="link" href="index.jsp">>房源主页</a>
				</div>
			</div>
		</div>
	</div>
</section>

<section class="box2">
	<div class="container">
		<div class="row">
			<div class="six columns">
				<div class="center wow fadeInDown">
					<h3 class="concept">发布房源</h3>
					<p>在这里你可以当老板为客人提供最好的服务</p>
					<a class="link" href="${pageContext.request.contextPath}/admin/admin_category_list">发布房源</a>
				</div>
			</div>
			<div class="six columns">
				<img src="images/ryouri.jpg" alt="发布图片" />
			</div>
		</div>
	</div>
</section>

<section class="box3">
	<div class="container">
		<h1 class="center">故事/体验</h1>
		<div class="row"><div class="center-inner">
			<div class="four columns wow fadeInDown">
				<img src="images/menu1.jpg" alt="体验" />
				<p>带你吃遍所有的美食</p>
				<a class="link" href="#">>　美食</a>
			</div>
			<div class="four columns wow fadeInDown">
				<img src="images/menu2.jpg" alt="体验" />
				<p>带你饮尽深夜的酒</p>
				<a class="link" href="#">> 美酒</a>
			</div>
			<div class="four columns wow fadeInDown">
				<img src="images/menu3.jpg" alt="体验" />
				<p>带你读尽所有的人生故事</p>
				<a class="link" href="#">　体验</a>
			</div>
		</div></div>
	</div>
</section>


<%@include file="include/home/homePage.jsp"%>


<section id="contact">
	<div class="container">
		<div class="row">
			<div class="nine columns wow fadeInDown">
				<h1 class="center">帮助</h1>
				<p>
					如果您需要更多的帮助，请联系：
				</p>
			</div>
			<div class="two columns wow fadeInDown">
				<a href="contact.html" class="header_contact">联系</a>
				<div class="top-number"><p><i class="fa fa-phone-square"></i>130123124112</p></div>
			</div>

		</div>
	</div>
</section>

<p class="pagetop"><a href="#"><i class="fa fa-chevron-up"></i></a></p>


<footer>
	<div class="container">
		<div class="row">
		</div></div>
	<address><a href="index.jsp"><img src="images/logo.png" alt="風の家" ></a>
		<p>
			四川大学二基楼301开发室<br>
			TEL:123-456-789
		</p>
	</address>
	<small>&copy; 2018 Gotrip </small>
</footer>

  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
<script src="js/swiper.min.js"></script>
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        parallax: true,
        speed: 600,
        loop: true,
        autoplay:3000
    });
</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
    (function($) {
        $(function() {
            var $header = $('#top-head');
            // Nav Fixed
            $(window).scroll(function() {
                if ($(window).scrollTop() > 350) {
                    $header.addClass('fixed');
                } else {
                    $header.removeClass('fixed');
                }
            });
            // Nav Toggle Button
            $('#nav-toggle').click(function(){
                $header.toggleClass('open');
            });
        });
    })(jQuery);
</script>
</body>
</html>