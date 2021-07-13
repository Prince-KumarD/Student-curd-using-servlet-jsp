package com.atd.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.atd.bo.StudentBO;
import com.atd.dao.StudentDAO;
import com.atd.dao.StudentDAOImpl;
import com.atd.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {
	
	private StudentDAO dao;
	
	

	public StudentServiceImpl() {
		dao = new StudentDAOImpl();
	}

	@Override
	public void insertStudent(StudentDTO studentdto) throws SQLException {
		
		StudentBO studentBO = new StudentBO();
		studentBO.setSTUDENT_NAME(studentdto.getSTUDENT_NAME());
		studentBO.setSTUDENT_DOB(studentdto.getSTUDENT_DOB());
		studentBO.setSTUDENT_DOJ(studentdto.getSTUDENT_DOJ());
		
		dao.insertStudent(studentBO);
		
	}



	@Override
	public StudentDTO selectStudent(int id) {
		StudentBO studentbo = dao.selectStudent(id);
		StudentDTO studentdto = new StudentDTO();
		studentdto.setSTUDENT_NO(studentbo.getSTUDENT_NO());
		studentdto.setSTUDENT_NAME(studentbo.getSTUDENT_NAME());
		studentdto.setSTUDENT_DOB(studentbo.getSTUDENT_DOB());
		studentdto.setSTUDENT_DOJ(studentbo.getSTUDENT_DOJ());
		return studentdto;
	}



	@Override
	public List<StudentDTO> selectAllStudent() {

		List<StudentBO> lbo = dao.selectAllStudent();
		List<StudentDTO> sdto = new ArrayList<>();
		
		
		lbo.forEach(bo -> {

			StudentDTO stddto = new StudentDTO();
			stddto.setSTUDENT_NO(bo.getSTUDENT_NO());
			stddto.setSTUDENT_NAME(bo.getSTUDENT_NAME());
			stddto.setSTUDENT_DOB(bo.getSTUDENT_DOB());
			stddto.setSTUDENT_DOJ(bo.getSTUDENT_DOJ());
			
			sdto.add(stddto);
		});
		
		return sdto;
	}



	@Override
	public boolean deleteStudent(int id) throws SQLException {

		
		return dao.deleteStudent(id);
	}



	@Override
	public boolean updateStudent(StudentDTO studentdto) throws SQLException {

		StudentBO studentBO = new StudentBO();
		studentBO.setSTUDENT_NO(studentdto.getSTUDENT_NO());
		studentBO.setSTUDENT_NAME(studentdto.getSTUDENT_NAME());
		studentBO.setSTUDENT_DOB(studentdto.getSTUDENT_DOB());
		studentBO.setSTUDENT_DOJ(studentdto.getSTUDENT_DOJ());
		
		return dao.updateStudent(studentBO);
	}




}
