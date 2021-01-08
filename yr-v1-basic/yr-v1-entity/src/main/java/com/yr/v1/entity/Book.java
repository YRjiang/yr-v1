package com.yr.v1.entity;

import java.io.Serializable;

public class Book implements Serializable {

    private Long id;

    private Long bookStoreId;

    private String name;

    private String author;

    private String price;

    private String topic;

    private String publishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookStoreId() {
        return bookStoreId;
    }

    public void setBookStoreId(Long bookStoreId) {
        this.bookStoreId = bookStoreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookStoreId=" + bookStoreId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", topic='" + topic + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
