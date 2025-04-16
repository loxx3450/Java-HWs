package com.loxx3450.hw_14_03_25.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Optional;
import lombok.Data;

@Entity
@DiscriminatorValue("client")
public class Client extends User {
	@Column(name = "wants_rooms")
	private int wantsRooms;

	@OneToMany(mappedBy = "client")
	private List<Rent> rents;

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public int getWantsRooms() {
		return wantsRooms;
	}

	public void setWantsRooms(int wantsRooms) {
		this.wantsRooms = wantsRooms;
	}
}
