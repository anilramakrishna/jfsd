package com.luv2code.springboot.demosecurity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;

    @Column(name="bid")
    private long bid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public Wishlist(){

        }

    public Wishlist(int id, String name, String title, String category, String image, String content,long bid) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.category = category;
        this.image = image;
        this.content = content;
        this.bid=bid;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }
}
