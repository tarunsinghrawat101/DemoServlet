package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StudentUtil;

@SuppressWarnings("serial")
@WebServlet("/deleteServlet")
public class DeleteStudent extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int roll = Integer.parseInt(request.getParameter("id"));
		try {
			StudentUtil.delete(roll);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("viewStudentList");
	}
}
