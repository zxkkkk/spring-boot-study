package top.zxk.springboot.quickstart.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zxk.springboot.quickstart.dto.BookDTO;
import top.zxk.springboot.quickstart.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping()
    public List<BookDTO> getBooks(){
        return bookService.getAllBooks();
    }
}
