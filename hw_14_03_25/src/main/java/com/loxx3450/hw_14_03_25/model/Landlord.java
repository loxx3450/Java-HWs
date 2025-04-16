package com.loxx3450.hw_14_03_25.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@DiscriminatorValue("landlord")
public class Landlord extends User {
	@Column(name = "is_verified")
	private boolean isVerified;

	@OneToMany(mappedBy = "landlord")
	private List<Flat> flats;

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean verified) {
		isVerified = verified;
	}

	public List<Flat> getFlats() {
		return flats;
	}

	public void setFlats(List<Flat> flats) {
		this.flats = flats;
	}
}
