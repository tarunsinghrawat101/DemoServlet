package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import util.StudentUtil;

@SuppressWarnings("serial")
@WebServlet("/viewStudentList")
public class ViewStudentList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		try {
			List<Student> studentList = new ArrayList<>();
			studentList = StudentUtil.getAllStudents();
			for(Student student: studentList) {
				System.out.println("name: " + student.getName());
				System.out.println("name: " + student.getRoll());
			}
			session.setAttribute("studentList", studentList);
			System.out.println("Session: " + session.getAttribute("studentList"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("viewList.jsp").forward(request, response);
	}
}
