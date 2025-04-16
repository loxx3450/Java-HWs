package com.loxx3450.hw_14_03_25.dto;

import com.loxx3450.hw_14_03_25.model.Client;
import com.loxx3450.hw_14_03_25.model.Flat;
import com.loxx3450.hw_14_03_25.model.Rent;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

public class RentDto {
	private int id;
	private int flatId;
	private int clientId;
	private LocalDate startedAt;
	private LocalDate endedAt;

	public RentDto(Rent rent) {
		setId(rent.getId());
		setFlatId(rent.getFlat().getId());
		setClientId(rent.getClient().getId());
		setStartedAt(rent.getStartedAt());
		setEndedAt(rent.getEndedAt());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public LocalDate getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDate startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDate getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(LocalDate endedAt) {
		this.endedAt = endedAt;
	}
}
