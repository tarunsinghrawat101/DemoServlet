<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Student"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<a href='index.html'>Add New Student Info</a>
	<h1>Student Info List</h1>
	<table border='1' style = "width: 100%">
		<thead>
			<tr>
				<th>Name</th>
				<th>Roll</th>
				<th>Operation</th>
			</tr>
		</thead>
		<tbody>
		<%
	List<Student> list = (List<Student>) session.getAttribute("studentList");
System.out.println("Student List : "+list);
%>
<% 
				for (Student u : list) { 
			%> 
			<tr> 
				<%-- <td><%=i++%></td>  --%>
				<td><%=u.getName()%></td> 
				<td><%=u.getRoll()%></td> 
				<td><a href=deleteServlet?roll= <%=u.getRoll()%> />Delete</td> 
				<td><a href=updateRecord.jsp?roll= <%=u.getRoll()%> />Update</td>
			</tr> 
			<% 
				} 
			%> 
		<%-- <%=eList %> --%>
			<%-- <c:forEach items="${eList}" var="student">
				<tr>
					<td><c:out value="${student.name}" /></td>
					<td><c:out value="${student.roll}" /></td>
					
					<c:url value="deleteServlet?roll= ${student.roll}" var="delete">
						<c:param name="roll" value="${deleteEntry}" />
					</c:url>
					<td><a href="${delete}"><c:out value="${deleteEntry}" /></a></td>
					
					<c:url value="updateRecord.jsp?roll=${student.roll}" var="update">
						<c:param name="roll" value="${updateEntry}" />
					</c:url>
					<td><a href="${update}"><c:out value="${updateEntry}" /></a></td>
					
					<td><a href="deleteServlet?roll=" + ${student.rollno}>
							delete </a> &nbsp; <a href="updateRecord.jsp?roll="
						+ ${student.rollno}> update</a></td>
				</tr>
			</c:forEach> --%>
			<%-- <tr>
				
				<%
					List<Student> students = request.getAttribute("studentList");

					for (Student student : students) {
						out.print("<td>" + student.getName() + "</td");
						out.print("<td>" + student.getRoll() + "</td");
						out.print("<td> <a href='deleteServlet?id=' " + student.getRoll() + "</a> </td");
						out.print("<td> <a href='updateRecord.jsp?roll='" + student.getRoll() + "</a> </td");
					}
				%>
			</tr> --%>
		</tbody>
	</table>

</body>
</html>