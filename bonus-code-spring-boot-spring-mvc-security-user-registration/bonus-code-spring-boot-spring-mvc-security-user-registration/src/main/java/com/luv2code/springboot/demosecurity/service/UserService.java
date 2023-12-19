package com.luv2code.springboot.demosecurity.service;

import com.luv2code.springboot.demosecurity.entity.User;
import com.luv2code.springboot.demosecurity.entity.Wishlist;
import com.luv2code.springboot.demosecurity.entity.blog;
import com.luv2code.springboot.demosecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);
	User findById(int theId);

	void save(User theUser);
	List<User> findAll();

	void save1(blog Userblog);

	List<blog> findAllblogs();

	blog findByblogId(int theId);



}
