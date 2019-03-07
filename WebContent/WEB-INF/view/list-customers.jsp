<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Customers </title>

<!--  reference our style sheet -->
<link type="text/css" 
rel="stylesheet" 
 href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM- Customer Relationship Management</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!-- add button add customer  -->
			<input type="button" value="add customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			<!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
			<!-- html table goes here -->
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					<!--  Loop through and print customers -->
					<c:forEach var="tempCustomer" items="${customers}">
					
					<!-- Construct an update link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<!-- Construct an delete link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<!--  Display the update link -->
						<td>
							<a href="${updateLink}">update</a>
							|
							<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false"
							>delete</a>
						</td>
					</tr>
					
					</c:forEach>
				</table>
		</div>
	</div>

</body>
</html>