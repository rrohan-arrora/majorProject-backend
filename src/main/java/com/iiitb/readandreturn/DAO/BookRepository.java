package com.iiitb.readandreturn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiitb.readandreturn.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
