package com.cit.kv.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.Item;
import com.cit.kv.domain.ItemsOwned;
import com.cit.kv.domain.User;
import com.cit.kv.repository.ItemRepository;
import com.cit.kv.repository.ItemsOwnedRepository;
import com.cit.kv.repository.UserRepository;

@Service
public class MenuService 
{
	@Autowired	
	UserRepository userRepo;
	@Autowired	
	ItemRepository itemRepo;
	@Autowired	
	ItemsOwnedRepository ownedRepo;
	@Autowired
	private HudService hudService;
	
	private User player;
	private Item item;	
	private List<ItemsOwned> ownedList;
	private int moveCount;
	private boolean isInRound;
	private int input;
	private boolean isInputValid;
	private final int MIN = 1;
	private final int MAX = 4;	
	
	public void runMenu(String userName) throws InterruptedException {		
		while(true)
		{			
			showMenu();
			hudService.runHud(userName);			
			player = userRepo.findOneByUserName(userName);				
			if(input == 1)
			{
				this.round(player);				
			}
			else if(input == 2)
			{
				this.buyEquipment(player);
				moveCount ++;
			}
			else if(input == 3)
			{
				this.sellEquipment(player);
				moveCount ++;
			}
			else
			{
				this.upgradeEquipment(player);
				moveCount ++;
			}		
		}		
	}
	public int showMenu() {
		Scanner keyboard = new Scanner(System.in);	
		while(!isInputValid)
		{
			System.out.println("Main Menu");
			if(!isInRound)
			{
				moveCount = 0;
				System.out.println("1. Start Round \n2. Buy new equipment \n3. Sell equipment \n4. Upgrade equipment");
			}
			else{
				System.out.println("1. Finish Round \n2. Buy new equipment \n3. Sell equipment \n4. Upgrade equipment");
			}
			input = keyboard.nextInt();
			if(input >= MIN && 	input <= MAX && moveCount <= 1)
			{
				isInputValid = true;
			}
			else
			{
				if(moveCount > 1)
				{					
					System.out.println("You are out of time, you must finish the round (option 1) and fight");
				}
				else{
					System.out.println("Please select a valid option between 1 and 4");
				}				
			}			
		}
		return input;
	}
	
	private void round(User player) {
		if(!isInRound)
		{
			isInRound = true;
		}
		else{
			isInRound = false;
			int level = player.getLevel() +1;
			long addedBalance = 5 * player.getLevel();
			player.setLevel(level);
			player.setLevel(level);
			System.out.println("You have fought well " + 
								player.getUserName() +
								" You have leveled up to Level" + 
								player.getLevel() + "!/nYou've earned " + addedBalance +" Kubits");
		}
	}
	private void buyEquipment(User player) {
		ownedList = ownedRepo.findAllByUser(player.getId());
		
	}
	private void sellEquipment(User player) {
		ownedList = ownedRepo.findAllByUser(player.getId());
		
	}
	private void upgradeEquipment(User player) {
		ownedList = ownedRepo.findAllByUser(player.getId());		
	}
}
