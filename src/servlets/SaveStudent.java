package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import model.Student;

@SuppressWarnings("serial")
@WebServlet("/saveStudent")
public class SaveStudent extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		response.setContentType("text/html");  
	        PrintWriter printWriter = response.getWriter();  
	          
	        String name=request.getParameter("name");  
	        int roll=Integer.parseInt(request.getParameter("roll"));
	       
	        Student student=new Student();  
	        student.setName(name);  
	        student.setRoll(roll);  
	        
	          
	        int status = 0;
			try {
				status = StudentDao.save(student);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        if(status >0){  
	        	printWriter.print("<p>Record saved successfully!</p>");  
	        	
	            request.getRequestDispatcher("index.html").include(request, response);  
	        }else{  
	        	printWriter.println("Sorry! unable to save record");  
	        }  
	          
	        printWriter.close();  
	}
}
