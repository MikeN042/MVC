package com.zookeeper.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zookeeper.rest.Models.Animal;

public interface AnimalRepo extends JpaRepository<Animal,Long>{

}
