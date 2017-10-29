package com.cit.kv.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.User;
import com.cit.kv.domain.User.UserType;
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
	private User player;
	private String typeList;
	private int count;
	private int input;
	
	public void  runmenu(String userName, Boolean isNewUser) throws InterruptedException {
		
		player = userRepo.findOneByUserName(userName);
		Scanner keyboard = new Scanner(System.in);		
		if(isNewUser)
		{
			System.out.println("** Welcome Newcomer to Kylars Vengeance **\n");
			Thread.sleep(1000);
			System.out.println("** The once entombed Kylar has risen from the dead.. **\n");
			Thread.sleep(2000);
			System.out.println("** He and his band of marauders now aim their swords and arrows at our homeland  **\n");
			Thread.sleep(3000);
			System.out.println("** You must help us " + player.getUserName() +" and defeat that yaldson Kylar **\n");
			Thread.sleep(3000);
			System.out.println("** Now tell me " + player.getUserName() + " what type of warrior are you? **\n");
			Thread.sleep(2000);
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
			System.out.println(typeList.substring(0, typeList.length() - 2));									
			input = keyboard.nextInt();		
		}			
	}
}
