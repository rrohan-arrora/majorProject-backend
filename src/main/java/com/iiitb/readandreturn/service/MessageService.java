package com.iiitb.readandreturn.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiitb.readandreturn.DAO.MessageRepository;
import com.iiitb.readandreturn.entity.Message;
import com.iiitb.readandreturn.requestmodels.AdminQuestionRequest;

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
	
	public void putMessage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception {
        Optional<Message> message = mssgRepo.findById(adminQuestionRequest.getId());
        if (!message.isPresent()) {
            throw new Exception("Message not found");
        }

        message.get().setAdminEmail(userEmail);
        message.get().setResponse(adminQuestionRequest.getResponse());
        message.get().setClosed(true);
        mssgRepo.save(message.get());
    }
	
	
}
