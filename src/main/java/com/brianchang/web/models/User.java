package com.brianchang.web.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

	//Member variables
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@Size(min=1, message="Alias must be at least 1 char long.")
	private String alias;
	
	@Column
	@Size(min=1, message="First name must be at least 1 char.")
	private String first_name;
	
	@Column
	@Size(min=1, message="Last name must be at least 1 char.")
	private String last_name;
	
	@Column(unique=true)
	@Email(message="Please enter valid e-mail address.")
	private String email;
	
	@Size(min=8, message="Password must be at least 8 chars.")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	//UPDATED AND CREATED TIMES
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date createdAt;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
	
	//Create on/Update on methods
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	
	////////////////////////////////////////////////////
	// RELATIONSHIPS                                  //
	////////////////////////////////////////////////////
	//Users have many roles
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_roles", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	//Users have many friends.those friends can have many friends as well.
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "user_relationships", 
		joinColumns = @JoinColumn(name = "user1_id"), 
		inverseJoinColumns = @JoinColumn(name = "user2_id"))
	private List<User> friends;
	
	//Each user can have A playlist
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Playlist playlist;

	////////////////////////////////////////////////////
	// END RELATIONSHIPS                              //
	////////////////////////////////////////////////////
	
	//CONSTRUCTOR
		public User() {
			
		}
		
	//GETTERS AND SETTERS
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}

		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
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

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}

		public Playlist getPlaylist() {
			return playlist;
		}

		public void setPlaylist(Playlist playlist) {
			this.playlist = playlist;
		}

		public List<User> getFriends() {
			return friends;
		}

		public void setFriends(List<User> friends) {
			this.friends = friends;
		}
		
		
		
		
	
}