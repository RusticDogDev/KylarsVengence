package com.cit.kv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cit.kv.service.Login;

@EnableAutoConfiguration
@SpringBootApplication

public class KylarsVengeanceApplication implements CommandLineRunner{
	@Autowired
	private Login login;
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(KylarsVengeanceApplication.class);       
        app.run(args);		
	}		      	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] myStringArray = new String[2];
		myStringArray = login.getlogindetails();
		System.out.println("Stuff:" + myStringArray[0]);
	}
}
