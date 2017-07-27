<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		 <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>User Form</title>

		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>
<div class="container">
<h1>${action} User</h1>
<jsp:include page="alert.jsp"/>
		<form:form modelAttribute="user" method="post" action="${action}" class="form-group">

				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
					<form:hidden path="userId"/>
				  <div class="form-group">
   						 <label for="userName">Email address:</label>
						<input type="text" name="userName" value="${user.userName}" class="form-control" placeholder="User Name" required="required"  />
				 </div>
				 <div class="form-group" >
				    <label for="phone" >Phone:</label>
					<input type="text" name="phone" value="${user.phone}" class="form-control" placeholder="000-000-000" required="required" pattern="\d{3}[\-]\d{3}[\-]\d{4}" />
				 </div>
				<div class="form-group" >
					<button type="submit" name="${action}" class="btn btn-primary" ${(action eq 'delete'?'formnovalidate':'') }  >${action}</button>
					<a href="<spring:url value="/user"/>" class="btn btn-danger"  >Cancel</a>
				</div>
				
		</form:form>
</div>
</body>