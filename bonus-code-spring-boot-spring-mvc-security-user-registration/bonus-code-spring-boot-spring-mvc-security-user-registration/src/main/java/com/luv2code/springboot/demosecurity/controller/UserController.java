package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.dao.*;
import com.luv2code.springboot.demosecurity.entity.Contact;
import com.luv2code.springboot.demosecurity.entity.User;
import com.luv2code.springboot.demosecurity.entity.Wishlist;
import com.luv2code.springboot.demosecurity.entity.blog;
import com.luv2code.springboot.demosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;
    UserDao userDao;
    UserRepository userRepository;
    BlogRepository blogRepository;
    ContactRepository contactRepository;
    WishRepository wishRepository;
    public UserController(UserService userService,UserDao userDao,UserRepository userRepository,BlogRepository blogRepository,ContactRepository contactRepository,WishRepository wishRepository){
        this.userService=userService;
        this.userDao=userDao;
        this.userRepository=userRepository;
        this.blogRepository=blogRepository;
        this.contactRepository=contactRepository;
        this.wishRepository=wishRepository;
    }
    @GetMapping("/profile")
    public String employeeUpdate(@RequestParam("username1") String name, Model theModel){
        User theUser= userDao.findByUserName(name);
        theModel.addAttribute("user",theUser);
        return "users/user-form";
    }
    @GetMapping("/update")
    public String updateUser(@RequestParam("userid") int id,Model theModel){
        User theUser=userService.findById(id);
        theModel.addAttribute("user",theUser);
        return "users/update-form";
    }

    @GetMapping("/showAddForm")
    public String addUser(Model theModel){
        User theUser=new User();
        theModel.addAttribute("user",theUser);
        return "users/addUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser){
        userDao.save(theUser);
        return "redirect:/";
    }


    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userid") int id){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Remove the user's roles
            user.getRoles().clear();

            // Delete the user
            userRepository.delete(user);
        }
        return "redirect:/admin";
    }
    @GetMapping("/blog-form")
    public String form_blog(Model theModel){
        blog theBlog=new blog();
        theModel.addAttribute("blog",theBlog);
        return "users/blog-form";
    }
    @PostMapping("/save-blog")
    public String saveBlog(@ModelAttribute("theBlog") blog theBlog,@RequestParam("name") String name){
        theBlog.setUsername(name);
        userService.save1(theBlog);
        return "redirect:/";
    }
    @GetMapping("/blogs")
    public String blogs(Model theModel){
        List<blog> theBlogs=userService.findAllblogs();
        // add to the spring model
        theModel.addAttribute("allblogs", theBlogs);
        return "users/blog";
    }

    @GetMapping("/blogdetails")
    public String detailblog(@RequestParam("blogid") int id,Model theModel){
        blog theBlog=userService.findByblogId(id);
        theModel.addAttribute("detailblog",theBlog);
        return "users/detailblog";
    }

    @GetMapping("/deleteblog")
    public String deleteblog(@RequestParam("blogid") int id){
        Optional<blog> blogOptional = blogRepository.findById(id);
        blog theBlog=blogOptional.get();
        blogRepository.delete(theBlog);
        return "redirect:/admin";

    }
    @PostMapping("/saveform")
    public String contactForm(@ModelAttribute("theForm") Contact theForm){
        contactRepository.save(theForm);
        return "redirect:contact";

    }
    @GetMapping("/contact")
    public String form_contact(Model theModel){
        Contact form=new Contact();
        theModel.addAttribute("form",form);
        return "users/contact_us";
    }

    @GetMapping("/deletefeed")
    public String deleteFeed(@RequestParam("feedid") int id){
        Optional<Contact> feedOptional = contactRepository.findById(id);
        Contact theFeed=feedOptional.get();
        contactRepository.delete(theFeed);
        return "redirect:/admin";
    }

    @GetMapping("/foodblogs")
    public String foodBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findFoodCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/food";
    }
    @GetMapping("/showsblogs")
    public String showsBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findShowsCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/Shows";
    }

    @GetMapping("/technologyblogs")
    public String techBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findTechnologyCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/Technology";
    }

    @GetMapping("/sportsblogs")
    public String sportsBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findSportsCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/Sports";
    }

    @GetMapping("/gadgetsblogs")
    public String gadgetsBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findGadgetsCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/Gadgets";
    }

    @GetMapping("/moviesblogs")
    public String moviesBlogs(Model theModel){
        List<blog> theblogs=blogRepository.findMoviesCategories();
        theModel.addAttribute("allblogs",theblogs);
        return "users/Movies";
    }

    @GetMapping("/wishlist")
    public String wish(@RequestParam("blogid") int id,@RequestParam("username1") String name){
        Optional<blog> wishOptional=blogRepository.findById(id);
        blog thewish=wishOptional.get();
        Wishlist w=new Wishlist();
        w.setName(name);
        w.setCategory(thewish.getCategory());
        w.setTitle(thewish.getTitle());
        w.setContent(thewish.getContent());
        w.setImage(thewish.getImage());
        w.setBid(thewish.getId());
        wishRepository.save(w);
        return "redirect:/";
    }
    @GetMapping("/wishlists")
    public String wishlist(@RequestParam("username1") String name,Model theModel){
            List<Wishlist> w=wishRepository.findByname(name);
            theModel.addAttribute("wish",w);
            return "users/wishlist";
    }

    @GetMapping("/deletewish")
    public String deletewish(@RequestParam("id") int id,@RequestParam("username1") String username1){
        wishRepository.deleteById(id);
        String name=username1;
        return "redirect:/user/wishlists?username1="+name;
    }

    @GetMapping("/deletewishlists")
    public String deletewish(@RequestParam("id") int id){
        wishRepository.deleteById(id);
        return "redirect:/admin";
    }

}
