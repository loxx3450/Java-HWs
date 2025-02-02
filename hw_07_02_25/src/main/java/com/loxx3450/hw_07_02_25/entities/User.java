package com.loxx3450.hw_07_02_25.entities;

import com.loxx3450.hw_07_02_25.annotations.Email;
import com.loxx3450.hw_07_02_25.annotations.Future;
import com.loxx3450.hw_07_02_25.annotations.Max;
import com.loxx3450.hw_07_02_25.annotations.Min;
import com.loxx3450.hw_07_02_25.annotations.NotEmpty;
import com.loxx3450.hw_07_02_25.annotations.NotNull;
import com.loxx3450.hw_07_02_25.annotations.Pattern;
import com.loxx3450.hw_07_02_25.annotations.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	@NotNull
	@Min(1)
	private Integer id;

	@NotNull
	@NotEmpty
	@Pattern(regex = "^[A-Za-z -]+$")
	@Size(min = 3, max = 40)
	private String name;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Future
	private LocalDate subscriptionPaidUntil;

	@NotNull
	@Min(18)
	@Max(100)
	private Integer age;
}
