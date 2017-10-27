package com.cit.kv.repository;

import java.util.List;

import com.cit.kv.domain.Item;

public interface ItemRepository {
	List<Item> findAll();
	Item findOne(Long itemId);
	Item save (Item it);
	int update(Item it);
	int deleteOne(Long itemId);
}
