<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Information</title>
<%
	String roll = request.getParameter("roll");
%>
</head>
<body>
	<h1>Update Student Info</h1>
	<form action="updateStudent" method="post">
		<table>
			<tr>
				<td>Roll:</td>
				<td><input type="text" name="roll" value=<%=roll%> readonly /></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" pattern="^[ A-Za-z]+$"
					required /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit"
					value=" Update Student Info " /></td>
			</tr>
		</table>
	</form>

	<br />
	<a href="viewStudentList">view Student List</a>
	<br />
	<a href="index.jsp">Add new Student</a>
</body>
</html>