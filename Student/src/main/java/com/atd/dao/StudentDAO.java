package com.atd.dao;

import java.sql.SQLException;
import java.util.List;

import com.atd.bo.StudentBO;

public interface StudentDAO {

	public void insertStudent(StudentBO studentbo) throws SQLException ;
	public StudentBO selectStudent(int id);
	public List<StudentBO> selectAllStudent();
	public boolean deleteStudent(int id) throws SQLException;
	public boolean updateStudent(StudentBO studentBO) throws SQLException;
}
