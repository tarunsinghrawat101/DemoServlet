package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import util.StudentUtil;

@SuppressWarnings("serial")
@WebServlet("/saveStudent")
public class SaveStudent extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = 0;

		String rollNoRegex = "^[0-9]*$";
		String nameRegex = "^[ A-Za-z]+$";

		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();

		String name = request.getParameter("name");
		String roll = request.getParameter("roll");
//		System.out.println("ROLL NUMBER: " + roll);
		Pattern rollNoCheck = Pattern.compile(rollNoRegex);
		Matcher rollNoMatcher = rollNoCheck.matcher(roll);

		Matcher nameMatcher = null;
		if (name != "") {
			Pattern nameCheck = Pattern.compile(nameRegex);
			nameMatcher = nameCheck.matcher(name);

		} else {
			printWriter.print("<p>Name cannot be null</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}

		int rollNo = Integer.parseInt(roll);

		if (rollNoMatcher.matches() && nameMatcher.matches()) {
			Student student = new Student();
			student.setName(name);
			student.setRoll(rollNo);

			try {
				status = StudentUtil.save(student);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				/*
				 * if(e.getErrorCode() == 1062) { try { status = StudentUtil.update(name,
				 * rollNo); } catch (SQLException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); } }
				 */
				printWriter.print("<p>Roll Number already exists!</p>");
				request.getRequestDispatcher("index.html").include(request, response);
				e.printStackTrace();
			}
		} else {
			status = -1;
		}

		if (status > 0) {
			printWriter.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("index.html").include(request, response);

		} else if (status == -1) {
			printWriter.print("<p>Sorry! unable to save record due to invalid Roll No or Student Name</p>");
			request.getRequestDispatcher("index.html").include(request, response);

		}

		printWriter.close();
	}
}
