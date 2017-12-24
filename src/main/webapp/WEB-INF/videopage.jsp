<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Library</title>
<!-- 	CSS -->
	<link rel="stylesheet" type="text/css" href="/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="/css/videoplayer1.css">
	<link rel="stylesheet" type="text/css" href="/css/videoplayerbar1.css">
<!-- javascript tags -->
	<script src="/js/videomagic.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#v').bind('contextmenu',function() { return false; });
			
		});
	</script>
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
	<main>
		<div class="canvasplayer">
			<canvas id="c"style="background-image: url(${video.getImage_mask()})"></canvas>
			<div id="buttons">
				<button onclick="getVideo().play()">Play</button>
				<button onclick="getVideo().pause()">Pause</button>
				<button onclick="getVideo().currentTime+=10">FastForward +.10</button>
			</div>
		</div>
		<div class="videoinfo">
			<p>Title: <c:out value="${video.getTitle()}"/></p>
			<p>Instructor: <c:out value="${video.getInstructor()}"/></p>
			<p>Description: <c:out value="${video.getDescription()}"/></p>
			<p>Created At: <c:out value="${video.getCreatedAt()}"/></p>
		</div>
		<video id="v" controls="controls" width="320" height="240" loop preload="true">
			<source id="mp4" src="<c:out value="${video.getVideo_url()}"/>" type="video/mp4">	
		</video>	
		<div id="videoControls"> 
			<button id="play" title="Play"> &#x25BA; </button> 
		  
			<div id="progress">  
				<div id="progress_box">  
					<span id="play_progress"></span> 
				</div>  
			</div> 
		  
			<button id="fullScreen" title="FullScreen Toggle">  FS </button> 
		</div>
		<script src="/js/videoplayer.js"></script>
		
	</main>
</body>
</html>