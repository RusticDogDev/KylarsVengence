package com.cit.kv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.cit.kv.domain.User;
import com.cit.kv.repository.UserRepository;
@EnableAutoConfiguration
@SpringBootApplication

public class KylarsVengeanceApplication {
	@Autowired
	UserRepository userRepo;
	public static void main(String[] args) {
		SpringApplication.run(KylarsVengeanceApplication.class, args);		
	}
	
	@Component
    public class MyRunner implements CommandLineRunner {       

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Hello World");
		List<User> user =  userRepo.findAll();
		System.out.println("User Name: " + user.get(0).getfName());
	}
   }
}
