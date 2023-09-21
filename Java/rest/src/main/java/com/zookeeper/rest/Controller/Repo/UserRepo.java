package com.zookeeper.rest.Controller.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zookeeper.rest.Models.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
