<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	
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
						<a class="nav-item nav-link active" href="/home">Home<span class="sr-only">(current)</span></a>
						<a class="nav-item nav-link" href="/admin">Admin</a>
						<a class="nav-item nav-link" href="/library">Technique Library</a>
						<a class="nav-item nav-link" href="/chat">Public Chatroom</a>
						<a class="nav-item nav-link" href="/profile/${currentUser.getId()}/${currentUser.getAlias()}">Profile</a>
						<div class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="" role="button" aria-haspopup="true" aria-expanded="false">More</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/about">About</a>
								<a class="dropdown-item" href="/contact">Contact</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/account">Account</a>
							</div>							
						</div>
						<form id="logoutForm" method="POST" action="/logout">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input class="btn btn-info" id="logout" type="submit" value="Logout!" />
						</form>
					</div>
				</div>
			</nav>
		</header>
		<main>
			<h2>Hello Admin <c:out value="${currentUser.getFirst_name()}"/> <c:out value="${currentUser.getLast_name()}"/></h2>
			<form:form class="videoform" action="/admin/createvideo" method="POST" modelAttribute="video">
				<div class="form-group">
					<form:label path="title">Title:</form:label>
					<form:input class="form-control" type="text" name="title" path="title"/>
				</div>
				
				<div class="form-group">
					<form:label path="instructor">Instructor:</form:label>
					<form:input class="form-control" type="text" name="instructor" path="instructor" />
				</div>
				
				<div class="form-group">
					<form:label path="description">Description:</form:label>
					<form:input class="form-control" type="text" name="description" path="description" />
				</div>
				
				<div class="form-group">
					<form:label path="video_url">Hosted Video URL:</form:label>
					<form:input class="form-control" type="text" name="video_url" path="video_url"/>
				</div>
				
				<div class="form-group">
					<form:label path="thumbnail_url">Thumbnail URL:</form:label>
					<form:input class="form-control" type="text" name="thumbnail_url" path="thumbnail_url"/>
				</div>
				
				<div class="form-group">
					<form:label path="image_mask">Mask Image URL:</form:label>
					<form:input class="form-control" type="text" name="image_mask" path="image_mask"/>	
				</div>
				
				<div class="form-group">
					<form:label path="categories">Categories:</form:label>
					<form:input class="form-control" type="text" required="required" path="categories" />	
				</div>
				
				<input class="btn btn-primary" type="submit" class="submit"/>
			</form:form>
		</main>
	</div>
</body>
</html>