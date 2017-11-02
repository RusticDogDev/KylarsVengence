package com.cit.kv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cit.kv.service.LoginService;
import com.cit.kv.service.MenuService;
import com.cit.kv.service.NewUserService;

@EnableAutoConfiguration
@SpringBootApplication

public class KylarsVengeanceApplication implements CommandLineRunner{
	
	@Autowired
	private LoginService login;
	@Autowired
	private NewUserService newUser;		
	@Autowired
	private MenuService menuService;	
	private Boolean isNewUser;
	
	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(KylarsVengeanceApplication.class);       
        app.run(args);		
	}		      	

	@Override
	public void run(String... args) throws Exception {
		String[] userLoggedIn = login.getlogindetails();		
		isNewUser = Boolean.valueOf(userLoggedIn[2]);	
		if(isNewUser)
		{
			newUser.runNewUser(userLoggedIn[0]);
		}
		menuService.runMenu(userLoggedIn[0]);
	}
}