package com.hackathon.Restaurant.Service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hackathon.Restaurant.DTO.Category;
import com.hackathon.Restaurant.DTO.Item;
import com.hackathon.Restaurant.Repository.ItemRepository;
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
	
	@Mock 
	ItemRepository itemRepo;
	
	@InjectMocks
	ItemService itemService;
	
	@Test
	public void fetchAllItemsTest() {
		List<Item> itemList=new ArrayList<>();
		itemList.add(Item.builder().name("idly")
				  .price(250)
				  .category(Category.BreakFast)
				  .description("nice").build());
		Mockito.when(itemRepo.findAll()).thenReturn(itemList);
		List<Item> resultList=itemService.fetchAllItems();
		Assertions.assertEquals(resultList.size(), itemList.size());
	}
	@Test
	public void createUserTest() {
		Item item=Item.builder().name("idly")
				  .price(250)
				  .category(Category.BreakFast)
				  .description("nice").build();
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		Item res= itemService.saveItem(item);
		Assertions.assertEquals(item,res);
	}
}
