<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>订单信息填写 -- 研究僧</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
		
<style type="text/css">
	select {
	  boxSizing: border-box;
      height: 32px;
      padding: 0 0.5em;
      border: 1px solid #888;
      border-radius: 3px;
      background-color: #fff;
      background-repeat: no-repeat;
      background-position: right center;
      font: 12px/1.5 Tahoma,Arial,sans-serif;
	}
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
       <h1>研究僧-填写并核对订单信息</h1>
      </div>
     </div>
    </div>
   </div>
   
   <div class="section">
    <div class="container">
     <div class="row">
      <div class="col-sm-5">
       <div class="basic-login">
         <form:form method="POST" modelAttribute="customerForm" class="form-signin">
		
			<spring:bind path="address">
				<div class="form-group ${status.error? 'has-error': ''}" id="location_select">
					<label for="order-location"><em>*</em>所在地区</label><br />
					<select class="province" data-first-title="选择省"></select>
                  	<select class="city" data-first-title="选择市" onchange="convertLocation()"></select>
                    <select class="area" data-first-title="选择地区" onchange="convertLocation()"></select><br />
					<form:hidden id="location" path="address"/>
					<form:errors path="address"></form:errors>
				</div>
			</spring:bind>
			
			<spring:bind path="name">
				<div class="form-group ${status.error? 'has-error': ''}">
					<label for="order-name"><em>*</em>收货人</label>
					<form:input path="name" type="text" class="form-control" placeholder="请正确填写收货人姓名"/>
					<form:errors path="name"></form:errors>
				</div>
			</spring:bind>
			
			<spring:bind path="addressDetail">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="order-locationDetail"><em>*</em>详细地址</label>
	                <form:input type="text" path="addressDetail" class="form-control"
	                            placeholder="请填写收货详细地址"></form:input>
	                <form:errors path="addressDetail"></form:errors>
	            </div>
			</spring:bind>
			
			<spring:bind path="phone">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="order-phone"><em>*</em>手机号码</label>
	                <form:input type="text" path="phone" class="form-control"
	                            placeholder="请输入正确的电话号码"></form:input>
	                <form:errors path="phone"></form:errors>
	            </div>
			</spring:bind>
			
			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label for="order-email"><em>*</em>邮箱地址</label>
	                <form:input type="text" path="email" class="form-control"
	                            placeholder="如需联系，通过电话或者邮箱来联系您"></form:input>
	                <form:errors path="email"></form:errors>
	            </div>
			</spring:bind>
			
			<div class="form-group">
				<button class="btn pull-right" type="submit">保存收货人信息</button>
				<div class="clearfix"></div>
			</div>
		</form:form>
       </div>
      </div>
      
      <div class="col-sm-6 col-sm-offset-1 social-login">
       <p>您可以使用微信或者QQ注册</p>
       <div class="social-login-buttons">
        <a href="#" class="btn-facebook-login">使用微信</a>
        <a href="#" class="btn-twitter-login">使用QQ</a>
       </div>
       <div class="clearfix"></div>
       	<div class="not-member">
		 <p>已有账号？ <a href="login">请登录</a></p>
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
<script src='<c:url value="/web-resources/lib/jquery/jquery.cxselect.min.js" />'></script>
<script type="text/javascript">

$(document).ready(function() {
	$('#location').val('');
});

$('#location_select').cxSelect({
	url: "/bookstore/web-resources/lib/json/cityData.min.json",
	selects: ['province', 'city', 'area'],
	nodata: 'none'
});

function convertLocation() {
	if(($('.province').val() === '北京市' || $('.province').val() === '上海市' 
			|| $('.province').val() === '重庆市' || $('.province').val() === '天津市'
			|| $('.province').val() === '香港特别行政区' || $('.province').val() === '澳门特别行政区') && $('.city').val() != null) {
		var location = $('.province').val() + $('.city').val();
		$('#location').val(location);
	} else if($('.area').val() != null) {
		
		var location = $('.province').val() + $('.city').val() + $('.area').val();
		$('#location').val(location);
	}
}
</script>
</body>
</html>