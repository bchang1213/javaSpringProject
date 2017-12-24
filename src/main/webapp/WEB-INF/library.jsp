<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Library</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/library.css" />
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
					<c:choose>
					<c:when test="${isAdmin.equals(true)}">
						<a class="nav-item nav-link" href="/admin">Admin</a>
					</c:when> 
					</c:choose>
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
			<div class="row">
				<div class="col-md-auto">
					<h4>All Videos</h4>
					<c:forEach items = "${allVideos}" var = "video">
					<div class='thumbnail'>
						<a id="vid-thumbnail" href="/library/${video.getId()}">
							<img class="vid-item" src="${video.getThumbnail_url()}" alt="${video.getTitle()}thumb"/>
						</a>							
						<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.0" x="0px" y="0px" viewBox="0 0 512 512" class="addicon" id="addicon" ><path d="M363 277v-42h-86v-86h-42v86h-86v42h86v86h42v-86h86zm42-213q17 0 30 13t13 30v298q0 17-13 30t-30 13H107q-18 0-30.5-13T64 405V107q0-17 12.5-30T107 64h298z"></path></svg>							
						<a class="vid-title" href="/library/${video.getId()}">${video.getTitle()}</a>
					</div>
					</c:forEach>
				</div>
				<div class="col"></div>
				<div class="col-md-auto">
					<h5>All Categories:</h5>
					<c:forEach items = "${allCategories}" var = "category">
						<p>${category.getName()}</p>
					</c:forEach>
				</div>
			</div>
		</main>
	</div>
	<script src="/js/library.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>
</html>