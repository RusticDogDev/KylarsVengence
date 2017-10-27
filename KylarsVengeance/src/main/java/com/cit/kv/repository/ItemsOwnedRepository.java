package com.cit.kv.repository;

import java.util.List;

import com.cit.kv.domain.ItemsOwned;

public interface ItemsOwnedRepository {
	List<ItemsOwned> findAllByUser(Long id);
	List<ItemsOwned> findAllByItem(Long itemId);
	ItemsOwned save(ItemsOwned io);
	int deleteOne(ItemsOwned io);
}
