package com.loxx3450.hw_17_02_25.service;

import com.loxx3450.hw_17_02_25.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final List<User> users;
	private int idCounter = 0;

	public UserServiceImpl() {
		this.users = new ArrayList<>(List.of(
			new User(++idCounter, "Alice", "Johnson", "123-456-7890", "alice@example.com", "https://aliceblog.com", "Loves Java"),
			new User(++idCounter, "Bob", "Smith", "987-654-3210", "bob@example.com", "https://bobdev.com", "Spring Boot expert"),
			new User(++idCounter, "Charlie", "Brown", "555-111-2222", "charlie@example.com", "https://charliesite.com", "Coffee lover"),
			new User(++idCounter, "Diana", "Adams", "333-444-5555", "diana@example.com", "https://dianacodes.com", "Full-stack developer"),
			new User(++idCounter, "Ethan", "Williams", "777-888-9999", "ethan@example.com", "https://ethanwrites.com", "Writes about tech"),
			new User(++idCounter, "Fiona", "Taylor", "111-222-3333", "fiona@example.com", "https://fionadesign.com", "UX/UI Designer"),
			new User(++idCounter, "George", "Harris", "444-555-6666", "george@example.com", "https://georgeblog.com", "Cloud engineer"),
			new User(++idCounter, "Hannah", "Clark", "666-777-8888", "hannah@example.com", "https://hannahwrites.com", "Writes poetry"),
			new User(++idCounter, "Isaac", "Lopez", "222-333-4444", "isaac@example.com", "https://isaacdev.com", "Backend developer"),
			new User(++idCounter, "Julia", "Martinez", "999-000-1111", "julia@example.com", "https://juliasite.com", "Frontend developer")
		));
	}

	public List<User> getAll() {
		return users;
	}

	public User getById(int id) {
		return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
	}

	public void update(User user) {
		delete(user.getId());
		users.add(user);
	}

	public void add(User user) {
		user.setId(++idCounter);
		users.add(user);
	}

	public void delete(int id) {
		users.removeIf(u -> u.getId() == id);
	}
}
