<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>

<html lang="ja">

<body>
<div class="swiper-container">
    <div class="swiper-wrapper">

        <div class="swiper-slide">

            <div class="slider">
                <img src="images/slider1.jpg" alt="slider1" >
            </div>
        </div>

        <div class="swiper-slide">

            <div class="slider">
                <img src="images/slider2.jpg" alt="slider1" >
            </div>
        </div>

        <div class="swiper-slide">

            <div class="slider">
                <img src="images/slider3.jpg" alt="slider1" >
            </div>
        </div>

    </div>

    <div class="swiper-pagination"></div>
    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>
    <div class="swiper-scrollbar"></div>

</div>

<section id="about">
    <div class="container">
        <div class="row">
            <div class="six columns">
                <div class="center wow fadeInDown">
                    <h1>GoTrip 故事</h1>
                    <h2>带你领略全新的人生</h2>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="feature">
    <div class="container">
        <div class="row">
            <div class="four columns">
                <div class="waku">
                    <img src="images/menu1.jpg" alt="メニュー１" />
                </div>
                <h3>GoTtrip夏日狂欢</h3>
                <p>
                    夏日的味道。
                </p>
            </div>
            <div class="four columns">
                <div class="waku">
                    <img src="images/menu2.jpg" alt="メニュー2" />
                </div>
                <h3>GoTrip音乐会</h3>
                <p>
                    聆听天籁之音
                </p>
            </div>
            <div class="four columns">
                <div class="waku">
                    <img src="images/menu3.jpg" alt="メニュー3" />
                </div>
                <h3>GoTrip美食</h3>
                <p>
                    带你领略美食
                </p>
            </div>
        </div>
    </div>
</section>

<%@include file="include/home/storyPage.jsp"%>

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
                <a href="#" class="header_contact">联系</a>
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
    <address><a href="${pageContext.request.contextPath}/index.jsp"><img src="images/logo.png" alt="風の家" ></a>
        <p>
            四川大学二基楼301开发室<br>
            TEL:123-456-789
        </p>
    </address>
    <small>&copy; 2018 Gotrip </small>
</footer>


<script src="js/wow.min.js"></script>
<script>
    new WOW({
        mobile: false
    }).init();
</script>
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
        autoplay:3000,
        effect: 'fade'
    });
</script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript">
    $(function() {
        var nav = $('.paradeiser');

        var navTop = nav.offset().top;

        $(window).scroll(function () {
            var winTop = $(this).scrollTop();

            if (winTop >= navTop) {
                nav.addClass('fixed')
            } else if (winTop <= navTop) {
                nav.removeClass('fixed')
            }
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        var pagetop = $('.pagetop');
        $(window).scroll(function () {
            if ($(this).scrollTop() > 700) {
                pagetop.fadeIn();
            } else {
                pagetop.fadeOut();
            }
        });
        pagetop.click(function () {
            $('body, html').animate({ scrollTop: 0 }, 500);
            return false;
        });
    });
</script>
</body>
</html>
