package com.loxx3450.hw_14_03_25.dto;

import com.loxx3450.hw_14_03_25.model.Flat;
import com.loxx3450.hw_14_03_25.model.Landlord;
import com.loxx3450.hw_14_03_25.model.Rent;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

public class FlatDto {
	private int id;
	private String title;
	private String description;
	private String address;
	private double area;
	private double price;
	private int floor;
	private int roomsCount;
	private boolean isAvailable;
	private int landlordId;
	private List<Integer> rentsIds;

	public FlatDto(Flat flat) {
		setId(flat.getId());
		setTitle(flat.getTitle());
		setDescription(flat.getDescription());
		setAddress(flat.getAddress());
		setArea(flat.getArea());
		setPrice(flat.getPrice());
		setFloor(flat.getFloor());
		setRoomsCount(flat.getRoomsCount());
		setAvailable(flat.isAvailable());
		setLandlordId(flat.getLandlord().getId());

		if (flat.getRents() != null) {
			var rentsIds = flat.getRents().stream().map(Rent::getId).toList();
			setRentsIds(rentsIds);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRoomsCount() {
		return roomsCount;
	}

	public void setRoomsCount(int roomsCount) {
		this.roomsCount = roomsCount;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public int getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(int landlordId) {
		this.landlordId = landlordId;
	}

	public List<Integer> getRentsIds() {
		return rentsIds;
	}

	public void setRentsIds(List<Integer> rentsIds) {
		this.rentsIds = rentsIds;
	}
}
