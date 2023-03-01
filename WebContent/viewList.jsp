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
	<a href='index.jsp'>Add New Student Info</a>
	<h1>Student Info List</h1>
	<table border='1' style="width: 100%">
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
				System.out.println("Student List : " + list);
			%>
			<%
				for (Student student : list) {
					int roll = student.getRoll();
			%>
			<tr>
				<%-- <td><%=i++%></td>  --%>
				<td><%=student.getName()%></td>
				<td><%=student.getRoll()%></td>
				<td><a href="deleteServlet?roll=<%=student.getRoll()%>" />Delete
					&emsp; <a href="updateRecord.jsp?roll=<%=student.getRoll()%>" />Update</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</body>
</html>