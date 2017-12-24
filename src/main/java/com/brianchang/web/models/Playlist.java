package com.brianchang.web.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="playlists")
public class Playlist {

	@Id
	@GeneratedValue
	private Long id;
	
	
	////////////////////////////////////////////////////
	// RELATIONSHIPS                                  //
	////////////////////////////////////////////////////
	
	//One playlist can only ever have one user.
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
    //Many playlists can each hold many different videos.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "playlists_videos",
        joinColumns = @JoinColumn(name = "playlist_id"), 
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> videos;
	
	
	
	////////////////////////////////////////////////////
	// END RELATIONSHIPS                              //
	////////////////////////////////////////////////////
    
//Constructor 
    public Playlist() {}


//Getters and setters
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	
	
	public void setUser(User user) {
		this.user = user;
	}



	public List<Video> getVideos() {
		return videos;
	}



	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
    
    
}
