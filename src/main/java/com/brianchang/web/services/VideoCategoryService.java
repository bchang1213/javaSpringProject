package com.brianchang.web.services;

import org.springframework.stereotype.Service;

import com.brianchang.web.models.VideoCategory;
import com.brianchang.web.repositories.VideoCategoryRepository;

@Service
public class VideoCategoryService {

	private VideoCategoryRepository vidcatRepo;
	
	public VideoCategoryService(VideoCategoryRepository vidcatRepo) {
		this.vidcatRepo = vidcatRepo;
	}
	
	public void createVideoCategory(VideoCategory vidcat) {
		vidcatRepo.save(vidcat);
	}
}
