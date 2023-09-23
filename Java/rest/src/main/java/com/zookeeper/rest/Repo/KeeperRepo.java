package com.zookeeper.rest.Controller.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zookeeper.rest.Models.Keeper;

public interface KeeperRepo extends JpaRepository<Keeper,Long> {

}
