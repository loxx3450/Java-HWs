package com.example.hw_20_02_25.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("shops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
	@Id
	private int id;

	@NotBlank(message = "Name cannot be empty")
	@Size(max = 50, message = "Name must be shorter than 50 characters")
	private String name;

	@NotBlank(message = "Address cannot be empty")
	@Size(max = 255, message = "Address must be shorter than 255 characters")
	private String address;

	@NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
	private String phone;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Website cannot be empty")
	@Pattern(regexp = "^(http(s)?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}(/\\S*)?$", message = "Invalid website URL")
	private String website;

	@NotNull(message = "Shop type cannot be null")
	private ShopType type;

	@NotBlank(message = "Description cannot be empty")
	@Size(max = 500, message = "Description must be shorter than 500 characters")
	private String description;

	private String logotypePath;
}
