package com.iiitb.readandreturn.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiitb.readandreturn.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	// pagebale helps in adding page and size to the url.
	Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pagable);
	Page<Book> findByAuthorContaining(@RequestParam("author") String author, Pageable pagable);
	Page<Book> findByCategory(@RequestParam("category") String category, Pageable pagable);
}
