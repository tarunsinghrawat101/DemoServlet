package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.StudentUtil;

@SuppressWarnings("serial")
@WebServlet("/updateStudent")
public class UpdateStudentRecord extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = 0;
		String nameRegex = "^[ A-Za-z]+$";

		String name = request.getParameter("name");
		int roll = Integer.parseInt(request.getParameter("roll"));

		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();

		Matcher nameMatcher = null;
		if (name != "") {
			Pattern nameCheck = Pattern.compile(nameRegex);
			nameMatcher = nameCheck.matcher(name);

		} else {
			printWriter.print("<p>Name cannot be null</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}

		if (nameMatcher.matches()) {

			try {
				status = StudentUtil.update(name, roll);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
			request.getRequestDispatcher("updateRecord.jsp?roll=" + roll).include(request, response);

		} else {
			printWriter.print("<p>Sorry! unable to save record</p>");
			request.getRequestDispatcher("updateRecord.jsp?roll=" + roll).include(request, response);

		}

		printWriter.close();
	}
}
