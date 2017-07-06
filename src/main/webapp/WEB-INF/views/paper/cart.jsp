
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>我的购物车 - 研究僧</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
 <link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />'>
 <link rel="stylesheet"
		href='<c:url value="/web-resources/lib/jquery/jquery-ui-1.10.4.custom.css" />' >
 <link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
 <style type="text/css">
  .shopping-cart th {
  	text-align: center;
  }
  .shopping-cart td {
  	text-align: center;
  }
  .shopping-cart button {
  	background-color: white;
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
   
  <!--  Page Title -->
  <div class="section section-breadcrumbs">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1>我的购物车</h1>
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
           <img alt="Multipurpose Twitter Bootstrap Template" src='<c:out value="resources/lib/img/gw01.png"></c:out>'>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <!-- Shopping Cart Items -->
          <table class="shopping-cart">
            	<tr><th colspan="2">商品</th><th>单价</th><th>数量</th><th>小计</th><th>操作</th></tr>
			<c:forEach items="${BUYER_CART.cartLines}" var="paper" varStatus="loopCounter">
				<tr>
					<td class="image"><a href="#"><img src='<c:out value="resources/lib/img/product1.jpg"></c:out>'></img></a></td>
					<td><c:out value="${paper.productInfo.name}"></c:out></td>
					<td><c:out value="${paper.productInfo.price}"></c:out></td>
					<td><button id="min${paper.productInfo.code}" onclick="minCart(${paper.productInfo.code})">-</button><input id="${paper.productInfo.code}" type="text" size="5" value="<c:out value="${paper.quantity}"></c:out>" onchange="sendAndUpdate(${paper.productInfo.code})" /><button onclick="addCart(${paper.productInfo.code})"> +</button></td>
					<td><c:out value="${paper.getAmount()}"></c:out></td>
					<td><a href="#" class="btn btn-xs btn-grey" onclick="delet(${paper.productInfo.code})"><i class="glyphicon glyphicon-trash"></i>删除</a></td>
				</tr>
			</c:forEach>
		    </tbody>
          </table>
        </div>
        <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">
					
		</div>
		<div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">

		</div>
        <!-- Shopping Cart Totals -->
        <div class="col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-6">
          <table class="cart-totals">
            <tr>
              <td><b>商品数</b></td>
              <td>共<strong style="color:red;"><c:out value="${BUYER_CART.quantity}"></c:out></strong>件</td>
            </tr>
            <tr class="cart-grand-total">
              <td><b>总价</b></td>
              <td><strong style="color:red;">￥<c:out value="${BUYER_CART.total}"></c:out></strong></td>
            </tr>
          </table>
          <!-- Action Buttons -->
          <div class="pull-right">
            <a href="toCart" class="btn btn-grey"><i class="glyphicon glyphicon-refresh"></i>刷新</a>
            <a href="checkLogin" class="btn"><i class="glyphicon glyphicon-shopping-cart icon-white"></i>去结算</a>
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
   
<script src='<c:url value="/web-resources/lib/bootstrap-3.3.6/js/bootstrap.min.js" />'></script>
<script src='<c:url value="/web-resources/lib/jquery/jquery-2.0.0.min.js" />'></script>
<script src='<c:url value="/web-resources/lib/jquery/jquery-ui-1.10.4.custom.js"/>'></script>
<script src='<c:url value="/web-resources/js/js-for-cartToOrder.js" />'></script>
</body>
</html>