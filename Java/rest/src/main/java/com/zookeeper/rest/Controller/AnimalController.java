package com.zookeeper.rest.Controller;

import java.util.List;

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
import com.zookeeper.rest.Service.AnimalService;
import com.zookeeper.rest.Service.FeedingService;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	

	@Autowired
	public AnimalService animalService;
	@Autowired
	public FeedingService feedingService;
	
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> getAnimals() {
		return new ResponseEntity<>(animalService.getAnimals(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnimalDTO> getAnimal(@PathVariable long id) {	
		AnimalDTO animalDTO = animalService.getAnimalByID(id);
		if(animalDTO != null) {
			return new ResponseEntity<>(animalDTO,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/new")
	public ResponseEntity<AnimalDTO> addAnimal(@RequestBody AnimalDTO animalDTO) {
		String result = animalService.addAnimal(animalDTO);
		if (result.equals("Created")) {
			return new ResponseEntity<AnimalDTO>(animalDTO,HttpStatus.CREATED);
		}
		return new ResponseEntity<AnimalDTO>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteAnimal(@PathVariable Long id){
		feedingService.deleteAllAnimalFeedings(id);
		animalService.deleteAnimal(id);
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}/feedings")
	public ResponseEntity<List<FeedingDTO>> getFeedingByAnimal(@PathVariable Long id){
			return new ResponseEntity<>(feedingService.getAllAnimalFeedings(id),HttpStatus.ACCEPTED);
		}
		
	@PostMapping("/{id}/feed")
	public ResponseEntity<FeedingDTO> feedAnimal(@PathVariable Long id, @RequestParam Long keeperID) {
		FeedingDTO feedingDTO = feedingService.feedAnimal(id, keeperID);
		if (feedingDTO != null){
			return new ResponseEntity<FeedingDTO>(feedingDTO,HttpStatus.ACCEPTED);
		}

			return new ResponseEntity<FeedingDTO>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/feedings/delete/{id}")
	public ResponseEntity<Long>  deleteFeeding(@PathVariable Long id){
		feedingService.deleteFeeding(id);
		return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
	}
}
