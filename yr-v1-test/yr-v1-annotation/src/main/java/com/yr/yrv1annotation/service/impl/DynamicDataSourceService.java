package com.yr.yrv1annotation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yr.v1.entity.Book;
import com.yr.v1.entity.City;
import com.yr.yrv1annotation.dao.BookDao;
import com.yr.yrv1annotation.dao.DynamicDataSourceDao;
import com.yr.yrv1annotation.dynamicdatasource.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DynamicDataSourceService {

    @Autowired
    DynamicDataSourceDao dynamicDataSourceDao;

    @Autowired
    BookDao bookDao;

    @DataSource
    public List<City> findAllCity() {
        List<City> allCity = dynamicDataSourceDao.findAllCity();
        return allCity;
    }

    @DataSource(value = "slave")
    public List<Book> findAllBook() {
        List<Book> allBook = bookDao.findAllBook();
        return allBook;
    }

    @DataSource(value = "slave")
    public void insertBook() {
        Book book = new Book();
        book.setBookStoreId(2L);
        book.setName("编程思想");
        book.setAuthor("who");
        book.setPrice("78.20");
        book.setTopic("编程思想");
        book.setPublishDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));
        bookDao.insertBook(book);
    }

    //@DS
    public Book getBookById() {
        Book book = bookDao.getBookById(1L);
        System.out.println("book: " + JSONObject.toJSONString(book));
        return book;
    }

}
