package com.hackathon.Restaurant.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.Restaurant.DTO.Category;
import com.hackathon.Restaurant.DTO.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByCategory(Category category);
	public Optional<Item> findByName(String name);

}
