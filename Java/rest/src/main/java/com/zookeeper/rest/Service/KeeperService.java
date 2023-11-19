package com.zookeeper.rest.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.zookeeper.rest.Models.Keeper;
import com.zookeeper.rest.Repo.KeeperRepo;

@Service
public class KeeperService {
	
	@Autowired
	private KeeperRepo keeperRepo;
	
	
	
	@PostMapping("/new")
	public void saveKeeper (Keeper keeper) {
		keeperRepo.save(keeper);
	}

	public String updateKeeper (long id, Keeper keeper) {
		Optional<Keeper> optUser = keeperRepo.findById(id);
		if (!optUser.isPresent()) {
			return null;
		}
		Keeper targetUser = optUser.get();
		targetUser.setFirstName(keeper.getFirstName());
		targetUser.setLastName(keeper.getLastName());
		targetUser.setTitle(keeper.getTitle());
		keeperRepo.save(targetUser);
		return "Updated!";
	}
	
	public void deleteKeeper (long id) {
		Keeper targetUser = keeperRepo.findById(id).get();
		keeperRepo.delete(targetUser);
	}
}
