<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer List</title>
<!-- linking / referencing to the css file -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
	<h1 style="color: #50d433;" , align="center">Customers List</h1>
	<hr>
	<div id="wrapper">
		<div id="header">
			<h1 align="center">CRM - Cutomer Relationship Manager</h1>
		</div>
	</div>
	<div id="container">
		<div id="content">

			<!-- Add new Button ... Add Customer -->
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"> <br>


			<form action="seaerchCustomer" >
				<!-- Add new Button ... Search Customer -->
				
				<input type="submit" value="Search Customer">
				<input type="text" name="search">

			<form>


			<!-- Add the html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name Name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>
				<tr>
					<!-- Loop over and print the customers list -->
					<c:forEach var="tempCustomer" items="${customers}">
						<!-- will loop over the customer and show them in a table -->

						<!-- Construct and "update link" with customer id, using jstl -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id }"></c:param>
						</c:url>

						<!-- Construct and "Delete link" with customer id, using jstl -->
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id }"></c:param>
						</c:url>

						<tr>
							<td>${tempCustomer.firstName }</td>
							<!-- this will call customer.getFirsName() -->
							<td>${tempCustomer.lastName }</td>
							<td>${tempCustomer.email }</td>
							<td><a href="${updateLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if(!(confirm('Are you sure you want to delete this customer ?'))) return false">Delete</a></td>
						</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>



</body>
</html>