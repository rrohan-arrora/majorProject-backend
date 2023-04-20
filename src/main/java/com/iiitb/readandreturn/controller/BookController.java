package com.iiitb.readandreturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.readandreturn.entity.Book;
import com.iiitb.readandreturn.service.BookService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;
	
	@Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
	
	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestParam Long bookId) throws Exception {
		String userEmail = "testuser1@gmail.com";
		
		return bookService.checkoutBook(userEmail, bookId);
	}
}
