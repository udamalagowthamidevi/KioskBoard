package com.hackathon.Restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.Restaurant.DTO.Order;
import com.hackathon.Restaurant.DTO.OrderDTO;
import com.hackathon.Restaurant.DTO.Status;
import com.hackathon.Restaurant.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	
	@PostMapping("/createOrder")
	public Order createOrder(@RequestBody OrderDTO order) {
		return orderService.createOrder(order);
	}
	
	@PutMapping("/updateOrder")
	public Order updateOrder(@RequestParam int orderId,@RequestParam Status status) {
		
		return null;
	}
	
	@GetMapping("/getOrder")
	public Order getOrderById(@RequestParam int orderId) {
		return null;
	}

}
