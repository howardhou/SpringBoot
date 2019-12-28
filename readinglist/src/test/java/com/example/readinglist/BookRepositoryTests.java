package com.example.readinglist;

import com.example.readinglist.Entity.Book;
import com.example.readinglist.Repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testFindByReader(){
        List<Book>  list = bookRepository.findByReader("houdongdong");

        Book book = list.get(0);
        System.out.printf(list.toString());
        Assert.assertEquals(book.getReader(), "houdongdong");
        Assert.assertEquals(book.getTitle(), "Docker 实战");
    }
}
