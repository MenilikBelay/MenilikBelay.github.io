<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='aspx' uri='/WEB-INF/TldDemo.tld' %> 
<%@ page import="models.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.name}</td>
				<td>${student.age}</td>
			</tr>
		</c:forEach>
	</table>
	
	<table>
		<%
			Student[] students = (Student[])request.getAttribute("students");
			if (students.length > 0) 
				out.write("<table>");
			for (Student student : students) {
				out.write("<tr><td>" + student.getName() + "</td><td>" + student.getAge() +  "</td></tr>");
			}
			if (students.length > 0) 
				out.write("</table>");
		%>
	</table>
	
	<aspx:Label foreColor='red' text='hello'/>
</body>
</html>