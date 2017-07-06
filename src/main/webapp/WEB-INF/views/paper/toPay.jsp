<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>订单提交成功 -- 研究僧</title> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
		
<style type="text/css">

	.tjbox_txt {width:930px; margin:0 auto; font-size:20px; color:#ff6000; text-align:center; margin-top:20px;} 
	.tjbox_txt01{ width:930px; margin:0 auto; font-size:14px; color:#ff6000; text-align:center; margin-top:15px;}
	.rmspbox{ width:888px; margin-top:0 auto; margin:0 auto; margin-top:20px;border:1px solid #dedede; height:35px; border-top:2px solid #ff6000; border-bottom:2px solid #dedede; line-height:35px; padding-left:15px;} 
	.zhifubbox{width:888px; margin:0 auto; border:1px solid #cccccc; border-top:none; padding:15px 20px;}
	.zhifubbox_01{ text-align:center; line-height:35px;}
	.zhifubbox_02{ border-top:1px dashed #cccccc; margin-top:20px; overflow:hidden; padding-top:15px;}
	.zhifubbox_02 a{ float:right; width:171px; height:40px; background:url(../images/index/jiesuan.png) no-repeat; font-size:18px; color:#ffffff; text-align:center; line-height:40px;}
	.zhifubbox_01 span{ color:#0d89df;}
	
</style>

</head>
<body>
     <div class="mainmenu-wrapper">
      <div class="container">
       <div class="menuextras">
         <div class="extras">
           <ul>
           	<c:choose>
           	 <c:when test="${sessionScope.user != null}">
           	  <li><a href="#">欢迎您，<c:out value="${sessionScope.user}"></c:out></a></li>
           	  <li><a href="logout">退出</a></li>
           	 </c:when>
           	 <c:otherwise>
          	  <li><a href="login">僧友登陆</a></li>
             <li><a href="registration">僧友注册</a></li>
           	 </c:otherwise>
           	</c:choose>
             <li class="shopping-cart-items"><i class="glyphicon glyphicon-shopping-cart icon-white"></i><a href="toCart"><b>购物车</b></a></li>
           </ul>
         </div>
       </div>
       <nav id="mainmenu" class="mainmenu">
         <ul>
           <li class="logo-wrapper"><a href="findbook"><img src="resources/lib/img/mPurpose-logo.png" alt="Multipurpose Twitter Bootstrap Template"></img></a></li>
           <li>
             <a href="findbook">主页</a>
           </li>
           <li>
             <a href="toYou">致广大考研童鞋们的一封信</a>
           </li>
           <li>
             <a href="toCart">购物车</a>
           </li>
           <li>
             <a href="toOrder">我的真题</a>
           </li>
         </ul>
       </nav>
     </div>
   </div>
   
   <!-- Page Title -->
   <div class="section section-breadcrumbs">
	<div class="container">
	 <div class="row">
	  <div class="col-md-12">
	  	<h1>提交订单成功</h1>
	  </div>
	 </div>
	</div>
   </div>
   
   <div class="section">
    <div class="container">
    
    <div class="row">
        <div class="col-md-12">
          <!-- Action-Button -->
          <div class="pull-right">
           <img alt="Multipurpose Twitter Bootstrap Template" src='<c:out value="resources/lib/img/gw03.png"></c:out>'>
          </div>
        </div>
     </div>
    
     <div class="row">
     	<div class="tjbox_txt">
     	  	恭喜，您的订单已经提交成功！
     	</div>
     </div>
     
     <div class="row">
     	<div class="tjbox_txt01">
     	  订单号：<a href="toOrder" style="color: #06C;">${lastOrderedCart.orderNum}</a>，已经提交成功，请尽快完成支付
     	</div>
     </div>
     
     <div class="rmspbox" style=" text-align:center;">
     	<img src="resources/lib/img/hanh.png"> &nbsp;&nbsp;订单将在支付成功后发货，未付款订单将在1小时内取消
     </div>
     
     <div class="zhifubbox">
     	<div class="zhifubbox_01">
     		<p>支付方式：微信支付</p>
     		<p>应付金额：￥${lastOrderedCart.fareTotal}元</p>
     	</div>
     	<div class="zhifubbox_02">
     		<div style="text-align:center">
     			<img src="http://www.samree.com/api/wxpayapi/util/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3Dj2MNYJz"><br><br>
     			二维码有效时长为2小时，请尽快支付
     		</div>
     	</div>
     </div>
     
    </div>
   </div>
   
   <!-- Footer -->
    <div class="footer">
    <div class="row">
      <div class="col-footer col-md-3 col-xs-6">
        <h3>服务宗旨</h3>
        <ul class="no-list-style footer-navigate-section">
          <li>真实</li>
          <li>诚信</li>
          <li>沟通</li>
        </ul>
      </div>

      <div class="col-footer col-md-3 col-xs-6">
        <h3>购物指南</h3>
        <ul class="no-list-style footer-navigate-section">
          <li><a href="">购物流程</a></li>
          <li><a href="">售后服务</a></li>
          <li><a href="">联系客服</a></li>
          <li><a href="">常见问题</a></li>
        </ul>
      </div>

      <div class="col-footer col-md-3 col-xs-6">
        <h3>支付方式</h3>
        <ul class="no-list-style footer-navigate-section">
          <li><a href="">货到付款</a></li>
          <li><a href="">在线支付</a></li>
          <li><a href="">邮局汇款</a></li>
        </ul>
      </div>

      <div class="col-footer col-md-3 col-xs-6">
        <h3>联系方式</h3>
        <p class="contanct-us-details">
          <b>地址：</b>上海市闵行区莘庄镇xx号<br/>
          <b>电话：</b>18516580543<br/>
          <b>Email:</b><a href="mailto:linfujian1999@sina.com">linfujian1999@sina.com</a>
        </p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="footer-copyright">
          &copy; 2017 yanjiuseng. All rights reserved.
        </div>
      </div>
    </div>
  </div>

<script type="text/javascript" src='<c:url value="/web-resources/lib/jquery/jquery-2.0.0.min.js"/>'></script>
<script type="text/javascript"
		src='<c:url value="/web-resources/lib/bootstrap-3.3.6/js/bootstrap.min.js"/>'></script>
</body>
</html>