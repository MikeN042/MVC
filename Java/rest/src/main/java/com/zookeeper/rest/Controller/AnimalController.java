package com.zookeeper.rest.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.zookeeper.rest.DTO.AnimalDTO;
import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Feeding;
import com.zookeeper.rest.Models.Keeper;
import com.zookeeper.rest.Repo.AnimalRepo;
import com.zookeeper.rest.Repo.FeedingRepo;
import com.zookeeper.rest.Repo.KeeperRepo;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	private final ModelMapper modelMapper;

	
	@Autowired
	public AnimalRepo animalRepo;
	@Autowired
	public FeedingRepo feedingRepo;
	@Autowired
	public KeeperRepo keeperRepo;
	@Autowired
	public AnimalController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> getAnimals() {
		List<Animal> animals = animalRepo.findAll();
		List<AnimalDTO> animalDTOs = new ArrayList<AnimalDTO>();
		animals.forEach(animal->animalDTOs.add(modelMapper.map(animal,AnimalDTO.class)));
		
		return new ResponseEntity<>(animalDTOs,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnimalDTO> getAnimal(@PathVariable long id) {	
		Optional<Animal> animal = animalRepo.findById(id);
		
		
		if(animal.isPresent()) {
			AnimalDTO animalDTO = modelMapper.map(animal.get(),AnimalDTO.class);
			return new ResponseEntity<>(animalDTO,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
			animal.setTemperament(animalDTO.getTemperament());
			animal.setEnclosure(animalDTO.getEnclosure());
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
