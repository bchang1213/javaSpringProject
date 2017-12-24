package com.brianchang.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brianchang.web.models.Video;
import com.brianchang.web.repositories.VideoRepository;

@Service
public class VideoService {
	private VideoRepository videoRepo;
	
	public VideoService(VideoRepository videoRepo) {
		this.videoRepo = videoRepo;
	}
	
	public void createVideo(Video video) {
		videoRepo.save(video);
	}
	
	public Video getVideo(Long id) {
		Video video = videoRepo.findOne(id);
		return video;
	}
	
	public List<Video> getAllVideos(){
		List<Video> allVideos = videoRepo.findAll();
		return allVideos;
	}
}
