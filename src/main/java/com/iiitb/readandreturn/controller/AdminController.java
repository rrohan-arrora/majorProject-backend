package com.iiitb.readandreturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.readandreturn.requestmodels.AddBookRequest;
import com.iiitb.readandreturn.service.AdminService;
import com.iiitb.readandreturn.utils.ExtractJWT;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value="Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        
        // this is required if any user tries to accss the url directly
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.postBook(addBookRequest);
    }
	
	@PutMapping("/secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);
    }
	
	@PutMapping("/secure/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestHeader(value="Authorization") String token,
                                     @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.decreaseBookQuantity(bookId);
    }
	
	@DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value="Authorization") String token,
                           @RequestParam Long bookId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
	}
	
	
}
