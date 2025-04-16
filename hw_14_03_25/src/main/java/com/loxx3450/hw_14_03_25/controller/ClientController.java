package com.loxx3450.hw_14_03_25.controller;

import com.loxx3450.hw_14_03_25.dto.ClientDto;
import com.loxx3450.hw_14_03_25.filter.ClientFilter;
import com.loxx3450.hw_14_03_25.service.ClientService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientDto>> getAll(
		@RequestParam(required = false) Optional<String> name,
		@RequestParam(required = false) Optional<String> phone,
		@RequestParam(required = false, name = "wants_rooms") Optional<Integer> wantsRooms
	)
	{
		var filter = new ClientFilter(name, phone, wantsRooms);

		List<ClientDto> clients = clientService.getAllByFilter(filter);
		return ResponseEntity.ok(clients);
	}

	@GetMapping("{id}")
	public ResponseEntity<ClientDto> getById(@PathVariable int id)
	{
		ClientDto clientDto = clientService.getById(id);
		return ResponseEntity.ok(clientDto);
	}
}
