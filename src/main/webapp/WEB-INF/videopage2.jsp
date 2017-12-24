<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/css/videoplayerbar1.css">
	<link rel="stylesheet" type="text/css" href="/css/navbar.css">
</head>
<body>
	<header>
		<div class="container">
			<h1 class="logo">Instructional Site</h1>

		<nav>
			<ul>
				<li><a href="/home">Home</a></li>
				<c:choose>
				<c:when test="${isAdmin.equals(true)}">
				<li><a href="/admin">Admin</a></li>
				</c:when> 
				</c:choose>
				<li><a href="/library">Technique Library</a></li>
				<li><a href="/dashboard">Account</a></li>
				<li><a href="/about">About</a></li>
				<li><a href="/contact">Contact</a></li>
				<li>
					<form id="logoutForm" method="POST" action="/logout">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input id="logout" type="submit" value="Logout!" />
					</form>
				</li>
			</ul>
		</nav>
		</div>
	</header>
	<div id="container">
	
		<!-- <h1> HTML5 Video </h1> -->
	
		<div id="video_container">
			<video id="v" controls width=1000 preload poster=/img/${video.getImage_mask()}>
				<source src="${video.getVideo_url()}" type="video/mp4">
				<source src="http://nettuts.s3.amazonaws.com/763_sammyJSIntro/trailer_test.ogg" type="video/ogg">
				
				<!-- FLASH FALLBACK -->
				<object width="1000" height="776"><param name="movie" value="http://www.youtube.com/v/2AzuX9Lrjv4&amp;hl=en_US&amp;fs=1?color1=0x3a3a3a&amp;color2=0x999999&amp;hd=1"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/2AzuX9Lrjv4&amp;hl=en_US&amp;fs=1?color1=0x3a3a3a&amp;color2=0x999999&amp;hd=1" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="1000" height="776"></embed></object>
			</video>
			
			<div id="videoControls">
				<button id="play" title="Play"> &#x25BA; </button>
				
				<div id="progress"> 
					<div id="progress_box"> 
							<span id="play_progress"></span>
					</div> 
				</div>
				
				<button id="fullScreen" title="FullScreen Toggle">  FS </button>
				<a href="http://www.tutsplus.com"><img src="img/tuts_logo.png" alt="Logo" id="video_tutsLogo" /></a>
			</div>
		</div>
		<div class="videoinfo">
			<p>Title: <c:out value="${video.getTitle()}"/></p>
			<p>Instructor: <c:out value="${video.getInstructor()}"/></p>
			<p>Description: <c:out value="${video.getDescription()}"/></p>
			<p>Created At: <c:out value="${video.getCreatedAt()}"/></p>
		</div>
	</div>	
	
	<script src="/js/videoplayer.js"></script>
</body>
</html>