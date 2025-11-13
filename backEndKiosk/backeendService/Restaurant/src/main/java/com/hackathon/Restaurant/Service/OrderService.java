package com.hackathon.Restaurant.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.Item;
import com.hackathon.Restaurant.DTO.Order;
import com.hackathon.Restaurant.DTO.OrderDTO;
import com.hackathon.Restaurant.DTO.Status;
import com.hackathon.Restaurant.Repository.OrderRepository;

@Service
public class OrderService {
	
	
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ItemService itemService;
	
	
	
	@Autowired
	AsyncOrderServiceTaskExecutor asyncService;
	
	public OrderService() {}
		
	
	public Order createOrder(OrderDTO order) {
		Map<Item,Integer> itemList=new HashMap<>();
		
		for(Entry<Integer,Integer> mp:order.getItemMap().entrySet()) {
			Item item= itemService.fetchItemById(mp.getKey());
			System.out.println("Order "+item.getName());
			itemList.put(item, mp.getValue());
		}
		Order finalObj=new Order().builder().issuedTime(new Date(System.currentTimeMillis())).itemList(itemList).orderType(order.getOrderType()).status(order.getStatus()).build();
		Order res=orderRepo.save(finalObj);
		asyncService.exceuteTask(res);
		return res;
		
	}
	
	
	public void updateOrder(Order orderObj,Status status) {
		Order order=orderRepo.findById(orderObj.getId()).get();
		order.setStatus(status);
		orderRepo.save(order);	
	}
	
	
	
	
	

}

