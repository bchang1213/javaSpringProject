package com.brianchang.web.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="videos_categories")
public class VideoCategory {
	@Id
	@GeneratedValue
	private Long id;
	
	
	////////////////////////////////////////////////////
	// RELATIONSHIPS                                  //
	////////////////////////////////////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="video_id")
	private Video video;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	////////////////////////////////////////////////////
	// END RELATIONSHIPS                              //
	////////////////////////////////////////////////////
	
	public VideoCategory() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
