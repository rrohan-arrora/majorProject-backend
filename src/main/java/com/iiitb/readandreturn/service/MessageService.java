package com.iiitb.readandreturn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.readandreturn.DAO.MessageRepository;
import com.iiitb.readandreturn.entity.Message;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageRepository mssgRepo;
	
	public void postMessage(Message mssg, String userEmail) {
		Message message = new Message(mssg.getTitle(), mssg.getQuestion());
		message.setUserEmail(userEmail);
		
		mssgRepo.save(message);
	}
	
	
	
}
