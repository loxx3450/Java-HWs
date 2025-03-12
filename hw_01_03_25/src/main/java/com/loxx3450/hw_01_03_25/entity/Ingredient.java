package com.loxx3450.hw_01_03_25.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ingredients")
public class Ingredient {
	@Id
	private int id;
	private String name;
}
