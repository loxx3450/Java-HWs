package com.example.hw_20_02_25.controller;

import com.example.hw_20_02_25.entity.Shop;
import com.example.hw_20_02_25.entity.ShopFilter;
import com.example.hw_20_02_25.service.FileService;
import com.example.hw_20_02_25.service.ShopService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("shops")
public class ShopController {

	private final ShopService shopService;
	private final FileService fileService;

	public ShopController(ShopService shopService, FileService fileService) {
		this.shopService = shopService;
		this.fileService = fileService;
	}

	@GetMapping
	public String getAll(
		@RequestParam Optional<String> namePattern,
		@RequestParam Optional<String> addressPattern,
		@RequestParam Optional<String> categoryPattern,
		Model model) {

		ShopFilter shopFilter = new ShopFilter(
			namePattern.orElse(""),
			addressPattern.orElse(""),
			categoryPattern.orElse("")
		);

		model.addAttribute("shops", shopService.getAll(shopFilter));
		model.addAttribute("filter", shopFilter);

		return "shops.html";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable int id, Model model) {
		Shop shop = shopService.getById(id);
		model.addAttribute("shop", shop);
		return "shop-full-info.html";
	}

	@GetMapping("/add")
	public String getAddShopPage(Model model) {
		model.addAttribute("shop", new Shop());
		return "add-shop.html";
	}

	@PostMapping
	public String addShop(@Valid @ModelAttribute Shop shop,
						  @RequestParam("logotypeFile")MultipartFile logotype) {

		if (!logotype.isEmpty()) {
			String path = fileService.save(logotype);
			shop.setLogotypePath(path);
		}

		shopService.add(shop);
		return "redirect:/shops";
	}

	@GetMapping("/edit/{id}")
	public String getEditShopPage(@PathVariable int id, Model model) {
		Shop shop = shopService.getById(id);

		model.addAttribute("shop", shop);
		return "edit-shop.html";
	}

	@PutMapping
	public String editShop(
		@Valid @ModelAttribute Shop shop,
		@RequestParam(name = "logotypeFile") MultipartFile logotype,
		@RequestParam(name = "existingLogotypePath") String existingLogotypePath) {

		if (!logotype.isEmpty()) {
			String newPath = fileService.save(logotype);
			shop.setLogotypePath(newPath);
		} else {
			shop.setLogotypePath(existingLogotypePath);
		}

		shopService.update(shop.getId(), shop);
		return "redirect:/shops";
	}

	@DeleteMapping("/{id}")
	public String deleteShop(@PathVariable int id) {
		shopService.delete(id);
		return "redirect:/shops";
	}
}
