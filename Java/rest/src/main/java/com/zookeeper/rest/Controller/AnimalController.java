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
import com.zookeeper.rest.DTO.FeedingDTO;
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
	public ResponseEntity<AnimalDTO> addAnimal(@RequestBody AnimalDTO animalDTO) {
		Optional<Keeper> keeper = keeperRepo.findById(animalDTO.getKeeperID());
		
		Animal animal = new Animal();
		
		if(keeper.isPresent()) {
			animal.setName(animalDTO.getName());
			animal.setSpecies(animalDTO.getSpecies());
			animal.setBirthdate(animalDTO.getBirthdate());
			animal.setKeeper(keeper.get());
			animal.setTemperament(animalDTO.getTemperament());
			animal.setEnclosure(animalDTO.getEnclosure());
			animalRepo.save(animal);
			return new ResponseEntity<AnimalDTO>(animalDTO,HttpStatus.CREATED);
		}

		return new ResponseEntity<AnimalDTO>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteAnimal(@PathVariable Long id){
		feedingRepo.deleteAll(feedingRepo.findByAnimalId(id));
		animalRepo.deleteById(id);
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}/feedings")
	public ResponseEntity<List<FeedingDTO>> getFeedingByAnimal(@PathVariable Long id){
			List<Feeding> feedings = feedingRepo.findByAnimalId(id);
			List<FeedingDTO> feedingsDTO = new ArrayList<FeedingDTO>();
			feedings.forEach(feeding->feedingsDTO.add(modelMapper.map(feeding, FeedingDTO.class)));
			return new ResponseEntity<>(feedingsDTO,HttpStatus.ACCEPTED);
		}
		
	@PostMapping("/{id}/feed")
	public ResponseEntity<FeedingDTO> feedAnimal(@PathVariable Long id, @RequestParam Long keeperID) {
		
		Optional<Keeper> user = keeperRepo.findById(keeperID);		
		if (!user.isPresent()){
			return new ResponseEntity<FeedingDTO>(HttpStatus.NOT_FOUND);
		}
		
		Optional<Animal> animal = animalRepo.findById(id);
		if (!animal.isPresent()) {
			return new ResponseEntity<FeedingDTO>(HttpStatus.NOT_FOUND);
		}
		
		LocalDateTime feedingTime = LocalDateTime.now();
		Animal animalUpdate = animal.get();
		animalUpdate.setLastFeedingTime(feedingTime);
		animalRepo.save(animalUpdate);
		
		Feeding feeding = new Feeding(user.get(),animalUpdate,feedingTime);
		feedingRepo.save(feeding);
		
		return new ResponseEntity<FeedingDTO>(modelMapper.map(feeding,FeedingDTO.class),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/feedings/delete/{id}")
	public ResponseEntity<Long>  deleteFeeding(@PathVariable Long id){
		feedingRepo.deleteById(id);
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
}
