<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- adding the spring taglib tag form -->
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer</title>
<!-- referencing the style sheets /css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">

</head>
<body>
	<h1 style="color: #50d433;" , align="center">Add Cstomer</h1>
	<hr>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relational Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>

		<f:form action="saveCustomer" modelAttribute="customer" method="POST">
		
		<!-- need to associate this data with customer id so we can use in save method to update the specific customer -->
		<f:hidden path="id"/> <!-- customer.getId() and assigne it to this hidden form -->
		<!-- we will use the same save() to update the customer as well by using the saveOrUpdate() of SPring -->
		

			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><f:input path="firstName" /></td>
						<!-- this will bind this textfield to the firstName -->
					</tr>

					<tr>
						<td><label>Last Name:</label></td>
						<td><f:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><f:input path="email" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"></td>
					</tr>
				</tbody>
			</table>

		</f:form>
	<!-- Give the user an option to navigate back to customer list if he dones wana save or ... -->
	<div style="clear; both;">
	<p>
	<a href="${pageContext.request.contextPath}/customer/list">Back to Customer List</a>
	</p>
	</div>
	</div>

</body>
</html>