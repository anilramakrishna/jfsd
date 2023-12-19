package com.luv2code.springboot.demosecurity.controller;


import com.luv2code.springboot.demosecurity.dao.ContactRepository;
import com.luv2code.springboot.demosecurity.dao.WishRepository;
import com.luv2code.springboot.demosecurity.entity.Contact;
import com.luv2code.springboot.demosecurity.entity.User;
import com.luv2code.springboot.demosecurity.entity.Wishlist;
import com.luv2code.springboot.demosecurity.entity.blog;
import com.luv2code.springboot.demosecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DemoController {

    private UserService userService;
    private ContactRepository contactRepository;

    private WishRepository wishRepository;

    public DemoController(UserService userService,ContactRepository contactRepository,WishRepository wishRepository){

        this.userService=userService;
        this.contactRepository=contactRepository;
        this.wishRepository=wishRepository;
    }

    // add a request mapping for /leaders

    @GetMapping("/admin")
    public String showLeaders(Model theModel) {
        List<User> theUser=userService.findAll();
        List<blog> theBlog=userService.findAllblogs();
        List<Contact> theform=contactRepository.findAll();
        List<Wishlist> thewish=wishRepository.findAll();
        // add to the spring model
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("blog",theBlog);
        theModel.addAttribute("theForm",theform);
        theModel.addAttribute("wish",thewish);

        return "users/list-users";
    }

    // add request mapping for /systems

    @GetMapping("/systems")
    public String showSystems() {

        return "systems";
    }

}









