package com.zookeeper.rest.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String species;
	
	@Column
	private Date birthdate;
	
	@Column
	private String enclosure;
	
	@Column
	private String temperament;

	@ManyToOne
	@JoinColumn(name = "keeper_id", referencedColumnName = "id")
	private Keeper keeper;
	
	@Column
	private LocalDateTime LastFeedingTime;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Keeper getKeeper() {
		return keeper;
	}

	public void setKeeper(Keeper keeper) {
		this.keeper = keeper;
	}
	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public LocalDateTime getLastFeedingTime() {
		return LastFeedingTime;
	}

	public void setLastFeedingTime(LocalDateTime lastFeedingTime) {
		LastFeedingTime = lastFeedingTime;
	}
}
