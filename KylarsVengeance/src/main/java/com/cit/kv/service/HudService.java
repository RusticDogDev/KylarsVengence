package com.cit.kv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.Item;
import com.cit.kv.domain.ItemsOwned;
import com.cit.kv.domain.User;
import com.cit.kv.repository.ItemRepository;
import com.cit.kv.repository.ItemsOwnedRepository;
import com.cit.kv.repository.UserRepository;
@Service
public class HudService {
	@Autowired	
	UserRepository userRepo;
	@Autowired	
	ItemRepository itemRepo;
	@Autowired	
	ItemsOwnedRepository ownedRepo;
	private User player;
	private Item item;	
	private List<ItemsOwned> ownedList;
	private String Equipment;
	
	public void runHud(String userName) throws InterruptedException {
		player = userRepo.findOneByUserName(userName);
		ownedList = ownedRepo.findAllByUser(player.getId());
		List<Item> itemsList = new ArrayList<Item>();
		for(int i = 0; i < ownedList.size(); i++)
		{
			item = itemRepo.findOne(ownedList.get(i).getItemId());
			itemsList.add(item);
		}
		System.out.println("--- KYLAR’S VENGEANCE ---");
		System.out.println("Player: \t"+ player.getUserName() + " the " + player.getUserType());
		System.out.println("Level: \t\t"+ player.getLevel());
		System.out.println("Balance: \t"+ player.getBalance());
		Equipment = "Equipment: \t";
		for(int i = 0; i < itemsList.size(); i++){
			if(i == 0){
				Equipment += itemsList.get(i).getItemName();
			}
			else{
				Equipment += ", " + itemsList.get(i).getItemName();
			}			
			Equipment += " (Level " + itemsList.get(i).getItemLevel() + ")";
		}
		System.out.println(Equipment + "\n");
	}
}