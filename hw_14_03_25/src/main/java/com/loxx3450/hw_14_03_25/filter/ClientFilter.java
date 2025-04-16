package com.loxx3450.hw_14_03_25.filter;

import java.util.Optional;

public class ClientFilter {
	private Optional<String> name = Optional.empty();
	private Optional<String> phone = Optional.empty();
	private Optional<Integer> wantsRooms = Optional.empty();

	public ClientFilter(Optional<String> name, Optional<String> phone, Optional<Integer> wantsRooms) {
		this.name = name;
		this.phone = phone;
		this.wantsRooms = wantsRooms;
	}

	public Optional<String> getName() {
		return name;
	}

	public void setName(Optional<String> name) {
		this.name = name;
	}

	public Optional<String> getPhone() {
		return phone;
	}

	public void setPhone(Optional<String> phone) {
		this.phone = phone;
	}

	public Optional<Integer> getWantsRooms() {
		return wantsRooms;
	}

	public void setWantsRooms(Optional<Integer> wantsRooms) {
		this.wantsRooms = wantsRooms;
	}
}
