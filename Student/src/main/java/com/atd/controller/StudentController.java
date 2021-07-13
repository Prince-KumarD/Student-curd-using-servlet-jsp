package com.atd.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atd.dto.StudentDTO;
import com.atd.service.StudentService;
import com.atd.service.StudentServiceImpl;
import com.atd.vo.StudentVO;

@WebServlet("/")
public class StudentController extends HttpServlet {
	
	private StudentService service;
	
	@Override
	public void init() throws ServletException {
		service = new StudentServiceImpl();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		
		System.err.println(action);
		

		
		try {
			switch (action) {
			
			case "/new": 
				showNewForm(req, resp);
				break;
				
			case "/insert": 
				insertStudent(req, resp);
				break;
				
			case "/delete": 
				deleteStudent(req, resp);
				break;

			case "/edit": 
				showEditForm(req, resp);
				break;
				
			case "/update": 
				updateStudent(req, resp);
				break;
				
			case "": 
				listStudent(req,resp);
				break;
			default:
				listStudent(req,resp);
				break;
			}
		} catch( Exception ex) {
			throw new ServletException(ex);
		}
	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	
	// check and fix method name
	public void destroy() {

		service = null;
	}
	
	

	private void listStudent(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		List<StudentDTO> listStudent = service.selectAllStudent();
		System.out.println(listStudent);
		req.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = req.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(req, res);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.err.println("edit");
		int id = Integer.parseInt(request.getParameter("id"));
		System.err.println(id);
		StudentDTO studentdto = service.selectStudent(id);
		
		System.err.println(studentdto.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		request.setAttribute("student", studentdto);
		dispatcher.forward(request, response);

	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException {
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String doj = request.getParameter("doj");
		StudentDTO studentDto = new StudentDTO();
		studentDto.setSTUDENT_NAME(name);
		studentDto.setSTUDENT_DOB(Date.valueOf(dob) );
		studentDto.setSTUDENT_DOJ(Date.valueOf(doj));
		service.insertStudent(studentDto);
		response.sendRedirect("listStudent");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String doj = request.getParameter("doj");
		StudentDTO studentDto = new StudentDTO();
		studentDto.setSTUDENT_NO(id);
		studentDto.setSTUDENT_NAME(name);
		studentDto.setSTUDENT_DOB( Date.valueOf(dob) );
		//studentDto.setSTUDENT_DOB((Date) new SimpleDateFormat("yyyy-MM-dd").parse(dob));
		studentDto.setSTUDENT_DOJ(Date.valueOf(doj));
		service.updateStudent(studentDto);
		response.sendRedirect("listStudent");	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		service.deleteStudent(id);
		response.sendRedirect("list");

	}

}
