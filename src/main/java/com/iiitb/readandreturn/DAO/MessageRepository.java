package com.iiitb.readandreturn.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.iiitb.readandreturn.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	Page<Message> findMessagesByUserEmail(@RequestParam("user_email") String userEmail, Pageable pagable);
	
	Page<Message> findByClosed(@RequestParam("closed") boolean closed, Pageable pageable);
}
