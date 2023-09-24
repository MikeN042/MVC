package com.zookeeper.rest.DTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AnimalDTO {
	
	private String name;
	private String species;
	private String enclosure;
	private Date birthdate;
	private Long age;
	private String temperament;
	private int temperamentWarning;
	private Long keeperID;
	private String keeperFirstName;
	private String keeperLastName;
	
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
		long ageDifferenceInMS  =  todaysDateInMS - birthdate.getTime();
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
	public Long getKeeperID() {
		return keeperID;
	}
	public void setKeeperID(Long keeperID) {
		this.keeperID = keeperID;
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
		if(temperament.trim().equals("Agressive") || temperament.trim().equals("Deadly")) {
			this.temperamentWarning = 1;
		}
		else {
			this.temperamentWarning = 0;
		}
		this.temperament = temperament;
	}
	public int getTemperamentWarning() {
		return temperamentWarning;
	}
	public String getKeeperFirstName() {
		return keeperFirstName;
	}
	public void setKeeperFirstName(String keeperFirstName) {
		this.keeperFirstName = keeperFirstName;
	}
	public String getKeeperLastName() {
		return keeperLastName;
	}
	public void setKeeperLastName(String keeperLastName) {
		this.keeperLastName = keeperLastName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	

}
