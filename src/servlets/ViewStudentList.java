package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import model.Student;

@SuppressWarnings("serial")
@WebServlet("/viewStudentList")
public class ViewStudentList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Student> studentList = new ArrayList<>();
		response.setContentType("text/html");  
		 
	     PrintWriter printWriter = response.getWriter();  
	     printWriter.println("<a href='index.html'>Add New Student Info</a>");  
	     printWriter.println("<h1>Student Info List</h1>");  
	       
		try {
			studentList = StudentDao.getAllStudents() ;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    
	     printWriter.print("<table border='1' width='100%'>");  
	     printWriter.print("<tr><th>Name</th><th>Roll</th><th>Operation</th></tr>");  
	     
	     for(Student student : studentList){  
	    	 printWriter.print("<tr><td>"+student.getName()+"</td><td>"+student.getRoll() +  
	             "</td> <td><a href='deleteServlet?id="+student.getRoll()+"'>delete</a></td></tr>");  
	     }  
	     
	     printWriter.print("</table>");  
	       
	     printWriter.close();  
	}
}
