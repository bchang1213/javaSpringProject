<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<header>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="/landinghome">Navbar</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-item nav-link active" href="/landinghome">Home <span class="sr-only">(current)</span></a>
						<a class="nav-item nav-link" href="/login">Login/Register</a>
					</div>
				</div>
			</nav>
		</header>
	<!--Register  -->
		<div class='row'>
		    <!--LOGIN/LOGOUT  -->
			<div class='col'>
				<c:if test="${logoutMessage != null}">
					<c:out value="${logoutMessage}"></c:out>
				</c:if>
				<h1>Login</h1>
				<c:if test="${errorMessage != null}">
					<c:out value="${errorMessage}"></c:out>
				</c:if>
				<form method="POST" action="/login">
					<div class="form-group">
						<label for="email">Email address</label>
						<input type="email" class="form-control" name="username" placeholder="Enter email" id="email">
						<small class="form-text text-muted">We'll never share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input type="password" name="password" class="form-control" id="password" placeholder="Password">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn btn-primary" value="Login!"/>
				</form>
			</div>
			<div class='col'>
				<p><form:errors path="user.*"/></p>
				<h1>Register</h1>
				<form:form method="POST" action="/registration" modelAttribute="user">
					<div class="form-group">
						<form:label for="inputEmail4" path="email">Email:</form:label>
						<form:input class="form-control" id="inputEmail4" placeholder="Email" path="email"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:password class="form-control" placeholder="Password" path="password"/>
					</div>
					<div class="form-group">
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password class="form-control" placeholder="Password Confirmation" path="passwordConfirmation"/>
					</div>
					<div class="form-group">
						<form:label path="alias">Alias:</form:label>
						<form:input path="alias" class="form-control" placeholder="Alias" type="text" />
						</div>
					<div class="form-group">
						<form:label path="first_name">First Name:</form:label>
						<form:input class="form-control" placeholder="First Name" path="first_name"/>
					</div>
					<div class="form-group">
						<form:label path="last_name">Last Name:</form:label>
						<form:input class="form-control" placeholder="Last Name" path="last_name"/>
					</div>
					<input class="btn btn-primary" type="submit" value="Register!"/>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>