package com.loxx3450.hw_14_03_25.dto;

import com.loxx3450.hw_14_03_25.model.Client;
import com.loxx3450.hw_14_03_25.model.Rent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDto extends UserDto{
	private int wantsRooms;
	private List<Integer> rentsIds;

	public ClientDto(Client client) {
		setId(client.getId());
		setEmail(client.getEmail());
		setPhone(client.getPhone());
		setFullName(client.getFullName());
		setWantsRooms(client.getWantsRooms());

		if (client.getRents() != null) {
			rentsIds = client.getRents().stream().map(Rent::getId).collect(Collectors.toList());
		}
	}

	public List<Integer> getRentsIds() {
		return rentsIds;
	}

	public void setRentsIds(List<Integer> rentsIds) {
		this.rentsIds = rentsIds;
	}

	public int getWantsRooms() {
		return wantsRooms;
	}

	public void setWantsRooms(int wantsRooms) {
		this.wantsRooms = wantsRooms;
	}
}
