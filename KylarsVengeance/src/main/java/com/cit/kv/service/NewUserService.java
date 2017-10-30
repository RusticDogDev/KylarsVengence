package com.cit.kv.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.Item.ItemType;
import com.cit.kv.domain.ItemsOwned;
import com.cit.kv.domain.User;
import com.cit.kv.domain.User.UserType;
import com.cit.kv.repository.ItemRepository;
import com.cit.kv.repository.ItemsOwnedRepository;
import com.cit.kv.repository.UserRepository;

@Service
public class NewUserService {
	@Autowired	
	UserRepository userRepo;
	@Autowired	
	ItemRepository itemRepo;
	@Autowired	
	ItemsOwnedRepository ownedRepo;
	private User player;
	private String typeList;
	private boolean validInput; 
	private int count;
	private long itemId;
	private int input;	
	private final int MAX = 6;
	private final int MIN = 1;
	
	public void  runNewUser(String userName) throws InterruptedException {
		
		player = userRepo.findOneByUserName(userName);
		Scanner keyboard = new Scanner(System.in);		
		System.out.println("** Welcome Newcomer to Kylars Vengeance **\n");
		Thread.sleep(1000);
		System.out.println("** The once entombed Kylar has risen from the dead.. **\n");
		Thread.sleep(2000);
		System.out.println("** He and his band of marauders now aim their swords and arrows at our homeland  **\n");
		Thread.sleep(3000);
		System.out.println("** You must help us " + player.getUserName() +" and defeat that yaldson Kylar **\n");
		Thread.sleep(3000);
		System.out.println("** Now tell me " + player.getUserName() + " what type of warrior are you?... **\n");
		Thread.sleep(3000);
		while(!validInput)
		{							
			for(UserType type : UserType.values())
			{
				count ++;
				String strcount = Integer.toString(count);
				if(count == 1){
					typeList = type.toString() + ": \t\t" + strcount + "* \n";
				}
				else
				{
					typeList += type.toString() + ": \t\t" + strcount + "* \n";
				}				
			}			
			typeList = typeList.substring(0, typeList.length() - 2);
			System.out.println(typeList);									
			input = keyboard.nextInt();
			if(input <= MAX && input >= MIN)
			{
				validInput = true;
			}
			else{
				System.out.println("** It seem's you're not the brightest warrior, try again... **\n");
				Thread.sleep(2000);
			}
		}			
		input = input -1;
		player.setUserType(UserType.values()[input].toString());
		userRepo.update(player);
		System.out.println("** Ahh I should've guessed " + player.getUserName() + " you have the look of a " + player.getUserType() + "  **\n");
		Thread.sleep(2000);
		System.out.println("** Since you are willing to help us I shall grant you my spare equipment for free **\n");
		Thread.sleep(1000);		
		itemId = 1;
		for(ItemType type : ItemType.values())
		{																			
			ItemsOwned owned = new ItemsOwned(player.getId(), itemId);
			ownedRepo.save(owned);
			itemId++;
			System.out.println("== " + type.toString() + " Item Added ==\n");
			if(itemId == 4)
			{
				break;
			}
		}			
		System.out.println("** They're not the best equipment one could have but it's better than nothing **\n");
		Thread.sleep(2000);
		System.out.println("** You can buy and upgrade your equipment in the market if you have the kubits **\n");
		Thread.sleep(2000);
		System.out.println("** Fairwell " + player.getUserName() + " the " + player.getUserType() + ".. I'm sure you'll do us proud!! **\n");
		keyboard.close();
	}			
}
