<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>订单结算页 -- 研究僧</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
<style type="text/css">
  .shopping-cart th {
  	text-align: center;
  }
  .shopping-cart td {
  	text-align: center;
  }  
</style>

<% response.setHeader("cache-control", "no-store"); %>

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
       <h1>结算页-请核对订单信息</h1>
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
           <img alt="Multipurpose Twitter Bootstrap Template" src='<c:out value="resources/lib/img/gw02.png"></c:out>'>
          </div>
        </div>
     </div>
     
     <div class="row basic-login">
      <div class="col-sm-5">
         <form:form class="form-signin">
         
				<div class="form-group" id="location_select">
					<label for="order-location"><em>*</em>收货地址</label><br />
					&nbsp;${myCart.customerInfo.address} &nbsp;&nbsp;${myCart.customerInfo.addressDetail}
				</div>
			
				<div class="form-group">
					<label for="order-name"><em>*</em>收货人</label><br />
					&nbsp;${myCart.customerInfo.name}
				</div>
			
				<div class="form-group">
					<label for="order-phone"><em>*</em>手机号码</label><br />
	                &nbsp;${myCart.customerInfo.phone}
	            </div>
			
				<div class="form-group">
					<label for="order-email"><em>*</em>邮箱地址</label><br />
	                &nbsp;${myCart.customerInfo.email}
	            </div>

			
			<div class="form-group">
				<a href="shoppingCartCustomer" class="btn btn-grey"><i class="glyphicon glyphicon-pencil"></i>修改收货人信息</a>
				<div class="clearfix"></div>
			</div>
		</form:form>
      </div>
      
      <div class="col-sm-6">
      <label>送货清单</label>
      <!-- Shopping Cart Items -->
       <table class="shopping-cart">
           	<tr><th colspan="2">商品</th><th>单价</th><th>数量</th></tr>
		<c:forEach items="${myCart.cartLines}" var="paper" varStatus="loopCounter">
			<tr>
				<td class="image"><a href="#"><img src='<c:out value="resources/lib/img/product1.jpg"></c:out>'></img></a></td>
				<td><c:out value="${paper.productInfo.name}"></c:out></td>
				<td><c:out value="${paper.productInfo.price}"></c:out></td>
				<td><c:out value="${paper.quantity}"></c:out></td>
			</tr>
		</c:forEach>
	    </tbody>
       </table>
      </div>
     </div>
     
     <div class="row">
     	<!-- Note -->
       <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">
         <div class="cart-promo-code">
           <h6><i class="glyphicon glyphicon-pencil"></i>备注</h6>
           <div class="input-group">
             <input class="form-control input-sm" style="width: 350px;" id="appendedNote" type="text" value="" placeholder="不要超过256个字" maxlength="256" />
             
           </div>
         </div>
       </div>
       
       <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">
         <div class="cart-shippment-options">
           <h6><i class="glyphicon glyphicon-plane"></i>运送方式</h6>
           <div class="input-append">
             <select class="form-control input-sm" id="selectType" onchange="calculateFare()">
             	<option selected value="sf">顺丰快递(2-3天)</option>
             	<option value="yd">韵达快递(4-5天)</option>
             	<option value="st">申通快递(5-6天)</option>
             	<option value="yt">圆通快递(5-7天)</option>
             	<option value="zt">中通快递(6-8天)</option>
             	<option value="ht">汇通快递(7-10天)</option>
             </select>
           </div>
         </div>
       </div>
       
       <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">
         <table class="cart-totals">
           <tr>
             <td>商品数</td>
             <td>共<strong>${myCart.quantity}</strong>件</td>
           </tr>
           <tr>
             <td>总商品金额</td>
             <td><strong>￥<span id="totalOfCart">${myCart.total}</span></strong></td>
           </tr>
           <tr>
           	<td>运费</td>
           	<td><strong>￥<span id="fare"></span></strong></td>
           </tr>
         </table>
       </div>
       
     </div>
     
     <div class="basic-login">
     <div class="row">
     	<div class="pull-right">
     	  <label>应付总额： <strong style="color:red;">￥<span id="totalToPay"></span></strong></label>
     	</div>
     </div>
     <div class="row">
     	<div class="pull-right">
     	  寄送至： ${myCart.customerInfo.address} ${myCart.customerInfo.addressDetail} &nbsp;&nbsp;&nbsp;&nbsp;收货人：${myCart.customerInfo.name} ${myCart.customerInfo.phone}
     	</div>
     </div>
     <div class="row"><div class="pull-right">---------------------------------------------------------------------</div></div>
     <div class="row">
     	<div class="pull-right">
     	 <button class="btn btn-red" type="button" style="width:150px;" onclick="sendInfo()">提交订单</button>
     	</div>
     </div>
     </div>
 	 <!-- form to submit with note and fare -->
 	 <div>
 	   <form method="post" id="waitToSend">
 	   	<input type="hidden" name="note" id="noteToSend" value="" />
 	   	<input type="hidden" name="fare" id="fareToSend" value="" />
 	   </form>
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
		
<script type="text/javascript">

	$(document).ready(function() {
		calculateFare();
	});
	
	function calculateFare() {
		var type = $('#selectType').val();
		
		if(type == 'sf') {
			$('#fare').text(12.0);
			var total = parseFloat($('#totalOfCart').text()) + 12.0;
			$('#totalToPay').text(total);
		}
		if(type == 'yd') {
			$('#fare').text(10.0);
			var total = parseFloat($('#totalOfCart').text()) + 10.0;
			$('#totalToPay').text(total);
		}
		if(type == 'st') {
			$('#fare').text(8.0);
			var total = parseFloat($('#totalOfCart').text()) + 8.0;
			$('#totalToPay').text(total);
		}
		if(type == 'yt') {
			$('#fare').text(7.0);
			var total = parseFloat($('#totalOfCart').text()) + 7.0;
			$('#totalToPay').text(total);
		}
		if(type == 'zt') {
			$('#fare').text(5.0);
			var total = parseFloat($('#totalOfCart').text()) + 5.0;
			$('#totalToPay').text(total);
		}
		if(type == 'ht') {
			$('#fare').text(4.0);
			var total = parseFloat($('#totalOfCart').text()) + 4.0;
			$('#totalToPay').text(total);
		}
	}
	
	function sendInfo() {
		
		var note = $('#appendedNote').val();
		var fare = $('#selectType').val();
		$('#noteToSend').val(note);
		$('#fareToSend').val(fare);
		$('form#waitToSend').submit();
		
	}
</script>
</body>
</html>