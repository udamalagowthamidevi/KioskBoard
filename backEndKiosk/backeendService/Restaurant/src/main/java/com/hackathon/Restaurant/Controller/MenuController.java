package com.hackathon.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.Restaurant.DTO.Item;
import com.hackathon.Restaurant.Service.ItemService;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/fetchItems")
	public List<Item> fetchAllItems(){
		return itemService.fetchAllItems();
	}
	
	@PostMapping("/addItem")
	public Item addNewItem(@RequestBody Item item) {
		return itemService.saveItem(item);
	}
	
	@PutMapping("/updateItem")
	public Item updateItem(@RequestBody Item item) {
		return itemService.updateItem(item);
	}
	
	@GetMapping("/fetchItemsByCategory/{category}")
	public List<Item> fetchItemsByCategory(@PathVariable String category){
		return itemService.fetchItemByCategoy(category);
	}

}
