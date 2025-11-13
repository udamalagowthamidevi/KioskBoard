package com.hackathon.Restaurant.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.Category;
import com.hackathon.Restaurant.DTO.Item;
import com.hackathon.Restaurant.Repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	
	public List<Item> fetchAllItems(){
		return itemRepo.findAll();	
	}
	
	public List<Item> fetchItemByCategoy(String category){
		if(category.equalsIgnoreCase(Category.BreakFast.toString())) {
			return itemRepo.findByCategory(Category.BreakFast);
		}else if(category.equalsIgnoreCase(Category.NorthIndian.toString())) {
			return itemRepo.findByCategory(Category.NorthIndian);
		}else if(category.equalsIgnoreCase(Category.SouthIndian.toString())) {
			return itemRepo.findByCategory(Category.SouthIndian);
		}else {
			return itemRepo.findByCategory(Category.Juice);
 
		}
	}
	
	public Item fetchItemByName(String name) throws Exception {
		return itemRepo.findByName(name).orElseThrow(()->new Exception("No Item found"));
	}
	
	public Item saveItem(Item item) {
		Item finalItem=Item.builder().name(item.getName())
				  .price(item.getPrice())
				  .category(item.getCategory())
				  .description(item.getDescription())
				  .build();
		return itemRepo.save(finalItem);
	}
	
	public Item updateItem(Item item) {
		try {
			Item saveditem=fetchItemByName(item.getName());
			saveditem.setCategory(item.getCategory());
			saveditem.setPrice(item.getPrice());
			return itemRepo.save(saveditem);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Item fetchItemById(Integer key) {
		return itemRepo.findById(key).get();
	}
	

}
