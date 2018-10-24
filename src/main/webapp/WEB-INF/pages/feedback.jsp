<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"%>
<%@ page import="com.sujatha.example.model.Feedback"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback</title>

<script>
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('form-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		}, false);
	})();
</script>

</head>
<body>

	<h3>Feedback Form</h3>

	<%
		String formSubmitstatus = (String) request.getAttribute("status");

		if ("Success".equals(formSubmitstatus)) {
	%>
	<p>Form submitted. Thank you for your valuable feedback</p>
	<%
		}
	%>
	<form class="form-validation" modelAttribute="addFeedback"
		action="/addFeedback" method="POST" novalidate>

		<div class="form-group">
			<label class="sr-only" for="firstName">Full Name</label> <input
				type="text" class="form-control" id="firstName" name="name"
				placeholder="First Name" required>
			<div class="invalid-feedback">Full Name not valid</div>
		</div>

		<div class="form-group">
			<label class="sr-only" for="email">Email</label> <input type="email"
				class="form-control" name="email" id="email" placeholder="Email"
				required> <small id="emailHelp" class="form-text text-muted">Your
				e-mail will not be shared with anyone else.</small>
			<div class="invalid-feedback">E-Mail not valid</div>
		</div>

		<div class="form-group">
			<div class="form-check pl-0">
				<label for="comment">Comment:</label>
				<textarea class="form-control" rows="10" id="comment" name="comment"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-default">Submit</button>
	</form>

	<%
		List<Feedback> fbList = (List) request.getAttribute("feedbackList");
		if (fbList != null && fbList.size() > 0) {
			
			
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="page-header">
					<h1>Comments</h1>

				</div>
				<small class="pull-right"><%=fbList.size() %> comment(s)</small>

				<div class="comments-list">
					<%
				  for(int c=0;c<fbList.size();c++) {
					  Feedback fb = fbList.get(c);
				%>

					<div class="media">
						<p class="pull-right">
							<small><%=fb.getTimestamp()%></small>
						</p>
						<!-- <a class="media-left" href="#"> <img
							src="http://lorempixel.com/40/40/people/1/"> -->
						</a>
						<div class="media-body">

							<h4 class="media-heading user_name">    <%=fb.getName()%></h4>
							<h4><%=fb.getComment()%></h4>
							<p>
								<small><a href="">Like</a> - <a href="">Share</a></small> - <a onclick="location.href=/deleteFeedback/{<%=fb.getId()%>}">Delete</a></small>
							</p>
							
						</div>
					</div>
					<%
				  }
				%>
				</div>

			</div>
		</div>
	</div>
	<%
		}
	%>

</body>
</html>