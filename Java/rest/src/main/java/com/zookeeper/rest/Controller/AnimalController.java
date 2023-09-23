package com.zookeeper.rest.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zookeeper.rest.Controller.Repo.AnimalRepo;
import com.zookeeper.rest.Controller.Repo.FeedingRepo;
import com.zookeeper.rest.Controller.Repo.KeeperRepo;
import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Feeding;
import com.zookeeper.rest.Models.Keeper;

import DTO.AnimalDTO;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	public AnimalRepo animalRepo;
	@Autowired
	public FeedingRepo feedingRepo;
	@Autowired
	public KeeperRepo keeperRepo;
	
	@GetMapping("/")
	public List<Animal> getAnimals() {
		return animalRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Animal> getAnimal(@PathVariable long id) {		
		return animalRepo.findById(null);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDTO animalDTO) {
		Optional<Keeper> keeper = keeperRepo.findById(animalDTO.getKeeperID());
		
		if(keeper.isPresent()) {
			Animal animal = new Animal();
			animal.setName(animalDTO.getName());
			animal.setSpecies(animalDTO.getSpecies());
			animal.setBirthdate(animalDTO.getBirthdate());
			animal.setKeeper(keeper.get());
			animalRepo.save(animal);
			return new ResponseEntity<>(animal,HttpStatus.CREATED);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/{id}/feed")
	public String feedAnimal(@PathVariable Long id, @RequestParam Long userID) {
		
		Optional<Keeper> user = keeperRepo.findById(userID);		
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
	
	@GetMapping("/feedings")
	public List<Feeding> getAllFeedings() {
		return feedingRepo.findAll();
	}
	
	@DeleteMapping("/{id}/remove")
	public String removeAnimal(@PathVariable Long id) {
		Optional<Animal> animal = animalRepo.findById(id);
		if (animal.isPresent()) {
			animalRepo.deleteById(id);
			return animal.get().getName() + " has been removed";
		}
		return "Animal of id = " + id + " was not found";
	}

}
