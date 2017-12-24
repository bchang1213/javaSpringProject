(function () {
	var videoThumbnails = document.getElementsByClassName("thumbnail");
	
	var addToPlaylistIcons = document.getElementsByClassName("addicon");

	for (var i = 0; i < videoThumbnails.length; i++) {
		console.log("hey the for loop wrked");
		videoThumbnails[i].addEventListener('mouseover', function() {
			console.log('hey we moused over');
			for (var j = 0; j < addToPlaylistIcons.length; j++) {
				addToPlaylistIcons[j].style.opacity = 1;
			}
		});
		videoThumbnails[i].addEventListener('mouseout', function() {
			console.log('hey we moused out');
			for (var j = 0; j < addToPlaylistIcons.length; j++) {
				addToPlaylistIcons[j].style.opacity = 0;
			}
		});		
	}
})();