package com.zookeeper.rest.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.rest.Controller.Repo.AnimalRepo;
import com.zookeeper.rest.Controller.Repo.FeedingRepo;
import com.zookeeper.rest.Controller.Repo.UserRepo;
import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Feeding;
import com.zookeeper.rest.Models.User;

@RestController
public class AnimalController {
	
	@Autowired
	public AnimalRepo animalRepo;
	@Autowired
	public FeedingRepo feedingRepo;
	@Autowired
	public UserRepo userRepo;
	
	@GetMapping(value="/animals")
	public List<Animal> getAnimals() {
		return animalRepo.findAll();
	}
	
	@GetMapping(value="/animals/{id}")
	public Optional<Animal> getAnimal(@PathVariable long id) {		
		return animalRepo.findById(null);
	}
	
	@PostMapping(value="/animals/new")
	public String addAnimal(@RequestBody Animal animal) {
		animalRepo.save(animal);
		return animal.getName() + " has been added";
	}
	
	@PostMapping(value="/animals/{id}/feed")
	public String feedAnimal(@PathVariable Long id, @RequestParam Long userID) {
		
		Optional<User> user = userRepo.findById(userID);		
		if (!user.isPresent()){
			return "UserID = " + userID + " not found!";
		}
		
		Optional<Animal> animal = animalRepo.findById(id);
		if (!animal.isPresent()) {
			return "AnimalID = " + id + " not found!";
		}
		
		feedingRepo.save(new Feeding(user.get(),animal.get(),LocalDateTime.now()));
		return "feeding recorded!";
	}
	
	@GetMapping(value="/animals/feedings")
	public List<Feeding> getAllFeedings() {
		return feedingRepo.findAll();
	}
	
	@DeleteMapping(value="animals/{id}/remove")
	public String removeAnimal(@PathVariable Long id) {
		Optional<Animal> animal = animalRepo.findById(id);
		if (animal.isPresent()) {
			animalRepo.deleteById(id);
			return animal.get().getName() + " has been removed";
		}
		return "Animal of id = " + id + " was not found";
	}

}
