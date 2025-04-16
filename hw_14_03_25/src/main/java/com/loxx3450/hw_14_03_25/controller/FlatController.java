package com.loxx3450.hw_14_03_25.controller;

import com.loxx3450.hw_14_03_25.dto.FlatDto;
import com.loxx3450.hw_14_03_25.model.Flat;
import com.loxx3450.hw_14_03_25.service.FlatService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/flats")
public class FlatController {
	private final FlatService flatService;

	public FlatController(FlatService flatService) {
		this.flatService = flatService;
	}

	@GetMapping
	public ResponseEntity<List<FlatDto>> getAll() {
		List<FlatDto> flats = flatService.getAll();
		return ResponseEntity.ok(flats);
	}

	@GetMapping("{id}")
	public ResponseEntity<FlatDto> getById(@PathVariable int id) {
		FlatDto flat = flatService.getById(id);
		return ResponseEntity.ok(flat);
	}
}
