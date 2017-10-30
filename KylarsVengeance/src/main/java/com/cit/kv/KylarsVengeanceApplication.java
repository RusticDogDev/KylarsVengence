package com.cit.kv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cit.kv.service.HudService;
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
	private HudService hudService;
	@Autowired
	private MenuService menuService;
	private Boolean isLoggedIn;
	private Boolean isNewUser;
	
	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(KylarsVengeanceApplication.class);       
        app.run(args);		
	}		      	

	@Override
	public void run(String... args) throws Exception {
		String[] userLoggedIn = login.getlogindetails();
		isLoggedIn = true;
		isNewUser = Boolean.valueOf(userLoggedIn[2]);	
		if(isNewUser)
		{
			newUser.runmenu(userLoggedIn[0]);
		}
		hudService.runHud(userLoggedIn[0]);	
	}
}