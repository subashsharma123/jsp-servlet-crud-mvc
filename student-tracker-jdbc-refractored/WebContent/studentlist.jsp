<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Application</title>
</head>
<body>
	<center>
		<h1>Student Management</h1>
		<h2>
			<a href="/student-tracker-jdbc-refractored/new">Add New Student</a> &nbsp;&nbsp;&nbsp; <a href="/student-tracker-jdbc-refractored/list">List
				All Student</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Student</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
			<c:forEach var="student" items="${listStudent}">
				<tr>
					<td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.firstName}" /></td>
					<td><c:out value="${student.lastName}" /></td>
					<td><c:out value="${student.email}" /></td>
					<td><a href="/student-tracker-jdbc-refractored/edit?id=<c:out value='${student.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/student-tracker-jdbc-refractored/delete?id=<c:out value='${student.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>