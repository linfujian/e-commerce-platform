<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>研究僧</title>
 <link rel="stylesheet"
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />'>
</head>
<body>
	<div id="major_location">
		<p>所选专业：
			<select class="province" data-first-title="选择省份"></select>
			<select class="university" data-required="true"></select>
			<select class="school" data-required="true"></select>
			<select class="major" data-required="true"></select>
		</p>
		<button id="query">查询</button>
	</div>
		
	<div id="cart" class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" onclick="clickMenuUpdate()">
			我的购物车
			<span class="caret"></span>
		</button>
	</div>
	
	<input id="paper_id" type="hidden" />
	<input id="paper_name" type="hidden" />
	<div id="query_result">
	</div>
	<div id="success_addcart">
	</div>
	
<script src='<c:url value="/web-resources/lib/jquery/jquery-2.0.0.min.js" />'></script>
<script src='<c:url value="/web-resources/lib/bootstrap-3.3.6/js/bootstrap.min.js" />'></script>
<script src='<c:url value="/web-resources/lib/jquery/jquery.cxselect.min.js" />'></script>
<script>
	$(document).ready(function() {
		clickMenuUpdate();
		
	});
	
	function clickMenuUpdate() {
		$.get('showcart', function(msg) {
			updatacart(msg);
		});
	}


	$('#major_location').cxSelect({
		url: '/bookstore/web-resources/lib/json/paperData.min.json',
		selects: ['province', 'university', 'school', 'major'],
		nodata: 'none'
	});
	
	$('#query').click(function(){
		$.ajax({
			type:'GET',
			DataType:'json',
			url: 'query',
			data:{
				'province': $('.province').val(),
				'university': $('.university').val(),
				'school': $('.school').val(),
				'major': $('.major').val()
			},
			success: function(msg) {
				successCallback(msg);
			},
			
		});
	});
	
	$(document).on('click','#add', function() {
		$('#num').val(parseInt($('#num').val())+1);
		if(parseInt($('#num').val())!=1){
			$('#min').attr('disabled',false);
		}
	});
	$(document).on('click','#min', function() {
		if(parseInt($('#num').val())==1){
			$('#min').attr('disabled',true);
		} else {
			$('#num').val(parseInt($('#num').val())-1);
		}
	});
	
	function addCart() {
		/* $.get('addcart/'+ $('#paper_id').val() + "/" + $('#num').val(), function(msg){
			//updatacart(msg);
			
			showSuccessAddCart();
		}); */
		window.open('addcart/'+ $('#paper_id').val() + "/" + $('#num').val());
	}
	
	function updatacart(msg){
		$('.dropdown-menu').remove();
		var content = "<ul class=\"dropdown-menu\" aria-labelledby=\"dropdownMenu1\">";
		$.each(msg.cartLines, function(index,item) {
			content += "<li>" + "<a ref=\"#\" onclick=\"showPaperDetail(" + item.productInfo.code + ")\">" + item.productInfo.name + "| ￥" + item.productInfo.price + "×" + item.quantity  + "</a>" + "<a href=\"#\" onclick=\"delet(" +item.productInfo.code + ")\">删除</a>" +  "</li>";
		});
		content += "<li>共" + msg.quantity + "件商品，共计￥" + msg.total + "<button onclick=\"goCart()\">去购物车结算</button>" + "</li></ul>";
		$(content).insertAfter('#dropdownMenu1');
	}
	
	function delet(code) {
		$.get('deleteproduct/' + code, function(msg) {
			updatacart(msg);
		})
	}
	
	function showSuccessAddCart() {
		var content = "<p>试题已成功加入购物车！</p>" + "<p><a>" + $('#paper_name').val() + "</a><a>  数量  : " + $('#num').val() + "</a>" + "<button onclick=\"showPaperDetail(" + $('#paper_id').val() + ")\">查看试题详情</button><button onclick=\"goCart()\">去购物车结算</button></p>"; 
		$('#query_result').empty();
		$('#success_addcart').html(content);
		
	}
	
	function showPaperDetail(code) {
		$.get('querysingleincart/' + code, function(msg) {
			successCallback(msg);
		})
	}
	
	function successCallback(msg) {
		$('#success_addcart').empty();
		var content = '<table><thead ><tr><th width=\"20%\">Name</th><th width=\"20%\">Price</th><th width=\"20%\">Description</th><th width=\"20%\">Author</th><th width=\"20%\">Published</th></tr></thead><tbody><tr>';
		content += '<td>' + msg.name + '</td><td>' + msg.price + '</td><td>' + msg.description + '</td><td>' + msg.author + '</td><td>' + msg.publishedOn + '</td><td>';
		content += '</tr></tbody></table><button id=\"min\">-</button><input type=\"text\" id=\"num\" value=\"1\" onchange=\"checkNum()\"/><button id=\"add\">+</button><button id=\"add_cart\" onclick=\"addCart()\">加入购物车</button></button>';
		$('#paper_id').val(msg.id);
		$('#paper_name').val(msg.name);
		$('#query_result').html(content);
	}
	
	function checkNum() {
		if($('#num').val()<=0) {
			$('#num').val(1);
		}
	}
	
	function goCart() {
		window.open("cart");
	}
</script>
</body>
</html>