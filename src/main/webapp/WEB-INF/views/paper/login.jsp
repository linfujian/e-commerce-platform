<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>欢迎登陆</title> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
</head>
<body>
     <div class="mainmenu-wrapper">
      <div class="container">
       <div class="menuextras">
         <div class="extras">
           <ul>
           	 <li><a href="login">僧友登陆</a></li>
             <li><a href="registration">僧友注册</a></li>
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
	  	<h1>研究僧-账户登陆</h1>
	  </div>
	 </div>
	</div>
   </div>
   
   <div class="section">
    <div class="container">
     <div class="row">
      <div class="col-sm-5">
       <div class="basic-login">
        <form method="POST" action="request_for_login" class="form-signin">
        <span>${message}</span>
		<div class="form-group ${error != null ? 'has-error' : ''}">
			<label for="login-username"><i class="icon-user"></i> <b>用户名</b></label>
			<input name="userName" type="text" class="form-control" placeholder="用户名"
					autofocus="true" />
		</div>
		<div class="form-group ${error != null ? 'has-error' : ''}">
			<label for="login-password"><i class="icon-lock"></i> <b>密码</b></label>
			<input name="password" type="password" class="form-control" placeholder="密码" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</div>
		<span>${error}</span>
		<div class="form-group">	
			<button class="btn pull-right" type="submit">登录</button>
			<div class="clearfix"></div>
		</div>
		</form>
       </div>
      </div>
      <div class="col-sm-7 social-login">
       	<p>使用微信或者QQ登陆</p>
       	<div class="social-login-buttons">
       	 <a href="#" class="btn-facebook-login">微信</a>
       	 <a href="#" class="btn-twitter-login">QQ</a>
       	</div>
       	<div class="clearfix"></div>
       	<div class="not-member">
		 <p>还没注册？ <a href="registration">立即注册</a></p>
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