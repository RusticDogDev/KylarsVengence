package com.cit.kv.repository;

import java.util.List;

import com.cit.kv.domain.User;

public interface UserRepository {
	List<User> findAll();
	User findOne(Long id);
	User findOneByUserName(String userName);
	User save (User ur);
	int update(User ur);
	int deleteOne(Long id);	
}
