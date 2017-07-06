<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url var="actionUrl" value="save" />

<form:form id="paperForm" commandName="paper" method="post"
	action="${actionUrl}" class="form-horizontal">

	<div class="form-group">
		<label for="name" class="col-xs-4 control-label">Name</label>
		<div class="col-xs-8">
			<form:input name="customerId" path="name" placeholder="Book Name"
				class="form-control" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="price" class="col-xs-4 control-label">Price</label>
		<div class="col-xs-8">
			<form:input path="price" placeholder="Price" maxlength="10"
				class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="description" class="col-xs-4 control-label">Description</label>
		<div class="col-xs-8">
			<form:input path="description" placeholder="Description" class="form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="author" class="col-xs-4 control-label">Author(s)</label>
		<div class="col-xs-8">
			<form:input path="author" placeholder="Author" class="form-control" />
		</div>
	</div>


	<div class="form-group">
		<label for="publishedOn" class="col-xs-4 control-label">Published
			On</label>
		<div class="col-xs-8">
			<form:input path="publishedOn" placeholder="YYYY-MM-DD"
				class="datepicker form-control" />
		</div>
	</div>

	<div class="form-group">
		<label for="province" class="col-xs-4 control-label">Province</label>
		<div class="col-xs-8">
			<form:input path="province" placeholder="Province" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="university" class="col-xs-4 control-label">University</label>
		<div class="col-xs-8">
			<form:input path="university" placeholder="University" />
		</div>
	</div>	

	<div class="form-group">
		<label for="school" class="col-xs-4 control-label">School</label>
		<div class="col-xs-8">
			<form:input path="school" placeholder="School" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="major" class="col-xs-4 control-label">Major</label>
		<div class="col-xs-8">
			<form:input path="major" placeholder="Major" />
		</div>
	</div>

	<form:input path="id" type="hidden" />
</form:form>