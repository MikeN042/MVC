package com.zookeeper.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zookeeper.rest.Models.Animal;
import com.zookeeper.rest.Models.Feeding;

public interface FeedingRepo extends JpaRepository<Feeding,Long>{
	
}
