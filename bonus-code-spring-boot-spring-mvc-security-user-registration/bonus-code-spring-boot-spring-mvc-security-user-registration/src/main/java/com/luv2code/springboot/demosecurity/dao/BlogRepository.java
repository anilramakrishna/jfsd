package com.luv2code.springboot.demosecurity.dao;

import com.luv2code.springboot.demosecurity.entity.User;
import com.luv2code.springboot.demosecurity.entity.blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<blog,Integer> {
    @Query("SELECT t FROM blog t WHERE t.category = 'food'")
    List<blog> findFoodCategories();
    @Query("SELECT t FROM blog t WHERE t.category = 'tv_shows'")
    List<blog> findShowsCategories();

    @Query("SELECT t FROM blog t WHERE t.category = 'movies'")
    List<blog> findMoviesCategories();

    @Query("SELECT t FROM blog t WHERE t.category = 'sports'")
    List<blog> findSportsCategories();

    @Query("SELECT t FROM blog t WHERE t.category = 'gadgets'")
    List<blog> findGadgetsCategories();

    @Query("SELECT t FROM blog t WHERE t.category = 'technology'")
    List<blog> findTechnologyCategories();
}
