
package com.hackathon.Restaurant.DTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.*;

@Entity(name="Orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	
	@ElementCollection
    @CollectionTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "item_id")   // key is a foreign key to Item
    @Column(name = "quantity")      
    private Map<Item, Integer> itemList = new HashMap<>();

	@Enumerated(EnumType.STRING)
	Status status;
	
	Date issuedTime;
	
	@Enumerated(EnumType.STRING)
	Type orderType;

}
