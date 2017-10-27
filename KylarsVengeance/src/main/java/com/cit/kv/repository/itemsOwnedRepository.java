package com.cit.kv.repository;

import java.util.List;

import com.cit.kv.domain.itemsOwned;

public interface itemsOwnedRepository {
	List<itemsOwned> findAllByUser(Long id);
	List<itemsOwned> findAllByWeapon(Long itemId);
	itemsOwned save(itemsOwned io);
	int deleteOne(itemsOwned io);
}
