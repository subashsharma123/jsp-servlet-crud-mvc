package com.jdbc.servlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.servlet.model.Student;
import com.mysql.jdbc.PreparedStatement;

public class StudentDao {

	private Connection connection;

	private String url;
	private String userName;
	private String password;

	public StudentDao(String url, String userName, String password) {
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public void connect() throws SQLException {
		try {
			if ((connection == null) || (connection.isClosed())) {

				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, userName, password);
			}
		} catch (Exception e) {

		}

	}

	public void disconnect() throws SQLException {
		if ((connection != null) && !connection.isClosed()) {
			connection.close();
		}
	}

	public boolean addStudent(Student student) throws SQLException {
		connect();
		PreparedStatement statement = (PreparedStatement) connection
				.prepareStatement("insert into student(first_name,last_name, email) values (?,?,?)");

		statement.setString(1, student.getFirstName());
		statement.setString(2, student.getLastName());
		statement.setString(3, student.getEmail());

		boolean rSet = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rSet;

	}

	public boolean updateStudent(Student student) throws SQLException {
		connect();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("update student set first_name=? ,last_name=? ,email=? where id =?");
		preparedStatement.setInt(4, student.getId());
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getEmail());
		boolean resultSet = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		return resultSet;
	}

	public boolean deleteStudent(Student student) throws SQLException {
		connect();
		PreparedStatement statement = (PreparedStatement) connection
				.prepareStatement(" delete from student where id=?");
		statement.setInt(1, student.getId());
		boolean resultSet = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return resultSet;

	}

	public List<Student> getListOfStudents() throws SQLException {
		connect();
		List<Student> students = new ArrayList<>();
		PreparedStatement statement = (PreparedStatement) connection.prepareStatement("Select * from student");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");

			Student student = new Student(id, firstName, lastName, email);
			students.add(student);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return students;
	}

	public Student getStudent(int id) throws SQLException {
		connect();
		Student student = null;
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("Select * from student where id=?");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");
			student = new Student(id, firstName, lastName, email);
		}
		resultSet.close();
		preparedStatement.close();
		return student;
	}

}
