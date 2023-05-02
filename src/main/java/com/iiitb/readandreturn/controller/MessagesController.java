package com.iiitb.readandreturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.readandreturn.entity.Message;
import com.iiitb.readandreturn.requestmodels.AdminQuestionRequest;
import com.iiitb.readandreturn.service.MessageService;
import com.iiitb.readandreturn.utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {
	
	@Autowired
	private MessageService mssgService;
	
	@PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        mssgService.postMessage(messageRequest, userEmail);
    }
	
	 @PutMapping("/secure/admin/message")
	    public void putMessage(@RequestHeader(value="Authorization") String token,
	                           @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
	        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
	        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
	        if (admin == null || !admin.equals("admin")) {
	            throw new Exception("Administration page only.");
	        }
	        mssgService.putMessage(adminQuestionRequest, userEmail);
	    }
	
	
}
