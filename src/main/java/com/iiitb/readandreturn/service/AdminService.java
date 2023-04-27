package com.iiitb.readandreturn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.readandreturn.DAO.BookRepository;
import com.iiitb.readandreturn.entity.Book;
import com.iiitb.readandreturn.requestmodels.AddBookRequest;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public void postBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopies(addBookRequest.getCopies());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        bookRepo.save(book);
    }
	
}
