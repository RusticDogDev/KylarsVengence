package com.cit.kv.service;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class Login {
	private String uName;
	private String pword;
	
	public String []  getlogindetails () {
		Scanner keyboard = new Scanner(System.in);
		String[] myStringArray = new String[2];
		System.out.println("Please eneter your user name");
		uName = keyboard.nextLine();
		
		System.out.println("Please eneter your password");
		pword = keyboard.nextLine();
		
		myStringArray[0] = uName;
		myStringArray[1] = pword;
		keyboard.close();
		
		return myStringArray;
	}
}
