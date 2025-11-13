package com.hackathon.Restaurant.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.hackathon.Restaurant.DTO.Order;

@Controller
public class MessageController {
	
	@MessageMapping("/update")
	@SendTo("/statusUpdate")
	public String statusUpdate(Order order) {
		return order.getId()+" - "+order.getStatus();
	}

}
