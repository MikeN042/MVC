package com.zookeeper.rest.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.rest.Controller.Repo.UserRepo;
import com.zookeeper.rest.Models.User;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping(value="/")
	public String getPage() {
		return "Welcome";
		
	}
	
	@GetMapping(value="/users")
	public List<User> getUsers() {
		return userRepo.findAll();
		
	}
	
	@PostMapping(value="/users/new")
	public String saveUser (@RequestBody User user) {
		userRepo.save(user);
		return "Success!";
	}

	@PutMapping(value="/users/update/{id}")
	public String updateUser (@PathVariable long id,@RequestBody User user) {
		Optional<User> optUser = userRepo.findById(id);
		if (!optUser.isPresent()) {
			return "User of id = " + id + " not found";
		}
		User targetUser = optUser.get();
		targetUser.setFirstName(user.getFirstName());
		targetUser.setLastName(user.getLastName());
		targetUser.setTitle(user.getTitle());
		userRepo.save(targetUser);
		return "Updated!";
	}
	
	@DeleteMapping(value="/users/remove/{id}")
	public String deleteUser (@PathVariable long id) {
		
		User targetUser = userRepo.findById(id).get();
		userRepo.delete(targetUser);
		return "User Deleted!";
	}
	

}