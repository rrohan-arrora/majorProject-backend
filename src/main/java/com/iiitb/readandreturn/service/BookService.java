package com.iiitb.readandreturn.service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiitb.readandreturn.DAO.BookRepository;
import com.iiitb.readandreturn.DAO.CheckoutRepository;
import com.iiitb.readandreturn.entity.Book;
import com.iiitb.readandreturn.entity.Checkout;
import com.iiitb.readandreturn.responsemodels.ShelfCurrentLoansResponse;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CheckoutRepository checkoutRepository;
	
	
	public Book checkoutBook(String userEmail, Long bookId) throws Exception{
		Optional<Book> book = bookRepository.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);

        return book.get();
		
	}
	
	public Boolean checkoutBookByUser(String userEmail, Long bookId) {
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validateCheckout != null) {
            return true;
        } else {
            return false;
        }
    }
	
	public int currentLoansCount(String userEmail) {
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }
	
	public List<Checkout> currentLoans(String userEmail) {
        return checkoutRepository.findBooksByUserEmail(userEmail);
    }
	
	 public List<ShelfCurrentLoansResponse> currentLoansByUser(String userEmail) throws Exception {
		 List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses = new ArrayList<>();

	     List<Checkout> checkoutList = currentLoans(userEmail);
	     List<Long> bookIdList = new ArrayList<>();

	     for (Checkout i: checkoutList) {
	    	 bookIdList.add(i.getBookId());
	     }

	     List<Book> books = bookRepository.findBooksByBookIds(bookIdList);

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        for (Book book : books) {
//	            Optional<Checkout> checkout = checkoutList.stream()
//	                    .filter(x -> x.getBookId() == book.getId()).findFirst();
	        	Checkout checkout  = checkoutRepository.findByUserEmailAndBookId(userEmail, book.getId());

//	            if (checkout.isPresent()) {

	                Date d1 = sdf.parse(checkout.getReturnDate());
	                Date d2 = sdf.parse(LocalDate.now().toString());

	                TimeUnit time = TimeUnit.DAYS;

	                long difference_In_Time = time.convert(d1.getTime() - d2.getTime(),
	                        TimeUnit.MILLISECONDS);

	                shelfCurrentLoansResponses.add(new ShelfCurrentLoansResponse(book, (int) difference_In_Time));
//	            }
	        }
	        return shelfCurrentLoansResponses;
	    }
	 
	 public void returnBook (String userEmail, Long bookId) throws Exception {

	        Optional<Book> book = bookRepository.findById(bookId);

	        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

//	        if (!book.isPresent() || validateCheckout == null) {
//	            throw new Exception("Book does not exist or not checked out by user");
//	        }

	        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);

	        bookRepository.save(book.get());
	        checkoutRepository.deleteById(validateCheckout.getId());

//	        History history = new History(
//	                userEmail,
//	                validateCheckout.getCheckoutDate(),
//	                LocalDate.now().toString(),
//	                book.get().getTitle(),
//	                book.get().getAuthor(),
//	                book.get().getDescription(),
//	                book.get().getImg()
//	        );
//
//	        historyRepository.save(history);
	    }
}
