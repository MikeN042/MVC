package com.zookeeper.rest.Models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Feeding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "keeper_id", referencedColumnName = "id")
	private Keeper keeper;

	@ManyToOne
	@JoinColumn(name = "animal_id", referencedColumnName = "id")
	private Animal animal;

	@Column
	private LocalDateTime date;

	
   public Feeding() {}
  
   public Feeding (Keeper theUser, Animal theAnimal, LocalDateTime theDate) {
	   keeper = theUser;  
	   animal = theAnimal; 
	   date = theDate;  
   }
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Keeper getUser() {
		return keeper;
	}

	public void setUser(Keeper user) {
		this.keeper = user;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
