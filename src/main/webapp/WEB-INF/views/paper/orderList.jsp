<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>我的订单 -- 研究僧</title> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
		
<style type="text/css">
	 .boxCenterList{padding:7px 7px 6px 7px; background-color:#fff;}
	 h4{height:30px; line-height:30px; font-size:12px; text-align:left; background-color:#FAF6F5; border:1px solid #DEDDDB;}
	 table{border:0px;border-spacing:0px;width:100%}
	 td, th {display: table-cell;vertical-align: inherit;text-align: center;backgroud-color:#ffffff}
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
	  	<h1>我的真题 -- 研究僧</h1>
	  </div>
	 </div>
	</div>
   </div>
   
   <div class="section">
    <div class="container">
    
   	<div class="boxCenterList">
   		<style>.hell td{padding:8px}</style>
   		<h4>&nbsp;&nbsp;我的订单</h4>
   		<div class="blank"></div>
   		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd" class="hell">
   			<tbody>
   				<tr>
   				  <td>订单号</td>
   				  <td>下单时间</td>
   				  <td>订单总金额</td>
   				  <td>付款状态</td>
   				  <td>订单状态</td>
   				</tr>
   				<c:forEach items="${orderList}" var="order">
   				  <tr>
   				  	<td><a href="toOrderDetail?order_id=${order.id}">${order.orderNum}</a></td>
   				  	<td>${order.orderDate}</td>
   				  	<td>${order.amount}</td>
   				  	<td>
   				  	  <c:if test="${order.payFlag == false}">未付款</c:if>
   				  	  <c:if test="${order.payFlag == true}">已付款</c:if>
   				  	</td>
   				  	<td>
   				  	  <c:if test="${order.orderStatus == 0}">未发货</c:if>
   				  	  <c:if test="${order.orderStatus == 1}">已发货</c:if>
   				  	  <c:if test="${order.orderStatus == 2}">已完成</c:if>
   				  	</td>
   				  </tr>
   				</c:forEach>
   			</tbody>
   		</table>
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