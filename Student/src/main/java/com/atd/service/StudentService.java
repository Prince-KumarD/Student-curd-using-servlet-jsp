package com.atd.service;

import java.sql.SQLException;
import java.util.List;

import com.atd.bo.StudentBO;
import com.atd.dto.StudentDTO;

public interface StudentService {
	
	public void insertStudent(StudentDTO studentdto) throws SQLException ;
	public StudentDTO selectStudent(int id);
	public List<StudentDTO> selectAllStudent();
	public boolean deleteStudent(int id) throws SQLException;
	public boolean updateStudent(StudentDTO studentdto) throws SQLException;

}
