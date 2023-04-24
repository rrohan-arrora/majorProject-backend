package com.iiitb.readandreturn.responsemodels;

import com.iiitb.readandreturn.entity.Book;

import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {
	
	private Book book;
	private int daysLeft;
	
	public ShelfCurrentLoansResponse(Book book, int daysLeft) {
		this.book = book;
		this.daysLeft = daysLeft;
	}

}
