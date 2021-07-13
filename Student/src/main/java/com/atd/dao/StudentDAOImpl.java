package com.atd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.atd.bo.StudentBO;

public class StudentDAOImpl implements StudentDAO {

	private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/1lAIHXc8Va";
	private String jdbcUsername = "1lAIHXc8Va";
	private String jdbcPassword = "TEMA3CTgGO";

	private static final String INSERT_STUDENT_SQL = "INSERT INTO STUDENT" + "  (STUDENT_NO,STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ) VALUES "
			+ " (?,?, ?, ?)";

	private static final String SELECT_STUDENT_BY_ID = "select STUDENT_NO,STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ from STUDENT where STUDENT_NO =?";
	private static final String SELECT_ALL_STUDENT = "select STUDENT_NO,STUDENT_NAME, STUDENT_DOB, STUDENT_DOJ from STUDENT";
	private static final String DELETE_STUDENT_SQL = "delete from STUDENT where STUDENT_NO = ?;";
	private static final String UPDATE_STUDENT_SQL = "update STUDENT set STUDENT_NAME = ?,STUDENT_DOB= ?, STUDENT_DOJ =? where STUDENT_NO = ?";

	
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertStudent(StudentBO studentbo) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
			List<StudentBO> lbo= selectAllStudent();
			StudentBO sbo = lbo.get(lbo.size() - 1);
			preparedStatement.setInt(1, sbo.getSTUDENT_NO()+1);
			preparedStatement.setString(2, studentbo.getSTUDENT_NAME());
			preparedStatement.setDate(3, studentbo.getSTUDENT_DOB());
			preparedStatement.setDate(4, studentbo.getSTUDENT_DOJ());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public StudentBO selectStudent(int id) {
		StudentBO studentBO = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer sid=rs.getInt("STUDENT_NO");
				String name = rs.getString("STUDENT_NAME");
				Date dob = rs.getDate("STUDENT_DOB");
				Date doj = rs.getDate("STUDENT_DOJ");
				studentBO = new StudentBO();
				studentBO.setSTUDENT_NO(sid);
				studentBO.setSTUDENT_NAME(name);
				studentBO.setSTUDENT_DOB(dob);
				studentBO.setSTUDENT_DOJ(doj);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return studentBO;
	}

	public List<StudentBO> selectAllStudent() {

		List<StudentBO> studentBO = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				StudentBO student = new StudentBO();
				student.setSTUDENT_NO(rs.getInt("STUDENT_NO"));
				student.setSTUDENT_NAME(rs.getString("STUDENT_NAME"));
				student.setSTUDENT_DOB(rs.getDate("STUDENT_DOB"));
				student.setSTUDENT_DOJ(rs.getDate("STUDENT_DOJ"));
				studentBO.add(student);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return studentBO;
	}

	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(StudentBO studentBO) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
			statement.setString(1, studentBO.getSTUDENT_NAME());
			statement.setDate(2, studentBO.getSTUDENT_DOB());
			statement.setDate(3, studentBO.getSTUDENT_DOJ());
			statement.setInt(4, studentBO.getSTUDENT_NO());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
