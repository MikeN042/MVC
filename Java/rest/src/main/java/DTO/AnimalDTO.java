package DTO;

import java.sql.Date;

public class AnimalDTO {
	
	private String name;
	private String species;
	private Date birthdate;
	private Long keeperID;
	
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
	public Long getKeeperID() {
		return keeperID;
	}
	public void setKeeperID(Long keeperID) {
		this.keeperID = keeperID;
	}
	

}
