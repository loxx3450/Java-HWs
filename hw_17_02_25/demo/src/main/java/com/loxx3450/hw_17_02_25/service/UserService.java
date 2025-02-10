package com.loxx3450.hw_17_02_25.service;

import com.loxx3450.hw_17_02_25.entity.User;
import java.util.List;

public interface UserService {
	List<User> getAll();

	User getById(int id);

	void update(User user);

	void add(User user);

	void delete(int id);
}
