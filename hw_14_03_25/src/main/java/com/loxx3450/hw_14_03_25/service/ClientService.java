package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.ClientDto;
import com.loxx3450.hw_14_03_25.filter.ClientFilter;
import com.loxx3450.hw_14_03_25.model.Client;
import java.util.List;

public interface ClientService {
	List<ClientDto> getAllByFilter(ClientFilter filter);
	ClientDto getById(int id);
}
