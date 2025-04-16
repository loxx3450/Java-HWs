package com.loxx3450.hw_14_03_25.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "rents")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "flat_id", referencedColumnName = "id", nullable = false)
	private Flat flat;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
	private Client client;

	@Column(name = "started_at", nullable = false)
	private LocalDate startedAt;

	@Column(name = "ended_at", nullable = true)
	private LocalDate endedAt;

	@PrePersist
	@PreUpdate
	public void validateDates() {
		if (startedAt.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("The start date cannot be in the past");
		}
		if (endedAt != null && !endedAt.isAfter(startedAt)) {
			throw new IllegalArgumentException("The end date cannot be in the past");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Flat getFlat() {
		return flat;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
