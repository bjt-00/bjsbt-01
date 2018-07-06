<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		 <meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Users List</title>

		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css"/>
 
		<script type="text/javascript" src="https://cdn.datatables.net/r/bs-3.3.5/jqc-1.11.3,dt-1.10.8/datatables.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').DataTable();
			} );
		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>
<div class="container">
<h1>Users List</h1>
<jsp:include page="alert.jsp"/>
<div class="row">
 		<form action="logout" method="post">
            <input type="submit" value="Sign Out - ${pageContext.request.remoteUser}"  class="btn btn-default pull-right"/>
        </form>
  <a href="<spring:url value="/user/add"/>" class="btn btn-default pull-right">New User</a>
</div>
<br>
<div class="listing Box">

	<table id="example" class="display" cellspacing="0" width="100%">
	<thead>
	<tr>		
		<th>Full Name</th>
		<th>Phone</th>
		<th>Operation</th>
	</tr>	
	</thead>
	<tbody>
	<c:forEach var="user" items="${usersList}">
		<tr>
			<td>${user.userName}</td>
			<td>${user.phone}</td>
			<td>&nbsp;
			<a href='<spring:url value="/user/edit?id=${user.userId}"/>'><span   class="glyphicon glyphicon-pencil"></span></a>
			<a href='<spring:url value="/user/confirm?id=${user.userId}"/>' ><span   class="glyphicon glyphicon-remove"></span></a>
			</td>
		</tr>
	</c:forEach>
	</tbody>	
	</table>	

    </div>
<script type="text/javascript">
	// For demo to fit into DataTables site builder...
	$('#example')
		.removeClass( 'display' )
		.addClass('table table-striped table-bordered');
</script>
</div>
</body>
</html>

