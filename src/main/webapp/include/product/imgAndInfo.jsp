<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<script>
 
$(function(){
    var stock = ${p.stock};
    $(".productNumberSetting").keyup(function(){
        var num= $(".productNumberSetting").val();
        num = parseInt(num);
        if(isNaN(num))
            num= 1;
        if(num<=0)
            num = 1;
        if(num>stock)
            num = stock;
        $(".productNumberSetting").val(num);
    });
     
    $(".increaseNumber").click(function(){
        var num= $(".productNumberSetting").val();
        num++;
        if(num>stock)
            num = stock;
        $(".productNumberSetting").val(num);
    });
    $(".decreaseNumber").click(function(){
        var num= $(".productNumberSetting").val();
        --num;
        if(num<=0)
            num=1;
        $(".productNumberSetting").val(num);
    });
     
    $(".addCartButton").removeAttr("disabled");
    $(".addCartLink").click(function(){
        var page = "forecheckLogin";
        $.get(
                page,
                function(result){
                    console.log("nooooooooooooo");
                    console.log(typeof result);
                    if(result != "0"){
                        console.log("yesssssssssss");
                        var pid = ${p.id};
                        var num= $(".productNumberSetting").val();
                        var addCartpage = "foreaddCart";
                        $.get(
                                addCartpage,
                                {"pid":pid,"num":num},
                                function(result){
                                    if(result != "0"){
                                        $(".addCartButton").html("已加入购物车");
                                        $(".addCartButton").attr("disabled","disabled");
                                        $(".addCartButton").css("background-color","lightgray")
                                        $(".addCartButton").css("border-color","lightgray")
                                        $(".addCartButton").css("color","black")

                                    }
                                    else{

                                    }
                                }
                        );
                    }
                    else{
                        $("#loginModal").modal('show');
                    }
                }
        );
        return false;
    });
    $(".buyLink").click(function(){
        var page = "forecheckLogin";
        var num= $(".productNumberSetting").val();
        num = parseInt(num);
        if(num>stock){
            alert("剩余房间不足，无法预定！");
            return false;
        }
        if(num<1){
            alert("非法数值，请输入正整数！");
            return false;
        }
        $.get(
                page,
                function(result){
                    if(result != "0"){
                        var num = $(".productNumberSetting").val();
                        location.href= $(".buyLink").attr("href")+"&number="+num;
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
     
    $("button.loginSubmitButton").click(function(){
        var name = $("#name").val();
        var password = $("#password").val();
         
        if(0==name.length||0==password.length){
            $("span.errorMessage").html("请输入账号密码");
            $("div.loginErrorMessageDiv").show();           
            return false;
        }
         
        var page = "${pageContext.request.contextPath}/foreloginAjax";
        $.get(
                page,
                {"name":name,"password":password},
                function(result){
                    if("success"==result){
                        location.reload();
                    }
                    else{
                        $("span.errorMessage").html("账号密码错误");
                        $("div.loginErrorMessageDiv").show();                       
                    }
                }
        );          
         
        return true;
    });
     
    $("img.smallImage").mouseenter(function(){
        var bigImageURL = $(this).attr("bigImageURL");
        $("img.bigImg").attr("src",bigImageURL);
    });
     
    $("img.bigImg").load(
        function(){
            $("img.smallImage").each(function(){
                var bigImageURL = $(this).attr("bigImageURL");
                img = new Image();
                img.src = bigImageURL;
                 
                img.onload = function(){
                    $("div.img4load").append($(img));
                };
            });     
        }
    );
});
 
</script>
 
<div class="imgAndInfo">
 
    <div class="imgInimgAndInfo">
        <img src="img/productSingle/${p.firstProductImage.id}.jpg" class="bigImg">
        <div class="smallImageDiv">
            <c:forEach items="${p.productSingleImages}" var="pi">
                <img src="img/productSingle_small/${pi.id}.jpg" bigImageURL="img/productSingle/${pi.id}.jpg" class="smallImage">
            </c:forEach>
        </div>
        <div class="img4load hidden" ></div>
    </div>
     
    <div class="infoInimgAndInfo">
         
        <div class="productTitle">
            ${p.name}
        </div>
        <div class="productSubTitle">
            ${p.subTitle} 
        </div>
         
        <div class="productPrice">


                <div class="originalDiv">
                    <span class="originalPriceDesc">价格</span>
                    <span class="originalPriceYuan">¥</span>
                    <span class="originalPrice">
                     
                        <fmt:formatNumber type="number" value="${p.orignalPrice}" minFractionDigits="2"/>                 
                    </span>
                </div>
                <div class="promotionDiv">
                    <span class="promotionPriceDesc">折扣价 </span>
                    <span class="promotionPriceYuan">¥</span>
                    <span class="promotionPrice">
                        <fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
                    </span>               
                </div>
            </div>
        </div>
        <div class="productSaleAndReviewNumber">
            <div>出租量 <span class="redColor boldWord"> ${p.saleCount }</span></div>
            <div>累计评价 <span class="redColor boldWord"> ${p.reviewCount}</span></div>    
        </div>
        <div class="productNumber">
            <span>预定房间</span>
            <span>
                <span class="productNumberSettingSpan">
                <input class="productNumberSetting" type="text" value="1">
                </span>
                <span class="arrow">
                    <a href="#nowhere" class="increaseNumber">
                    <span class="updown">
                            <img src="img/site/increase.png">
                    </span>
                    </a>
                     
                    <span class="updownMiddle"> </span>
                    <a href="#nowhere"  class="decreaseNumber">
                    <span class="updown">
                            <img src="img/site/decrease.png">
                    </span>
                    </a>
                     
                </span>
                     
            间</span>
            <span>剩余房间${p.stock}间</span>
        </div>
        <div class="serviceCommitment">
            <span class="serviceCommitmentDesc">我们将提供最好的服务</span>
            <span class="serviceCommitmentLink">
                <a href="#nowhere">舒适</a>
                <a href="#nowhere">健康</a>
                <a href="#nowhere">美好体验</a>
            </span>
        </div>    
         
        <div class="buyDiv">
            <a class="buyLink" href="${pageContext.request.contextPath}/forebuyone?pid=${p.id}"><button class="buyButton">预定房间</button></a>
        </div>
    </div>
     
    <div style="clear:both"></div>
     
</div>