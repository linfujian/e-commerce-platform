<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1; charset=UTF-8">
<title>研究僧</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />'>
<%-- <link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/icomoon-social.css" />'>
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/leaflet.css" />'> --%>
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/css/main.css" />'>
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
<div id="app"></div>
<script src='<c:url value="/web-resources/lib/jquery/jquery-2.0.0.min.js" />'></script>
<script src='<c:url value="/web-resources/lib/bootstrap-3.3.6/js/bootstrap.min.js" />'></script>
<script src='<c:url value="/web-resources/reactBundle/bundle.js" />'></script>
<script src='<c:url value="/web-resources/lib/jquery/jquery.cxselect.min.js" />'></script>
<script type="text/javascript">
$('#major_location').cxSelect({
	url: '/bookstore/web-resources/lib/json/paperData.min.json',
	selects: ['province', 'university', 'school', 'major'],
	nodata: 'none'
});
</script>
</body>
</html>