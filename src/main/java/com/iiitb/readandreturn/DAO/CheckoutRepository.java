package com.iiitb.readandreturn.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iiitb.readandreturn.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
	Checkout findByUserEmailAndBookId(String userEmail, Long bookId);
}
