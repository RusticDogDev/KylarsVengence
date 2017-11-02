package com.cit.kv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.Item;
import com.cit.kv.domain.Item.ItemType;
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
	private List<Item> itemsList;	
	private int moveCount;
	private boolean isInRound;
	private int input;	
	
	public void runMenu(String userName) throws InterruptedException {		
		while(true)
		{			
			hudService.runHud(userName);	
			showMenu();							
			
			player = userRepo.findOneByUserName(userName);				
			ownedList = ownedRepo.findAllByUser(player.getId());
			itemsList = new ArrayList<Item>();			
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
			}
			else if(input == 3)
			{
				this.sellEquipment();				
			}
			else if(input == 4)
			{
				this.upgradeEquipment();				
			}		
			input = 0;
		}		
	}
	public int showMenu() {
		boolean isInputValid = false;	
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
			if(input >= MIN && 	input <= MAX && moveCount < 1)
			{
				isInputValid = true;
			}
			else if(input >= MIN && input <= MAX && input == 1)
			{
				isInputValid = true;	
			}	
			else
			{
				if(moveCount == 1)
				{					
					System.out.println("\n** You are out of time, you must finish the round (option 1) and fight **\n");
				}
				else{
					System.out.println("Please select a valid option between 1 and 4");
				}				
			}			
		}
		return input;
	}
	
	private void round(User player) throws InterruptedException {		
		if(!isInRound)			
		{
			isInRound = true;
			System.out.println("\n** You've begun the round, fight well, if you're weapons are not up to scratch you can upgrade them during the intermission **\n");
		}
		else{
			isInRound = false;
			int level = player.getLevel() +1;
			long addedBalance = 2 * player.getLevel() + 2;
			player.setLevel(level);			
			player.setBalance(addedBalance + player.getBalance());
			userRepo.update(player);
			System.out.println("\n** You have fought well " + 
								player.getUserName() +
								" You have leveled up to Level " + 
								player.getLevel() + " !! **\n** You've earned " + addedBalance +" Kubits **\n");			
		}
		Thread.sleep(3000);
	}
	private void buyEquipment() throws InterruptedException {
		int choice;
		long cost = 0;
		boolean validChoice = false;					
		boolean hasLongRange= false;
		boolean hasCloseRange = false;
		boolean hasArmour = false;
		boolean hasShield = false;
		List<Item> canSell = new ArrayList<Item>();			
		final int MIN = 0;	
		
		
		Scanner keyboard = new Scanner(System.in);
		while(!validChoice)
		{			
			if(itemsList.size() > 4)
			{
				System.out.println("\nHey.. you have no space to buy more items, sell an item to make room for another ..\n");
				break;
			}												
				for(int i =0; i < itemsList.size(); i++)
				{				
					if(itemsList.get(i).getItemType().equals(ItemType.CloseRange.toString()))
					{
						hasCloseRange = true;
					}
					else if(itemsList.get(i).getItemType().equals(ItemType.LongRange.toString()))
					{
						hasLongRange = true;
					}
					else if(itemsList.get(i).getItemType().equals(ItemType.Armour.toString()))
					{
						hasArmour = true;
					}					
					else if(itemsList.get(i).getItemType().equals(ItemType.Shield.toString()))
					{
						hasShield = true;
					}					
				}
			if(!hasCloseRange)
			{		
				canSell.addAll(itemRepo.findAllByItemType(ItemType.CloseRange.toString()));
			}
			else if(!hasLongRange){
				canSell.addAll(itemRepo.findAllByItemType(ItemType.LongRange.toString()));
			}
			else if(!hasArmour){
				canSell.addAll(itemRepo.findAllByItemType(ItemType.Armour.toString()));
			}
			else if(!hasShield){
				canSell.addAll(itemRepo.findAllByItemType(ItemType.Shield.toString()));
			}
			if(canSell.size() == 0)
			{
				System.out.println("\nSorry we currently have no items to trade with come back another time....\n");
				break;
			}	
			int max = canSell.size();
			String equipment = "";			
			for(int i = 0; i < canSell.size(); i++ )
			{
				int count = i + 1;
				equipment += "("+ count +") Type: " + canSell.get(i).getItemType() + " || Level: " + canSell.get(i).getItemLevel()
						+ " || Damage: " + canSell.get(i).getAttack() + " || Defence: " + canSell.get(i).getDefence() + " || Name: " + canSell.get(i).getItemName() + " || Kubits needed to buy = " + canSell.get(i).getItemValue() +" \n";
			}							
			System.out.println( "Items available to ugrade:\n(0) Exit\n" + equipment);
			choice = keyboard.nextInt();
			if(choice >= MIN && choice <= max )
			{
				cost = canSell.get(choice - 1).getItemValue();
				if(player.getBalance() > cost)
				{
					item = canSell.get(choice - 1);
					ItemsOwned itemOwned = new ItemsOwned(player.getId(), item.getItemId());
					ownedRepo.save(itemOwned);
					player.setBalance(player.getBalance() - cost);
					userRepo.update(player);
					System.out.println("\nA fine choice, Keep it sharp for Kylars throat!!\n" + "== " + cost + " Kubits Removed ==\n");						
					moveCount ++;
					validChoice = true;	
				}
				else
				{
					System.out.println("\nYou do not have enough kubits for this purchase, either fight more rounds or visit the exchange to transfer your cash into kubits \n");					
				}	
			}
			else
			{
				System.out.println("\nError: Please select the items available to buy\n");
			}	
		}		
		Thread.sleep(3000);
	}
	
	
	private void sellEquipment() throws InterruptedException {
		int choice;
		long saleKubits;
		final int MIN = 0;
		final int MAX = 4;		
		boolean validChoice = false;	
		Scanner keyboard = new Scanner(System.in);
		while(!validChoice)
		{
			if(itemsList.size() == 0)
			{
				System.out.println("\nHey.. you have no items to sell!..\n");					
				break;							
			}
			String equipment = " Items available to sell:\n";			
			for(int i = 0; i < itemsList.size(); i++ )
			{
				equipment += "("+ i +") Type: " + itemsList.get(i).getItemType() + " || Level: " + itemsList.get(i).getItemLevel()
						+ " || Damage: " + itemsList.get(i).getAttack() + " || Defence: " + itemsList.get(i).getDefence() + " || Name: " + itemsList.get(i).getItemName() + " || Value of sale: " + itemsList.get(i).getItemValue() + " kubits\n";
			}							
			System.out.println(equipment + "(4) Exit\n");
			choice = keyboard.nextInt();
			if(choice >= MIN && choice <= MAX )
			{
				if(choice == 4)
				{
					validChoice = true;	
					System.out.println("\nSee ya!! ..you better sell me something next time...\n");	
					Thread.sleep(3000);
				}
				else{
					item = itemsList.get(choice);
					saleKubits = item.getItemValue();
					player.setBalance(player.getBalance() + saleKubits);
					ItemsOwned itemOwned = new ItemsOwned(player.getId(), item.getItemId());
					ownedRepo.deleteOne(itemOwned);
					userRepo.update(player);
					System.out.println("\nGood trade!! Here are your Kubits..\n" + "== " + saleKubits + " Recieved ==\n");	
					moveCount ++;
					validChoice = true;	
				}
			}			
			else
			{
				System.out.println("\nError: Please select the items available to sell\n");
			}	
		}
		Thread.sleep(3000);			
	}
	
	private void upgradeEquipment() throws InterruptedException {			
		int cost = 5;
		int attdef = 3;
		int choice;
		final int MIN = 0;
		final int MAX = 4;	
		boolean validChoice = false;		
		Scanner keyboard = new Scanner(System.in);
		while(!validChoice)
		{
			if(itemsList.size() == 0)
			{
				System.out.println("\nYou fool.. you have no items to upgrade!..\n");					
				break;							
			}
			String equipment = " Items available to ugrade:\n";			
			for(int i = 0; i < itemsList.size(); i++ )
			{
				equipment += "("+ i +") Type: " + itemsList.get(i).getItemType() + " || Level: " + itemsList.get(i).getItemLevel()
						+ " || Damage: " + itemsList.get(i).getAttack() + " || Defence: " + itemsList.get(i).getDefence() + " || Name: " + itemsList.get(i).getItemName() + " || Kubits needed to upgrade = " + cost * itemsList.get(i).getItemLevel() +" \n";
			}							
			System.out.println(equipment + "(4) Exit\n");
			choice = keyboard.nextInt();
			if(choice >= MIN && choice <= MAX )
			{																
				cost = cost * itemsList.get(choice).getItemLevel();	
				attdef = attdef * itemsList.get(choice).getItemLevel();
				if(player.getBalance() > cost && choice != 4)
				{					
					player.setBalance(player.getBalance() - cost);
					item = itemsList.get(choice);
					item.setItemLevel(item.getItemLevel() + 1);
					item.setAttack(item.getAttack() + attdef);
					item.setDefence(item.getDefence() + attdef);
					item.setItemValue(item.getItemValue() + 2 * item.getItemLevel() );
					itemRepo.update(item);
					userRepo.update(player);
					System.out.println("== "+ cost +" Kubits removed ==\n\n"+"Thank you my friend I have upgraded your item\n");					
					validChoice = true;					
					moveCount ++;					
				}
				else if(choice == 4)
				{
					System.out.println("\nGoodbye friend!! ..Don't waste my time again...\n");
					validChoice = true;		
				}
				else
				{
					System.out.println("\nYou do not have enough kubits for this upgrade my friend, either fight more rounds or visit the exchange to transfer your cash into kubits \n");					
				}			
			}
			else
			{
				System.out.println("\nError: Please select the items available to upgrade\n");
			}						
		}							
		Thread.sleep(3000);
	}
}
