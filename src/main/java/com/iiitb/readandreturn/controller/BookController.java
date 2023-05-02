package com.iiitb.readandreturn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.readandreturn.entity.Book;
import com.iiitb.readandreturn.entity.Checkout;
import com.iiitb.readandreturn.responsemodels.ShelfCurrentLoansResponse;
import com.iiitb.readandreturn.service.BookService;
import com.iiitb.readandreturn.utils.ExtractJWT;

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
	public Book checkoutBook(@RequestParam Long bookId,
			@RequestHeader(value="Authorization") String token) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
		return bookService.checkoutBook(userEmail, bookId);
	}
	
	@PutMapping("/secure/return")
    public void returnBook(@RequestHeader(value = "Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.returnBook(userEmail, bookId);
    }
	
	@PutMapping("/secure/renew/return")
    public void reNewBook(@RequestHeader(value = "Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        bookService.renewLoan(userEmail, bookId);
    }
	
	
	@GetMapping("/secure/currentLoans/count")
    public int currentLoansCount(@RequestHeader(value="Authorization") String token) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }
	
	@GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId,
    		@RequestHeader(value="Authorization") String token) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
		
        return bookService.checkoutBookByUser(userEmail, bookId);
    }
	
	@GetMapping("/secure/checkoutBooks")
    public List<Checkout> checkoutBooksByUser(@RequestHeader(value="Authorization") String token) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
        return bookService.currentLoans(userEmail);
    }
	
	@GetMapping("/secure/currentLoans")
	public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value="Authorization") String token) throws Exception{
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		
		return bookService.currentLoansByUser(userEmail);
	}
}
