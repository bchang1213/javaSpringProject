<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>Video</title>
	<!-- video related -->
	<link rel="stylesheet" type="text/css" href="/css/videopage_navbar.css" />
	<link rel="stylesheet" type="text/css" href="/css/videopage4.css" />
	<link rel="stylesheet" type="text/css" href="/css/videopage4_info.css" />
</head>
<body>
	<h1 class="videotitle"><c:out value="${video.getTitle()}"/></h1>
	<figure id="videoContainer" data-fullscreen="false">
		<video id="video" controls preload="metadata">
			<source src="<c:out value="${video.getVideo_url()}"/>" type="video/mp4">
			<source src="video/sintel-short.webm" type="video/webm">
			<track label="English" kind="subtitles" srclang="en" src="subtitles/vtt/sintel-en.vtt" default>
			<track label="Deutsch" kind="subtitles" srclang="de" src="subtitles/vtt/sintel-de.vtt">
			<track label="Español" kind="subtitles" srclang="es" src="subtitles/vtt/sintel-es.vtt">
		</video>
		<div id="video-controls" class="controls" data-state="hidden">
			<button id="playpause" type="button" data-state="play">Play/Pause</button>
			<button id="stop" type="button" data-state="stop">Stop</button>
			<div class="progress">
				<progress id="progress" value="0" min="0">
					<span id="progress-bar"></span>
				</progress>
			</div>
			<button id="mute" type="button" data-state="mute">Mute/Unmute</button>
			<button id="volinc" type="button" data-state="volup">Vol+</button>
			<button id="voldec" type="button" data-state="voldown">Vol-</button>
			<button id="fs" type="button" data-state="go-fullscreen">Fullscreen</button>
			<button id="subtitles" type="button" data-state="subtitles">CC</button>
		</div>
	</figure>
	<div class="tags">
		<p>Tags:</p>
		<c:forEach items = "${video.getCategories()}" var = "category">
		<div class="tag">${category.getName()}</div>
		</c:forEach>
	</div>
	<!-- video-player related scripts -->
	<script src="/js/videoplayer4.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#video').bind('contextmenu',function() { return false; });
			
		});
	</script>
	<!--[if lt IE 9]><script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv-printshiv.min.js" type="text/javascript"></script><![endif]-->
</body>
</html>