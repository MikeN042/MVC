package com.zookeeper.rest.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zookeeper.rest.DTO.AnimalDTO;
import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Keeper;
import com.zookeeper.rest.Repo.AnimalRepo;
import com.zookeeper.rest.Repo.FeedingRepo;
import com.zookeeper.rest.Repo.KeeperRepo;

@Service
public class AnimalService {
	
	@Autowired
	public AnimalRepo animalRepo;
	@Autowired
	public FeedingRepo feedingRepo;
	@Autowired
	public KeeperRepo keeperRepo;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public AnimalService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	
	public List<AnimalDTO> getAnimals() {
		List<Animal> animals = animalRepo.findAll();
		List<AnimalDTO> animalDTOs = new ArrayList<AnimalDTO>();
		animals.forEach(animal->animalDTOs.add(modelMapper.map(animal,AnimalDTO.class)));
		return animalDTOs;
	}
	
	public AnimalDTO getAnimalByID(long id) {	
		Optional<Animal> animal = animalRepo.findById(id);
		if(animal.isPresent()) {
			return modelMapper.map(animal.get(),AnimalDTO.class);
		}
		
		return null;
	}
	
	public String addAnimal(AnimalDTO animalDTO) {
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
			return "Created";
		}

		return "Not Found";
	}
	
	public void deleteAnimal(Long id) {
		animalRepo.deleteById(id);		
	}

}
