package com.luv2code.springboot.demosecurity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blog")
public class blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;

    public blog(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public blog(String username, String title, String category, String image, String content) {
        this.username = username;
        this.title = title;
        this.category = category;
        this.image = image;
        this.content = content;
    }

    @Override
    public String toString() {
        return "blog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
