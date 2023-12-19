package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
