package com.example.readinglist.Controller;

import com.example.readinglist.Entity.Book;
import com.example.readinglist.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/reading")
// @ConfigurationProperties注解，说明该 Bean的属性应该是(通过setter方法)从配置属性值注入的
// prefix属性说明 ReadingListController应该注入带amazon前缀的属性
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {
    private BookRepository bookRepository;
    private String associateId;

    @Autowired
    public ReadingListController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = bookRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }
        return "readingList";
    }
    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        bookRepository.save(book);
        return "redirect:/reading/{reader}";
    }
}
