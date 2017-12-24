package com.brianchang.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brianchang.web.models.Category;
import com.brianchang.web.models.ChatMessage;
import com.brianchang.web.models.Playlist;
import com.brianchang.web.models.User;
import com.brianchang.web.models.Video;
import com.brianchang.web.services.CategoryService;
import com.brianchang.web.services.UserService;
import com.brianchang.web.services.VideoService;
import com.brianchang.web.validators.UserValidator;

@Controller
public class MainController {
	//dependency injection
	private UserService userservice;
	private UserValidator validator;
	private VideoService videoservice;
	private CategoryService categoryservice;
	
	public MainController(UserService userservice, UserValidator validator, VideoService videoservice, CategoryService categoryservice) {
		this.userservice = userservice;
		this.validator = validator;
		this.videoservice = videoservice;
		this.categoryservice = categoryservice;
	}
	
	// This allows to change the input of the tags into an array of tags
	// If you do not have this code, the tags path will come into a string
	// We are going to use the InitBinder to change the tags delimited by the comma into tag objects.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, "categories", new CustomCollectionEditor(List.class) {
			public void setAsText(String element) {
			// information coming from the form
			String[] listOfCategoryString = element.split(", ");
			List<Category> listOfCategories = new ArrayList<Category>();

			int maxLength = listOfCategoryString.length > 2 ? 3 : listOfCategoryString.length;

			for(int i = 0; i < maxLength; i++) {
				listOfCategories.add(categoryservice.findOrCreateCategory(listOfCategoryString[i]));
			}

			// set the value of the element to be a list of tags
			setValue(listOfCategories);
			}
		}); 
	}
	
	
	
	
	//PLEASE LOGIN, VIEW #1
	@RequestMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successfull!");
		}
		return "registrationPage.jsp";
	}
	
	//POST ROUTE UPON REGISTRATION
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		//Testing to see if any admins exist
		Boolean adminCheck = userservice.checkforAdmin();
		
		validator.validate(user, result);
		if (result.hasErrors()) {
			return "registrationPage.jsp";
		}
		//If no admins...
		if(adminCheck == false) {
			model.addAttribute("message","Success.");
			userservice.saveUserWithAdminRole(user);
			return "redirect:/login";
		}
		model.addAttribute("message","Success.");
		userservice.saveWithUserRole(user);
		return "redirect:/login";
	}
	
	//DASHBOARD *ACCESS GRANTED
	@RequestMapping(value = "/")
	public String index(Principal principal, Model model) {
		// 1	 retrieve info of user who has logged in.
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			return "redirect:/admin";
		}
		model.addAttribute("currentUser", currentUser);
		return "account.jsp";			
    }
	
	//landing Home
	@RequestMapping("/landinghome")
	public String landinghome(Principal principal) {
		if(principal == null) {
			return "landinghome.jsp";
		}
		return "redirect:/home";
	}
	
	//User navigates Home
	@RequestMapping("/home")
	public String home(Model model, Principal principal) {
	//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		model.addAttribute("currentUser",currentUser);
		return "home.jsp";
	}
	
	//User navigates to account
	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		model.addAttribute("currentUser", currentUser);
		return "account.jsp";
	}
	
	//User navigates to About page
	@RequestMapping("/about")
	public String about(Model model, Principal principal) {
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		model.addAttribute("currentUser", currentUser);
		return "about.jsp";
	}
	
	//Contact Page
	@RequestMapping("/contact")
	public String contactus(Model model, Principal principal) {
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		model.addAttribute("currentUser", currentUser);
		return "contact.jsp";
	}
	
	//User Profile
	@RequestMapping("/profile/{user_id}/{user_alias}")
	public String profile(@PathVariable("user_id")Long user_id, @PathVariable("user_alias")String user_alias, Model model, Principal principal) {
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		
		//Find the user for the profile
		User userProfile = userservice.findUser(user_id);
		//Find the user's friends.
		List<User> friends = userservice.findFriends(user_id);
		//Find the user's playlist.
		Playlist userPlaylist = userProfile.getPlaylist();
		
		//IF IS ADMIN
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("userPlaylist", userPlaylist);
		}
		
		//Add models to the page.
		model.addAttribute("friends", friends);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("userPlaylist", userPlaylist);
		return "profile.jsp";
	}
	//User navigates to Video Library
	@RequestMapping("/library")
	public String showlibrary(Model model, Principal principal) {
		List<Video> allVideos = videoservice.getAllVideos();
		List<Category> allCategories = categoryservice.getAllCategories();
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("allVideos", allVideos);
		
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		model.addAttribute("currentUser",currentUser);
		return "library.jsp";
	}
	
	//Library Video
	@RequestMapping("/library/{video_id}")
	public String showVideo(@PathVariable("video_id")Long video_id, Model model, Principal principal) {
		Video thisvideo = videoservice.getVideo(video_id);
		model.addAttribute("video", thisvideo);
		
		//Retrieve current user, check if admin
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);
		Boolean isAdmin = userservice.checkifAdmin(currentUser);
		//IF IS ADMIN
		if(isAdmin == true) {
			model.addAttribute("isAdmin", isAdmin);
		}
		
		return "videopage4.jsp";
	}
	//*************** THE BELOW CODE ARE THE ROUTES TO OLD, FAILED VIDEO PLAYERS. USE FOR EXPERIMENTATION. NOT FOR PRODUCT.************
//	@RequestMapping("/library/{video_id}/videopage1")
//	public String showVideo1(@PathVariable("video_id")Long video_id, Model model, Principal principal) {
//		Video thisvideo = videoservice.getVideo(video_id);
//		model.addAttribute("video", thisvideo);
//		
//		//Retrieve current user, check if admin
//		String email = principal.getName();
//		User currentUser = userservice.findByEmail(email);
//		Boolean isAdmin = userservice.checkifAdmin(currentUser);
//		//IF IS ADMIN
//		if(isAdmin == true) {
//			model.addAttribute("isAdmin", isAdmin);
//		}
//		
//		return "videopage.jsp";		
//	}
//	
//	@RequestMapping("/library/{video_id}/videopage2")
//	public String showVideo2(@PathVariable("video_id")Long video_id, Model model, Principal principal) {
//		Video thisvideo = videoservice.getVideo(video_id);
//		model.addAttribute("video", thisvideo);
//		
//		//Retrieve current user, check if admin
//		String email = principal.getName();
//		User currentUser = userservice.findByEmail(email);
//		Boolean isAdmin = userservice.checkifAdmin(currentUser);
//		//IF IS ADMIN
//		if(isAdmin == true) {
//			model.addAttribute("isAdmin", isAdmin);
//		}
//		
//		return "videopage2.jsp";		
//	}
//	
//	@RequestMapping("/library/{video_id}/videopage3")
//	public String showVideo3(@PathVariable("video_id")Long video_id, Model model, Principal principal) {
//		Video thisvideo = videoservice.getVideo(video_id);
//		model.addAttribute("video", thisvideo);
//		
//		//Retrieve current user, check if admin
//		String email = principal.getName();
//		User currentUser = userservice.findByEmail(email);
//		Boolean isAdmin = userservice.checkifAdmin(currentUser);
//		//IF IS ADMIN
//		if(isAdmin == true) {
//			model.addAttribute("isAdmin", isAdmin);
//		}
//		
//		return "videopage3.jsp";
//	}

// CHAT ROUTES
	//Contact Page
	@RequestMapping("/chat")
	public String showChat() {
		return "chat.jsp";
	}
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
////	// ADMIN TERRITORY ////////////////////////////////////////////////////////////////////////
	//ADMIN ROUTE
	@RequestMapping("/admin")
	public String adminPage(Principal principal, Model model, @ModelAttribute("video")Video video) {
		String email = principal.getName();
		User currentUser = userservice.findByEmail(email);

		List<User> allAdmins = userservice.findAdmins();
		List<User> allUsers = userservice.findAllUsers();
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allAdmins", allAdmins);
		model.addAttribute("allUsers", allUsers);
		return "adminPage.jsp";
	}

	@PostMapping("/admin/createvideo")
	public String createvideo(@Valid @ModelAttribute("video")Video video, BindingResult result, Principal principal, Model model, @RequestParam("categories")String categories) {
		if(result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userservice.findByEmail(email);

			List<User> allAdmins = userservice.findAdmins();
			List<User> allUsers = userservice.findAllUsers();

			model.addAttribute("currentUser", currentUser);
			model.addAttribute("allAdmins", allAdmins);
			model.addAttribute("allUsers", allUsers);
			return "adminPage.jsp";    			
		}
		videoservice.createVideo(video);
		return "redirect:/admin";
	}

	//Delete user
	@RequestMapping("/admin/delete/{userid}")
	public String deleteuser(@PathVariable("userid")Long id) {
		userservice.deleteUserByID(id);
		return "redirect:/";
	}
	
	//Make user an Admin
	@RequestMapping("admin/{userid}/allow")
	public String makeAdmin(@PathVariable("userid")Long id) {
		userservice.makeAdmin(id);
		return "redirect:/";
	}
}
