package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;


public class StudentDao {
	//private static Student student;
	public static Connection connection() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "password");
		}catch(Exception exception) {
			System.out.println("Error while connecting to mysql " + exception);
			exception.printStackTrace();
		}
		return connection;
	}
	
	public static int save(Student student) throws SQLException {
		int saveStatus = 0;
		Connection connection = StudentDao.connection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into studentInfo() values(?,?)");
		preparedStatement.setString(1,  student.getName());
		preparedStatement.setLong(2,  student.getRoll());
		
		saveStatus = preparedStatement.executeUpdate();
		connection.close();
		
		return saveStatus;
	}
	
	public static int delete(int roll) throws SQLException {
		int deleteStatus = 0;
		Connection connection = StudentDao.connection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"delete from studentInfo where rollno = ?");
		preparedStatement.setLong(1,  roll);
		
		deleteStatus = preparedStatement.executeUpdate();
		
		connection.close();
		
		return deleteStatus;
	}
	
	public static List<Student> getAllStudents() throws SQLException{
		Student student = new Student();
		List<Student> studentList = new ArrayList<Student>();
		
		Connection connection = StudentDao.connection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from studentInfo");
		ResultSet resultSet = preparedStatement.executeQuery();
		try {
			if(resultSet != null) {
				while(resultSet.next()) {
					
					student.setName(resultSet.getString(1));
					student.setRoll(resultSet.getInt(2));
					
					studentList.add(student);
				}
			}
		}catch(Exception exception) {
			exception.printStackTrace();  
		}
		
		preparedStatement.close();
		connection.close();
		
		return studentList;
	}
}
