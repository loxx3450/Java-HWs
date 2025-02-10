package com.loxx3450.hw_17_02_25.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String linkToBlog;
	private String notes;

	public String getFullName() {
		return firstName + " " + lastName;
	}
}
