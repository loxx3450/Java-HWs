package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.RentDto;
import com.loxx3450.hw_14_03_25.model.Rent;
import java.util.List;

public interface RentService {
	List<RentDto> getAll();
	RentDto getById(int id);
}
