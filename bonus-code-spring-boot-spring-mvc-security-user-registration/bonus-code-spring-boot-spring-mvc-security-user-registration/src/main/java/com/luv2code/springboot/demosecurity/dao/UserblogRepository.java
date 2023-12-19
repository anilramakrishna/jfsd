package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserblogRepository extends JpaRepository<blog,Integer> {
}
