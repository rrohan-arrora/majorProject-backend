package com.iiitb.readandreturn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiitb.readandreturn.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
