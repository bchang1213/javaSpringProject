package com.brianchang.web.services;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brianchang.web.models.Playlist;
import com.brianchang.web.models.User;
import com.brianchang.web.repositories.PlaylistRepository;
import com.brianchang.web.repositories.RoleRepository;
import com.brianchang.web.repositories.UserRepository;

@Service
public class UserService {
	//Dependency Injection
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder bcrypt;
	private PlaylistRepository playlistRepo;
	
	public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bcrypt, PlaylistRepository playlistRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bcrypt = bcrypt;
		this.playlistRepo = playlistRepo;
	}
	
	//1
	public void saveWithUserRole(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_USER"));
		Playlist newPlaylist = new Playlist();
		newPlaylist.setUser(user);
		playlistRepo.save(newPlaylist);
		userRepo.save(user);
	}
	
	// 2 
	public void saveUserWithAdminRole(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
		Playlist newPlaylist = new Playlist();
		newPlaylist.setUser(user);
		playlistRepo.save(newPlaylist);
		userRepo.save(user);
	}
	
	//3
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	//4 Create the logout date
	public void findByUpdatedAt(User findByEmail) {
	//Auto-generated method stub
		findByEmail.setUpdatedAt(new Date());
		userRepo.save(findByEmail);
	}
	
	//FIND ALL USERS
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	//FIND ALL ADMINS
	public List<User> findAdmins() {
		return userRepo.findAdmins();
	}
	
	//FIND ONE USER
	public User findUser(Long id) {
		User foundUser = userRepo.findOne(id);
		return foundUser;
	}
	
	//Check if any users are admins
	public Boolean checkforAdmin() {
		List<User> allAdmins = userRepo.findAdmins();
		Integer admincount = 0;
		for(User user : allAdmins) {
			admincount++;
		}
		if(admincount > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Check 1 user if is admin
	public Boolean checkifAdmin(User user) {
		List<User> allAdmins = userRepo.findAdmins();
		Boolean admincheck;
		Integer admincount = 0;
		for(User admin : allAdmins) {
			if(user == admin) {
				admincount ++;
			}
		}
		if(admincount > 0) {
			admincheck = true;
		}
		else {
			admincheck = false;
		}
		return admincheck;
	}
	
	//Delete User by ID
	public void deleteUserByID(Long id) {
		User user = userRepo.findOne(id);
		userRepo.delete(user);
	}
	
	//Make Admin
	public void makeAdmin(Long id) {
		User user = userRepo.findOne(id);
		user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
		userRepo.save(user);
	}
	
	//Find all of the user's friends
	public List<User> findFriends(Long id){
		User currentUser = userRepo.findOne(id);
		List<User> friends = currentUser.getFriends();
		return friends;
	}
}
