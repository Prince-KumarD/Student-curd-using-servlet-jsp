<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Student Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Student</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
					<form action="update" method="post">

				<caption>
					<h2>
					Edit Student						
					</h2>
				</caption>

				<fieldset class="form-group">
					<label>Student No</label>
					<input type="text" value="${student.STUDENT_NO}" class="form-control " name="id" readonly="readonly">
				</fieldset>

				<fieldset class="form-group">
					<label>Student Name</label> 
					<input type="text" value="${student.STUDENT_NAME}" class="form-control" name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Date of birth</label> 
					<input type="date"	value="${student.STUDENT_DOB}" class="form-control" name="dob">
				</fieldset>

				<fieldset class="form-group">
					<label>Date of Joining</label> 
					<input type="date"	value="${student.STUDENT_DOJ}" class="form-control" name="doj">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
