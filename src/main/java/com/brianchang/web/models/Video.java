package com.brianchang.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="videos")
public class Video {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String instructor;
	
	@Column
	private String description;
	
	@Column
	private String video_url;
	
	@Column
	private String thumbnail_url;
	
	@Column
	private String image_mask;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date createdAt;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
	
	////////////////////////////////////////////////////
	// RELATIONSHIPS                                  //
	////////////////////////////////////////////////////
	
	//Many videos can have many categories.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "videos_categories", 
        joinColumns = @JoinColumn(name = "video_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
	
	//Many videos can have many play lists.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "playlists_videos", 
        joinColumns = @JoinColumn(name = "video_id"), 
        inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private List<Playlist> playlists;
	
    
    
    
	////////////////////////////////////////////////////
	// END RELATIONSHIPS                              //
	////////////////////////////////////////////////////
    
    
	//Create on/Update on methods
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
//Constructor
	public Video() {}

//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getImage_mask() {
		return image_mask;
	}

	public void setImage_mask(String image_mask) {
		this.image_mask = image_mask;
	}
	
	
	
	
	
}