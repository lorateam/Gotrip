<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.storyCategorycount}">
    <c:set var="storyCategorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="storyCategorycount" scope="page" value="${param.storyCategorycount}"/>
</c:if>

</head>
<style>
    div.storyPageCategoryProducts{
        background-color: #FFFFFF;
        padding: 50px 10px 50px 10px;
        margin: 10px auto;
        max-width: 1519.5px;
    }
    div.storyProductItem{
        width: 283px;
        height: 427px;
        border: 1px solid white;
        background-color: white;
        margin: 8px 4px;
        float: left;
        cursor: pointer;
    }
    div.storyProductItem span.storyProductItemDesc{
        font-size: 12px;
        color: #666666;
        display: block;
        padding: 16px;
    }
    div.storyProductItem span.storyProductPrice{
        font-size: 16px;
        color: #FF003A;
        display: block;
        padding-left: 16px;
        margin-top: -10px;
    }
    div.storyProductItem img{
        width: 280.5px;
        height: 285px;
    }
    div.storyProductItem  img:hover{
        opacity: 0.7;
        filter: alpha(opacity = 70);
    }
    div.eachStorypageCategoryProducts{
        margin: 0px 0px 40px 0px;
    }
    a.storyProductItemDescLink{
        display: inline-block;
        height: 66px;
        text-decoration:none;
    }
    span.storyCategoryTitle{
        font-size: 26px;
        /*margin-left: 30px;*/
        text-align:center;
        color: #0a1664;
        font-weight: bold;
    }
    div.left-mark1{
        display: inline-block;
        height: 20px;
        vertical-align: top;
        width: 5px;
        background-color: #19C8A9;
    }
    img.endpng1{
        display: block;
        width: 82px;
        margin: 0 auto;
    }
</style>

<div class="storyPageCategoryProducts">
    <c:forEach items="${cs}" var="c" varStatus="stc">
        <c:if test="${stc.count<=storyCategorycount}">
            <div class="eachStorypageCategoryProducts">
                <div class="left-mark1"></div>
                <span class="storyCategoryTitle">${c.name}</span>
                <br>
                <c:forEach items="${c.products}" var="p" varStatus="st">
                    <c:if test="${st.count<=5}">
                        <div class="storyProductItem" >
                            <a href="foreproduct?pid=${p.id}"><img width="100px" src="img/productSingle_middle/${p.firstProductImage.id}.jpg"></a>
                            <a class="storyProductItemDescLink" href="foreproduct?pid=${p.id}">
								<span class="storyProductItemDesc">[强烈推荐]
								${fn:substring(p.name, 0, 20)}
								</span>
                            </a>
                            <span class="storyProductPrice">
								<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
							</span>
                        </div>
                    </c:if>
                </c:forEach>
                <div style="clear:both"></div>
            </div>
        </c:if>
    </c:forEach>




</div>

