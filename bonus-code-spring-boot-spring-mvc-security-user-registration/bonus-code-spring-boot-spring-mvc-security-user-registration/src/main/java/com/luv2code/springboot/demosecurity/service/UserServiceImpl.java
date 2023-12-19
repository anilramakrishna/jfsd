package com.luv2code.springboot.demosecurity.service;

import com.luv2code.springboot.demosecurity.dao.*;
import com.luv2code.springboot.demosecurity.entity.Role;
import com.luv2code.springboot.demosecurity.entity.User;
import com.luv2code.springboot.demosecurity.entity.blog;
import com.luv2code.springboot.demosecurity.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	private UserblogRepository userblogRepository;
	private UserRepository userRepository;
	private UserDao userDao;

	private RoleDao roleDao;

	private BlogRepository blogRepository;

	private BCryptPasswordEncoder passwordEncoder;

	private WishRepository wishRepository;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder,UserRepository userRepository,UserblogRepository userblogRepository,BlogRepository blogRepository,WishRepository wishRepository) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
		this.userRepository=userRepository;
		this.userblogRepository=userblogRepository;
		this.blogRepository=blogRepository;
		this.wishRepository=wishRepository;
	}

	@Override
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	public void save(WebUser webUser) {
		User user = new User();

		// assign user details to the user object
		user.setUserName(webUser.getUserName());
		user.setPassword(passwordEncoder.encode(webUser.getPassword()));
		user.setFirstName(webUser.getFirstName());
		user.setLastName(webUser.getLastName());
		user.setEmail(webUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		// save user in the database
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				authorities);
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);

		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		userRepository.save(theUser);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void save1(blog Userblog) {
		userblogRepository.save(Userblog);
	}

	@Override
	public List<blog> findAllblogs() {
		return userblogRepository.findAll();
	}

	@Override
	public blog findByblogId(int theId) {
		Optional<blog> result = blogRepository.findById(theId);
		blog theBlog=result.get();
		return theBlog;
	}



}
