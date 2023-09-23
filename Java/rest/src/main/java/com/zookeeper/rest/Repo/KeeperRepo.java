package com.zookeeper.rest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zookeeper.rest.Models.Keeper;

public interface KeeperRepo extends JpaRepository<Keeper,Long> {

}
