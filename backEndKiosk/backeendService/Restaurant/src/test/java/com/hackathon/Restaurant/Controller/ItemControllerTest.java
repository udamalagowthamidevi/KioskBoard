package com.hackathon.Restaurant.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hackathon.Restaurant.Auth.JwtHelper;
import com.hackathon.Restaurant.DTO.Category;
import com.hackathon.Restaurant.DTO.Item;
import com.hackathon.Restaurant.Service.ItemService;

@WebMvcTest(controllers = MenuController.class)
public class ItemControllerTest {
	
	@MockBean
	ItemService itemService;
	
	
	
	static Item item;
	static List<Item> itemList;
	
	
	
//	@MockBean
//    JwtHelper jwtHelper; 
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@BeforeEach
	public void setup() {
	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	
	@BeforeAll
    public static void setUp() {
        item = Item.builder()
                .name("idly")
                .price(250)
                .category(Category.BreakFast)
                .description("nice")
                .build();

        itemList = new ArrayList<>();
        itemList.add(item);
    }
	
	@Test
	public void fetchAllitemTest() throws Exception {
		Mockito.when(itemService.fetchAllItems()).thenReturn(itemList);
		mockMvc.perform(get("/menu/fetchItems")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("idly"))
                .andExpect(jsonPath("$[0].price").value(250))
                .andExpect(jsonPath("$[0].category").value("BreakFast"))
                .andDo(print());
	}
	
	@Test
	public void AddItem() throws Exception{
		Mockito.when(itemService.saveItem(item)).thenReturn(item);
		mockMvc.perform(post("/menu/addItem",item).contentType("application/json")).
		andExpect(status().isCreated()).andDo(print());
	}

}
