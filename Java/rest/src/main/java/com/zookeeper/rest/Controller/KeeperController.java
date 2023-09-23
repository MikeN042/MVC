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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.rest.Controller.Repo.KeeperRepo;
import com.zookeeper.rest.Models.Keeper;

@RestController
@RequestMapping("/keeper")
public class KeeperController {
	
	@Autowired
	private KeeperRepo keeperRepo;
	
	@GetMapping(value="/")
	public String getPage() {
		return "Welcome";
		
	}
	
	@GetMapping(value="/")
	public List<Keeper> getUsers() {
		return keeperRepo.findAll();
		
	}
	
	@PostMapping(value="/new")
	public String saveUser (@RequestBody Keeper user) {
		keeperRepo.save(user);
		return "Success!";
	}

	@PutMapping(value="/update/{id}")
	public String updateUser (@PathVariable long id,@RequestBody Keeper user) {
		Optional<Keeper> optUser = keeperRepo.findById(id);
		if (!optUser.isPresent()) {
			return "User of id = " + id + " not found";
		}
		Keeper targetUser = optUser.get();
		targetUser.setFirstName(user.getFirstName());
		targetUser.setLastName(user.getLastName());
		targetUser.setTitle(user.getTitle());
		keeperRepo.save(targetUser);
		return "Updated!";
	}
	
	@DeleteMapping(value="/remove/{id}")
	public String deleteUser (@PathVariable long id) {
		
		Keeper targetUser = keeperRepo.findById(id).get();
		keeperRepo.delete(targetUser);
		return "User Deleted!";
	}
	

}