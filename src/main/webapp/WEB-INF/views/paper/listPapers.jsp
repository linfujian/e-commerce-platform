<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>List of Papers</title>

<link rel="stylesheet" 
		href='<c:url value="/web-resources/lib/bootstrap-3.3.6/css/bootstrap.min.css" />' >
<link rel="stylesheet"
		href='<c:url value="/web-resources/lib/jquery/jquery-ui-1.10.4.custom.css" />' >

<style type="text/css">
th {
	text-align: left
}
</style>

</head>

<body>
	<div style="width: 95%; margin: 0 auto;">
	
		<div id="paperDialog" style="display: none;">
			<%@ include file="paperForm.jsp" %>
		</div>
		
		<h1>List of Papers</h1>
		
		<button class="btn btn-primary" onclick="addPaper()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Paper
		</button>
		<br>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th width="5%">N.O</th>
					<th width="9.5%">Name</th>
					<th width="9.5%">Price</th>
					<th width="9.5%">Description</th>
					<th width="9.5%">Author</th>
					<th width="9.5%">Published</th>
					<th width="9.5%">Province</th>
					<th width="9.5%">University</th>
					<th width="9.5%">School</th>
					<th width="9.5%">Major</th>
					<th width="9.5%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${paperList}" var="paper" varStatus="loopCounter">
					<tr>
						<td><c:out value="${loopCounter.count}" /></td>
						<td><c:out value="${paper.name}" /></td>
						<td><c:out value="${paper.price}" /></td>
						<td><c:out value="${paper.description}" /></td>
						<td><c:out value="${paper.author}" /></td>
						<td><c:out value="${paper.publishedOn}" /></td>
						<td><c:out value="${paper.province}" /></td>
						<td><c:out value="${paper.university}" /></td>
						<td><c:out value="${paper.school}" /></td>
						<td><c:out value="${paper.major}" /></td>
						<td><nobr>
							<button class="btn btn-primary"
									onclick="editPaper(${paper.id});">
									
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Edit
							</button>
							
							<a class="btn btn-primary"
								onclick="return confirm('Are you sure you want to delete this paper?');"
								href="delete/${paper.id}">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>Delete
							</a>
						</nobr></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript" src='<c:url value="/web-resources/lib/jquery/jquery-2.0.0.min.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/lib/jquery/jquery-ui-1.10.4.custom.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/lib/jquery/jquery.ui.datepicker.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/lib/bootstrap-3.3.6/js/bootstrap.min.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/js-for-listPaper.js"/>'></script>
</body>
</html>