package com.yr.yrv1annotation.dao;

import com.yr.v1.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookDao {

    @Select("select * from book where id=#{id}")
    public Book getBookById(Long id);

    @Update("update book set book_store_id=#{bookStoreId}, name=#{name}, author=#{author}, price=#{price}, topic=#{topic}, publish_date=#{publishDate} where id=#{id}")
    void updateBookById(Book book);

    @Delete("delete from book where id=#{id}")
    public void deleteBookById(Integer id);

    @Insert("insert into book(book_store_id, name, author, price, topic, publish_date) values(#{bookStoreId}, #{name}, #{author}, #{price}, #{topic}, #{publishDate})")
    public void insertBook(Book book);

    @Select("select * from book")
    public List<Book> findAllBook();

}
