package com.loxx3450.hw_14_03_25.dto;

import com.loxx3450.hw_14_03_25.model.Flat;
import com.loxx3450.hw_14_03_25.model.Landlord;
import com.loxx3450.hw_14_03_25.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

public class LandlordDto extends UserDto {
	private boolean isVerified;

	private List<Integer> flatsIds;

	public LandlordDto(Landlord landlord) {
		this.setId(landlord.getId());
		setEmail(landlord.getEmail());
		setPhone(landlord.getPhone());
		setFullName(landlord.getFullName());
		setVerified(landlord.isVerified());

		if (landlord.getFlats() != null) {
			List<Integer> flatsIds = landlord.getFlats().stream().map(Flat::getId).toList();
			setFlatsIds(flatsIds);
		}
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean verified) {
		isVerified = verified;
	}

	public List<Integer> getFlatsIds() {
		return flatsIds;
	}

	public void setFlatsIds(List<Integer> flatsIds) {
		this.flatsIds = flatsIds;
	}
}
