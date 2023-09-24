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
  
   public Feeding (Keeper user, Animal animal, LocalDateTime date) {
	   this.keeper = user;  
	   this.animal = animal; 
	   this.date = date;  
   }

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public Keeper getKeeper() {
	return keeper;
}

public void setKeeper(Keeper keeper) {
	this.keeper = keeper;
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
