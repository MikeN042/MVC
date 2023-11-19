package com.zookeeper.rest.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zookeeper.rest.DTO.FeedingDTO;
import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Feeding;
import com.zookeeper.rest.Models.Keeper;
import com.zookeeper.rest.Repo.AnimalRepo;
import com.zookeeper.rest.Repo.FeedingRepo;
import com.zookeeper.rest.Repo.KeeperRepo;

@Service
public class FeedingService {
	
	@Autowired
	public FeedingRepo feedingRepo;
	@Autowired
	public AnimalRepo animalRepo;
	@Autowired
	public KeeperRepo keeperRepo;


	private final ModelMapper modelMapper;
	
	@Autowired
	public FeedingService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public List<FeedingDTO> getAllAnimalFeedings(Long id){
		List<Feeding> feedings = feedingRepo.findByAnimalId(id);
		List<FeedingDTO> feedingsDTO = new ArrayList<FeedingDTO>();
		feedings.forEach(feeding->feedingsDTO.add(modelMapper.map(feeding, FeedingDTO.class)));
		return feedingsDTO;
	}
	
	public FeedingDTO feedAnimal(Long id, Long keeperID) {
		
		Optional<Keeper> user = keeperRepo.findById(keeperID);		
		if (!user.isPresent()){
			return null;
		}
		
		Optional<Animal> animal = animalRepo.findById(id);
		if (!animal.isPresent()) {
			return null;
		}
		
		LocalDateTime feedingTime = LocalDateTime.now();
		Animal animalUpdate = animal.get();
		animalUpdate.setLastFeedingTime(feedingTime);
		animalRepo.save(animalUpdate);
		
		Feeding feeding = new Feeding(user.get(),animalUpdate,feedingTime);
		feedingRepo.save(feeding);
		
		return modelMapper.map(feeding,FeedingDTO.class);
	}
	
	public void deleteAllAnimalFeedings(Long animalID) {
		feedingRepo.deleteAll(feedingRepo.findByAnimalId(animalID));
	}
	
	public void deleteFeeding(Long id) {
		feedingRepo.deleteById(id);
	}
	
}
