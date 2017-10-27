package com.cit.kv.repository;

import java.util.List;

import com.cit.kv.domain.Item;

public interface itemRepository {
	List<Item> findAll();
	Item findOne(Long itemId);
	Item save (Item ur);
	int update(Item ur);
	int deleteOne(Long itemId);
}
