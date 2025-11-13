package com.hackathon.Restaurant.DTO;

import java.util.*;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
	
	Map<Integer,Integer> itemMap=new HashMap<>();
	Status status;
	Type orderType;

}
