package com.hackathon.Restaurant.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hackathon.Restaurant.DTO.Order;
import com.hackathon.Restaurant.DTO.Status;
import com.hackathon.Restaurant.Repository.OrderRepository;

@Service
public class AsyncOrderServiceTaskExecutor {
	
	
	Queue<Order> foodOrder;
	ThreadPoolExecutor taskExec;
	
	@Autowired
	OrderRepository orderRepo;
	@Autowired
    private SimpMessagingTemplate mssgTemplate;
	public AsyncOrderServiceTaskExecutor() {
		foodOrder=new LinkedList<>();
		taskExec=new ThreadPoolExecutor(2,4,60,TimeUnit.SECONDS,new LinkedBlockingQueue<>(4),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	}
	
	@Async
	public void exceuteTask(Order order) {
		while(!foodOrder.isEmpty()) {
			taskExec.submit(()->processOrder(foodOrder.poll()));
		}
		taskExec.submit(()->processOrder(orderRepo.save(order)));
		
	}
	private void processOrder(Order order) {
        try {
            System.out.println(Thread.currentThread().getName() + " processing order: " + order);
            Thread.sleep(2000); // simulate cooking
            System.out.println("✅ Order completed: " + order.getId());
            order.setStatus(Status.Ready);
            orderRepo.save(order);
            mssgTemplate.convertAndSend("/statusUpdate", " "+order.getId()+" is ready.");   
        } catch (InterruptedException e) {
        	System.out.println("error");
        	foodOrder.offer(order);
            Thread.currentThread().interrupt();
        }
    }

}
