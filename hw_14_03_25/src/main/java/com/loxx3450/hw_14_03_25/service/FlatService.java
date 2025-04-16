package com.loxx3450.hw_14_03_25.service;

import com.loxx3450.hw_14_03_25.dto.FlatDto;
import com.loxx3450.hw_14_03_25.model.Flat;
import java.util.List;

public interface FlatService {
	List<FlatDto> getAll();
	FlatDto getById(int id);
}
