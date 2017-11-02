package com.cit.kv.service;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cit.kv.domain.User;
import com.cit.kv.repository.UserRepository;

@Service
public class LoginService {
	
	private int input;
	private String userName;
	private String pword;
	private String[] userDetails = new String[3];
	private List<User> allUsers;
	private boolean valid;
	private boolean inputValid;
	private boolean usernameValid = true;
	private final int MAX = 2;
	private final int MIN = 1;	
	
	@Autowired	
	UserRepository userRepo;
	
	public String []  getlogindetails () {
		Scanner keyboard = new Scanner(System.in);		
		System.out.println(" ===== Welcome to Kylars Vengeance ===== ");
		while (!valid)
		{
			while(!inputValid)
			{
				if(input == 0)
				{
					System.out.println("Do you wish to Login (1) or Sign-up (2)?\n");
					input = keyboard.nextInt();
					if(input >= MIN && input <= MAX)
					{
						inputValid = true;
					}
					else{
						System.out.println("INCORRECT INPUT \n Please enter 1 for Login or 2 for Sign-up\n");
					}
							
				}
			}															
			System.out.println("Please enter your user name (At least 5 characters)");							
			keyboard.nextLine();					
			userName = keyboard.nextLine();	
			System.out.println("Please enter your password (At least 8 characters including one number)");
			pword = keyboard.nextLine();			
			
			allUsers = userRepo.findAll();				
			for(int i =0; i < allUsers.size(); i++)
			{
				if(input == MIN)
				{
					if(userName.equals(allUsers.get(i).getUserName()) && pword.equals(allUsers.get(i).getPassword()))
					{					
						userDetails[0] = userName;
						userDetails[1] = pword;
						userDetails[2] = "false"; //Returning Customer						
						valid = true;
					}			
				}
				else
				{
					if(userName.equals(allUsers.get(i).getUserName()))
					{																	
						usernameValid = false;					
					}
				}
			}						
			if(!usernameValid)
			{
				System.out.println("ERROR: Username already exists\nINFO: Please try again with a different username");
			}	
			else{
				if (userName.length() >= 5 && pword.length() >= 8 && pword.matches(".*\\d.*") )
				{
					System.out.println("\nSuccess: The username '" + userName + "' is available");
					User newUser = new User((long) 2, userName, pword, "", 0, (long)1000);
					userRepo.save(newUser);
					System.out.println("Success: Your account has been created\n ");
					userDetails[0] = userName;
					userDetails[1] = pword;
					userDetails[2] = "true";					
					valid = true;
				}
			}			
			if(!valid)
			{				
				if(input == MAX){
					System.out.println(
						"INFO: Username requires at least 5 characters\nINFO: Password requires at least 8 characters, including one number\n"
					);						
				}
				else{
					System.out.println("ERROR: The username or password did not match\n");						
				}
			}								
		}
		return userDetails;
	}
}
