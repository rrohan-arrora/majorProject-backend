package com.iiitb.readandreturn.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiitb.readandreturn.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	// pagebale helps in adding page and size to the url.
	Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pagable);
	Page<Book> findByAuthorContaining(@RequestParam("author") String author, Pageable pagable);
	Page<Book> findByCategory(@RequestParam("category") String category, Pageable pagable);
	
	@Query("select o from Book o where id in :book_ids")
    List<Book> findBooksByBookIds (@Param("book_ids") List<Long> bookId);
}
