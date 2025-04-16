package com.loxx3450.hw_14_03_25.controller;

import com.loxx3450.hw_14_03_25.dto.RentDto;
import com.loxx3450.hw_14_03_25.service.RentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rents")
public class RentController {

	private final RentService rentService;

	public RentController(RentService rentService) {
		this.rentService = rentService;
	}

	@GetMapping
	public ResponseEntity<List<RentDto>> getAll() {
		var rents = rentService.getAll();
		return ResponseEntity.ok(rents);
	}

	@GetMapping("{id}")
	public ResponseEntity<RentDto> getById(@PathVariable int id) {
		var rent = rentService.getById(id);
		return ResponseEntity.ok(rent);
	}
}
