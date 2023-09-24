package com.zookeeper.rest.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FeedingDTO {

		private Long id;
		private String date;
		private Long animalId;
		private String animalName;
		private Long keeperID;
		private String keeperFirstName;
		private String keeperLastName;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		public Long getAnimalId() {
			return animalId;
		}
		public void setAnimalId(Long animalId) {
			this.animalId = animalId;
		}
		public String getAnimalName() {
			return animalName;
		}
		public void setAnimalName(String animalName) {
			this.animalName = animalName;
		}
		public Long getKeeperID() {
			return keeperID;
		}
		public void setKeeperID(Long keeperID) {
			this.keeperID = keeperID;
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
}
