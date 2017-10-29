package com.cit.kv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.repository.ItemRepository;
import com.cit.kv.repository.ItemsOwnedRepository;
import com.cit.kv.repository.UserRepository;

@Service
public class MenuService {
	@Autowired	
	UserRepository userRepo;
	@Autowired	
	ItemRepository itemRepo;
	@Autowired	
	ItemsOwnedRepository ownedRepo;
	public String []  runmenu(String userName, Boolean isNewUser) {
		
		return null;		
	}
}
