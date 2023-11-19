package com.zookeeper.rest.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.zookeeper.rest.Service.KeeperService;

@RestController
@RequestMapping("/keeper")
public class KeeperController {
	
	@Autowired
	private KeeperRepo keeperRepo;
	@Autowired
	private KeeperService keeperService;
	

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
	public ResponseEntity<Long> updateKeeper (@PathVariable long id,@RequestBody Keeper keeper) {
		String result = keeperService.updateKeeper(id, keeper);
		if (result != null) {
			return new ResponseEntity<Long>(id,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Long>(id,HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/remove/{id}")
	public String deleteKeeper (@PathVariable long id) {
		keeperService.deleteKeeper(id);
		return "User Deleted!";
	}
	

}