<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-sm white navbar-lightk"> <!-- Brand -->
	<a class="navbar-brand" href="#"><img width="60px" height="50px"
		src="https://thedesignlove.com/wp-content/uploads/2018/04/335-Bharatanatyam-Logo-Template.jpg" /></a>

	<!-- Links -->
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="home.jsp"><span
				color="#000000">Home</span></a></li>
		<li class="nav-item"><a class="nav-link" href="/about">About</a></li>
		<li class="nav-item"><a class="nav-link" href="/contact">Contact</a></li>

		<!-- Dropdown -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Events </a>

			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">Events 2016</a> <a
					class="dropdown-item" href="#">Events 2017</a> <a
					class="dropdown-item" href="#">Events 2018</a>
			</div></li>
	</ul>

	<div class="d-flex justify-content-end flex-grow-1 ">
		<a class="nav-link" data-toggle="modal" data-target="#loginModal">Sign
			In</a> <a class="nav-link" data-toggle="modal"
			data-target="#registerModal">Register</a>
		<form class="form-inline" action="/action_page.php">
			<input class="form-control mr-sm-2" type="text" placeholder="Search">
			<button class="btn btn-success" type="submit">Search</button>
		</form>
	</div>
	</nav>

	<br>

	<div class="modal" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Sign In</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label class="sr-only" for="userName">User Name</label> <input
								type="email" class="form-control" id="userName"
								placeholder="User Name">
						</div>
						<div class="form-group">
							<label class="sr-only" for="password">Password</label> <input
								type="password" class="form-control" id="password"
								placeholder="Password">
						</div>
						<div class="checkbox">
							<label> <input type="checkbox"> Remember me
							</label>
						</div>
						<button type="submit" class="btn btn-default">Sign in</button>
					</form>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>


	<div class="modal" id="registerModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Sign In</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form modelAttribute="userRegister" action="/userRegister" method="POST">

						<div class="form-group">
							<label class="sr-only" for="firstName">First Name</label> <input
								type="text" class="form-control" id="firstName" th:field="firstName"
								name="firstName" placeholder="First Name">
						</div>
						<div class="form-group">
							<label class="sr-only" for="lastName">Last Name</label> <input
								type="text" class="form-control" id="lastName" th:field="lastName"
								name="lastName" placeholder="Last Name">

						</div>

						<div class="form-group">
							<label>Gender</label> <label class="radio-inline"> 
							<input type="radio" name="gender" id="male" value="male"> Male
							</label> <label class="radio-inline"> 
							<input type="radio"	name="gender" id="female" value="female"> Female
							</label> <label class="radio-inline"> 
							<input type="radio" name="gender" id="others" value="others"> Others
							</label>
						</div>

						<div class="form-group">
							<label class="sr-only" for="address1">Address 1</label> <input
								type="text" class="form-control" name="address1" id="address1"
								placeholder="Address 1">
						</div>

						<div class="form-group">
							<label class="sr-only" for="address2">Address 2</label> <input
								type="text" class="form-control" name="address2" id="address2"
								placeholder="Address 2">
						</div>

						<div class="form-group">
							<label class="sr-only" for="city">City</label> <input type="text"
								class="form-control" name="city" id="city" placeholder="City">
						</div>

						<div class="form-group">
							<label class="sr-only" for="pinCode">Pin Code</label> <input
								type="text" class="form-control" name="pinCode" id="pinCode"
								placeholder="Pin Code">
						</div>

<div class="form-group">
							<label class="sr-only" for="country">Country</label> <select
								name="country" class="custom-select-sm">
								<option selected>Select Country</option>
								<option value="Germany">Germany</option>
								<option value="India">India</option>
						</div>

						</br>

						<div class="form-group">
							<label class="sr-only" for="state">State</label> <select
								name="state" class="custom-select-sm">
								<option selected>Select State</option>
								<option value="Hesse">Hesse</option>

							</select>
						</div>

						

						<div class="form-group">
							<label class="sr-only" for="userName">User Name</label> <input
								type="text" class="form-control" name="userName" id="userName"
								placeholder="Unique user name"> <small id="emailHelp"
								class="form-text text-muted">This will be used for login</small>
						</div>
						<div class="form-group">
						 <input type="hidden" class="form-control" name="isAdmin" value="false" id="isAdmin"> 
						<div class="form-group">
							<label class="sr-only" for="email">Email</label> <input
								type="email" class="form-control" name="email" id="email" placeholder="Email">
							<small id="emailHelp" class="form-text text-muted">Your
								e-mail will not be shared with anyone else.</small>
						</div>
						<div class="form-group">
							<label class="sr-only" for="password">Password</label> <input
								type="password" class="form-control" name="password" id="password"
								placeholder="Password">
						</div>

						<div class="form-group">
							<label class="sr-only" for="confirmPassword">Confirm
								Password</label> <input type="password" class="form-control"
								name="confirmPassword" id="password" placeholder="Confirm Password">
						</div>

						<button type="submit" class="btn btn-default">Register</button>
					</form>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

</body>
</html>