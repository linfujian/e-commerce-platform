<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>


<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>订单详情 -- 研究僧</title> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
		
<style type="text/css">
	 .boxCenterList{padding:7px 7px 6px 7px; background-color:#fff;}
	 h4{height:30px; line-height:30px; font-size:12px; text-align:left; background-color:#FAF6F5; border:1px solid #DEDDDB;}
	 table{border:0px;border-spacing:0px;width:100%;display: table;border-collapse: separate;}
	 td, th {display: table-cell;vertical-align: inherit;text-align: center;backgroud-color:#ffffff}
	 .yonghuxinxi td{padding:10px !important; border-bottom: #DEDDDB 1px  dashed;font-size:14px}
	 .simple-list td{padding:7px 10px;border-bottom:1px solid #eed;}
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
	  	<h1>订单详情 -- 研究僧</h1>
	  </div>
	 </div>
	</div>
   </div>
   
   <div class="section">
    <div class="container">
    
   	<div class="boxCenterList">
   		<h4>&nbsp;&nbsp;订单信息</h4>
   		<div class="blank"></div>
   		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd" class="yonghuxinxi">
   			<tbody>
   				<tr>
   				  <td width="15%" align="right">订单编号： </td>
   				  <td align="left">${orderInfo.orderNum}</td>
   				</tr>
   				<tr>
   				  <td width="15%" align="right">成交时间： </td>
   				  <td align="left">${orderInfo.orderDate}</td>
   				</tr>
   				<tr>
   				  <td align="right">付款状态：</td>
   				  <td align="left">
   				  	<c:if test="${orderInfo.payFlag == false}">未付款</c:if>
   				  	<c:if test="${orderInfo.payFlag == true}">已付款</c:if>
   				  </td>
   				</tr>
   				<tr>
   				  <td width="15%" align="right">微信交易号： </td>
   				  <td align="left">${orderInfo.transactionNum}</td>
   				</tr>
   				<tr>
   				  <td width="15%" align="right">付款时间： </td>
   				  <td align="left">${orderInfo.payTime}</td>
   				</tr>
   				<tr>
   				  <td align="right">配送状态：</td>
   				  <td align="left">
   				  	<c:if test="${orderInfo.orderStatus == 0}">未发货</c:if>
				  	<c:if test="${orderInfo.orderStatus == 1}">已发货</c:if>
				  	<c:if test="${orderInfo.orderStatus == 2}">已完成</c:if>
   				  </td>
   				</tr>
   				<tr>
   				  <td align="right">运单号:<br><i>(凭此单号可在快递公司官网查询物流详细信息)</i></td>
   				  <td align="left">${orderInfo.expressNum}</td>
   				</tr>
   			</tbody>
   		</table>
   		<div class="blank"></div>
   		<h4>&nbsp;&nbsp;商品列表</h4>
   		<div class="blank"></div>
   		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
   		  <tbody>
   		  	<tr>
   		  	  <th width="30%" align="center">商品名称</th>
   		  	  <th width="30%" align="center">商品价格</th>
   		  	  <th width="20%" align="center">购买数量</th>
   		  	  <th width="20%" align="center">小计</th>
   		  	</tr>
   		  	<c:forEach items="${orderInfo.details}" var="detail">
   		  	  <tr>
   		  	  	<td>${detail.productName}</td>
   		  	  	<td>￥${detail.price}</td>
   		  	  	<td>${detail.quantity}</td>
   		  	  	<td>￥${detail.amount}</td>
   		  	  </tr>
   		  	</c:forEach>
   		  </tbody>
   		</table>
   		<div class="blank"></div>
   		<h4>&nbsp;&nbsp;费用总计</h4>
   		<div class="blank"></div>
   		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
   		 <tbody>
   		  <tr>
   		  	<td style="text-align:right;">商品总价：￥${orderInfo.amount - orderInfo.expressCost} + 配送费用：￥${orderInfo.expressCost}</td>
   		  </tr>
   		  <tr>
   		  	<td style="text-align:right;">应付款金额：￥${orderInfo.amount}</td>
   		  </tr>
   		 </tbody>
   		</table>
   		<div class="blank"></div>
   		<h4>&nbsp;&nbsp;物流信息</h4>
   		<div class="blank"></div>
   		<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd" class="simple-list">
   		  <tbody>
   		  	<tr>
   		  	  <td>收货地址：</td>
   		  	  <td style="text-align:left;">${orderInfo.customerName},&nbsp;${orderInfo.customerPhone},&nbsp; ${orderInfo.customerAddress}&nbsp;${orderInfo.customerAddressDetail}</td>
   		  	</tr>
   		  	<tr>
   		  	  <td>物流公司：</td>
   		  	  <td style="text-align:left;">
   		  	  	<c:if test="${orderInfo.expressName eq 'sf'}">顺丰速运</c:if>
   		  	  	<c:if test="${orderInfo.expressName eq 'yd'}">韵达速运</c:if>
   		  	  	<c:if test="${orderInfo.expressName eq 'st'}">申通快递</c:if>
   		  	  	<c:if test="${orderInfo.expressName eq 'yt'}">圆通快递</c:if>
   		  	  	<c:if test="${orderInfo.expressName eq 'zt'}">中通快递</c:if>
   		  	  	<c:if test="${orderInfo.expressName eq 'ht'}">汇通快递</c:if>
   		  	  </td>
   		  	</tr>
   		  	<tr>
   		  	  <td>运单号：</td>
   		  	  <td style="text-align:left;">${orderInfo.expressNum}</td>
   		  	</tr>
   		  	<tr>
   		  	  <td>买家留言：</td>
   		  	  <td style="text-align:left;">${orderInfo.orderNote}</td>
   		  	</tr>
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