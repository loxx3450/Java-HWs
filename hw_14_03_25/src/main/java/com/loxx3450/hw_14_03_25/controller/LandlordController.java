package com.loxx3450.hw_14_03_25.controller;

import com.loxx3450.hw_14_03_25.dto.LandlordDto;
import com.loxx3450.hw_14_03_25.service.LandlordService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/landlords")
public class LandlordController {
	private final LandlordService landlordService;

	public LandlordController(LandlordService landlordService) {
		this.landlordService = landlordService;
	}

	@GetMapping
	public ResponseEntity<List<LandlordDto>> getAll()
	{
		List<LandlordDto> landlords = landlordService.getAll();
		return ResponseEntity.ok(landlords);
	}

	@GetMapping("{id}")
	public ResponseEntity<LandlordDto> getById(@PathVariable int id)
	{
		LandlordDto landlord = landlordService.getById(id);
		return ResponseEntity.ok(landlord);
	}
}
