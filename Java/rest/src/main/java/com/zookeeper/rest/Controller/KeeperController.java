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

import com.zookeeper.rest.Models.Keeper;
import com.zookeeper.rest.Repo.KeeperRepo;

@RestController
@RequestMapping("/keeper")
public class KeeperController {
	
	@Autowired
	private KeeperRepo keeperRepo;
	

	@GetMapping
	public List<Keeper> getKeepers() {
		return keeperRepo.findAll();
		
	}
	
	@PostMapping("/new")
	public String saveKeeper (@RequestBody Keeper keeper) {
		keeperRepo.save(keeper);
		return "Success!";
	}

	@PutMapping("/update/{id}")
	public String updateKeeper (@PathVariable long id,@RequestBody Keeper keeper) {
		Optional<Keeper> optUser = keeperRepo.findById(id);
		if (!optUser.isPresent()) {
			return "User of id = " + id + " not found";
		}
		Keeper targetUser = optUser.get();
		targetUser.setFirstName(keeper.getFirstName());
		targetUser.setLastName(keeper.getLastName());
		targetUser.setTitle(keeper.getTitle());
		keeperRepo.save(targetUser);
		return "Updated!";
	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteKeeper (@PathVariable long id) {
		
		Keeper targetUser = keeperRepo.findById(id).get();
		keeperRepo.delete(targetUser);
		return "User Deleted!";
	}
	

}