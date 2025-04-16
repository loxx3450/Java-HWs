package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.LandlordDto;
import com.loxx3450.hw_14_03_25.model.Landlord;
import java.util.List;

public interface LandlordService {
	List<LandlordDto> getAll();
	LandlordDto getById(int id);
}
