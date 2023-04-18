package com.iiitb.readandreturn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.iiitb.readandreturn.entity.Book;

@CrossOrigin("http://localhost:3000")
public interface BookRepository extends JpaRepository<Book, Long>{

}
