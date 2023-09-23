package com.zookeeper.rest.Models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
	
	private long age;

	@OneToOne
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
		long todaysDateInMS = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).getTime();
		long ageDifferenceInMS  = birthdate.getTime() - todaysDateInMS;
		/*conversion for ms to year
		    1 year = 365 days
			1 day = 24 hours
			1 hour = 60 minutes
			1 minute = 60 seconds
			1 second = 1,000 milliseconds
		 */
		this.setAge(ageDifferenceInMS / 1000 /  60 / 24 / 365);
		this.birthdate = birthdate;
	}

	public long getAge() {
		return age;
	}

	private void setAge(long age) {
		this.age = age;
	}

	public Keeper getKeeper() {
		return keeper;
	}

	public void setKeeper(Keeper keeper) {
		this.keeper = keeper;
	}
}
