package com.cit.kv.service;

import java.util.ArrayList;
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
	private List<Item> itemsList = new ArrayList<Item>();
	private int moveCount;
	private boolean isInRound;
	private int input;
	private boolean isInputValid;	
	
	public void runMenu(String userName) throws InterruptedException {		
		while(true)
		{			
			showMenu();
			hudService.runHud(userName);						
			player = userRepo.findOneByUserName(userName);	
			ownedList = ownedRepo.findAllByUser(player.getId());						
			for(int i = 0; i < ownedList.size(); i++)
			{
				item = itemRepo.findOne(ownedList.get(i).getItemId());
				itemsList.add(item);
			}
			if(input == 1)
			{
				this.round(player);				
			}
			else if(input == 2)
			{
				this.buyEquipment();
				moveCount ++;
			}
			else if(input == 3)
			{
				this.sellEquipment();
				moveCount ++;
			}
			else
			{
				this.upgradeEquipment();
				moveCount ++;
			}		
		}		
	}
	public int showMenu() {
		final int MIN = 1;
		final int MAX = 4;	
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
			long addedBalance = 2 * player.getLevel();
			player.setLevel(level);
			player.setBalance(addedBalance + player.getLevel());
			System.out.println("You have fought well " + 
								player.getUserName() +
								" You have leveled up to Level" + 
								player.getLevel() + "!/nYou've earned " + addedBalance +" Kubits");
		}
	}
	private void buyEquipment() {
				
	}
	private void sellEquipment() {
		
		
	}
	private void upgradeEquipment() {			
		int cost = 5;
		int attdef = 3;
		int choice;
		final int MIN = 1;
		final int MAX = 5;	
		boolean validChoice = false;
		String equipment = " Items available to ugrade:";
		Scanner keyboard = new Scanner(System.in);
		while(!validChoice)
		{
			for(int i = 0; i < itemsList.size(); i++ ){
				equipment += "("+ i +") Type: " + itemsList.get(i).getItemType() + " Level: " + itemsList.get(i).getItemLevel()
						+ " Damage: " + itemsList.get(i).getAttack() + " Defence: " + itemsList.get(i).getDefence() + " Kubits needed to upgrade " + cost * itemsList.get(i).getItemLevel() +" \n";
			}							
			System.out.println(equipment + " (5) Exit\n");
			choice = keyboard.nextInt();
			if(choice >= MIN && choice <= MAX ){				
				cost = cost * itemsList.get(choice).getItemLevel();	
				attdef = attdef * itemsList.get(choice).getItemLevel();	
				if(player.getBalance() > cost)
				{
					player.setBalance(player.getBalance() - cost);
					item = itemsList.get(choice);
					item.setItemLevel(item.getItemLevel() + 1);
					item.setAttack(item.getAttack() + attdef);
					item.setDefence(item.getDefence() + attdef);					
					System.out.println("Thnak you my friend i have upgraded your item");
					validChoice = true;
				}
				else{
					System.out.println("You do not have enough kubits for this upgrade my friend, either fight more rounds or visit the exchange to transfer your cash into kubits \n");					
				}			
			}
			else{
				System.out.println("Error: Please select the items available to upgrade");
			}			
		}							
	}
}
