package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Student;

public class StudentUtil {
	// private static Student student;
	public static Connection connection() {

		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "password");
		} catch (Exception exception) {
			System.out.println("Error while connecting to mysql " + exception);
			exception.printStackTrace();
		}
		return connection;
	}

	public static int save(Student student) throws SQLException {
		int saveStatus = 0;
		Connection connection = StudentUtil.connection();
		PreparedStatement preparedStatement = connection.prepareStatement("insert into studentInfo() values(?,?)");
		preparedStatement.setString(1, student.getName());
		preparedStatement.setLong(2, student.getRoll());

		saveStatus = preparedStatement.executeUpdate();
		connection.close();

		return saveStatus;
	}

	public static int update(String name, int rollNo) throws SQLException {
		int updateStatus = 0;
		Connection connection = StudentUtil.connection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("update studentInfo set name = ? where rollno = ?");
		preparedStatement.setString(1, name);
		preparedStatement.setLong(2, rollNo);

		updateStatus = preparedStatement.executeUpdate();
		connection.close();

		return updateStatus;
	}

	public static int delete(int roll) throws SQLException {
		int deleteStatus = 0;
		Connection connection = StudentUtil.connection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from studentInfo where rollno = ?");
		preparedStatement.setLong(1, roll);

		deleteStatus = preparedStatement.executeUpdate();

		connection.close();

		return deleteStatus;
	}

	public static List<Student> getAllStudents() throws SQLException {
		//Student student = new Student();
		List<Student> studentList = new ArrayList<Student>();

		Connection connection = StudentUtil.connection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from studentInfo");

		ResultSet resultSet = preparedStatement.executeQuery();

		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					Student student = new Student();

					student.setName(resultSet.getString(1));
					student.setRoll(resultSet.getInt(2));
					
					studentList.add(student);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return studentList;
	}

	public static Student getStudentById() throws SQLException {
		Student student = new Student();

		Connection connection = StudentUtil.connection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from studentInfo where rollno = ?");
		preparedStatement.setLong(1, student.getRoll());

		ResultSet resultSet = preparedStatement.executeQuery();

		try {
			if (resultSet != null) {
				while (resultSet.next()) {

					student.setName(resultSet.getString(1));
					student.setRoll(resultSet.getInt(2));
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		preparedStatement.close();
		connection.close();

		return student;
	}
}
