package com.loxx3450.hw_17_02_25.controller;


import com.loxx3450.hw_17_02_25.entity.User;
import com.loxx3450.hw_17_02_25.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getAll(@RequestParam Optional<String> namePattern, Model model) {
		List<User> users = userService.getAll();
		if (namePattern.isPresent()) {
			users = users.stream()
				.filter(u -> u.getFullName().toLowerCase()
					.contains(namePattern.get().toLowerCase()))
				.toList();
		}
		model.addAttribute("users", users);
		model.addAttribute("pattern", namePattern.orElse(""));
		return "users.html";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable int id, Model model) {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "user-full-info.html";
	}

	@GetMapping("/add")
	public String getAddUserPage(Model model) {
		model.addAttribute("user", new User());
		return "add-user.html";
	}

	@PostMapping
	public String addUser(@ModelAttribute User user) {
		userService.add(user);
		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String getEditUserPage(@PathVariable int id, Model model) {
		User user = userService.getById(id);

		model.addAttribute("user", user);
		return "edit-user.html";
	}

	@PutMapping
	public String editUser(@ModelAttribute User user) {
		userService.update(user);
		return "redirect:/users";
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/users";
	}
}
