<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>查询结果</title>
</head>
<body>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
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
			<tr>
				<td><c:out value="${paper.name}" /></td>
				<td><c:out value="${paper.price}" /></td>
				<td><c:out value="${paper.description}" /></td>
				<td><c:out value="${paper.author}" /></td>
				<td><c:out value="${paper.publishedOn}" /></td>
				<td><c:out value="${paper.province}" /></td>
				<td><c:out value="${paper.university}" /></td>
				<td><c:out value="${paper.school}" /></td>
				<td><c:out value="${paper.major}" /></td>
			</tr>
		</tbody>
	</table>
</body>
</html>