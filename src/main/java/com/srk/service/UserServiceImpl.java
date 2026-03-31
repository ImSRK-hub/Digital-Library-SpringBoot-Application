package com.srk.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.srk.dto.BorowDto;
import com.srk.entity.Book;
import com.srk.entity.Penalty;
import com.srk.entity.Transaction;
import com.srk.entity.TransactionType;
import com.srk.entity.UserRecord;
import com.srk.repository.AuthorRepository;
import com.srk.repository.BookRepository;
import com.srk.repository.PenaltyRepository;
import com.srk.repository.TransactionRepository;
import com.srk.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TransactionRepository tranRepo;
	@Autowired
	private PenaltyRepository penaltyRepo;
	
	@Override
	public UserRecord addNewUser(UserRecord u) {
		return userRepo.save(u);
	}

	@Override
	public Book borrowBook(BorowDto borrowDto) {
		UserRecord user = userRepo
				.findById(borrowDto.getUserId()).orElseThrow(() -> new ApplicationContextException("User not found"));
		Book book = bookRepo
				.findById(borrowDto.getBookId()).orElseThrow(() -> new ApplicationContextException("Book not found"));
		if(book.getStock() > 0) {
			Transaction t = new Transaction();
			t.setBorrowedDate(LocalDate.now());
			t.setTransactionType(TransactionType.BORROW);
			t.setAmt(book.getCost());
			t.setBook(book);
			t.setUser(user);
			book.setStock(book.getStock() - 1);
			bookRepo.save(book);
			tranRepo.save(t);
			
		}else {
			throw new ApplicationContextException("No Stock available now");
		}
		
		return book;
	}

	@Override
	public List<Transaction> checkTransactionByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRecord> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public Transaction returnBook(int transactionId) {
		Transaction t = tranRepo
				.findById(transactionId).orElseThrow(() -> new ApplicationContextException("Transaction not found"));
		if(t.getTransactionType() != TransactionType.BORROW) {
			throw new ApplicationContextException("Transaction already closed");
		}
		Book b = bookRepo.findById(t.getBook().getBookId()).get();
		LocalDate borrowedDate = t.getBorrowedDate();
		LocalDate returnedDated = LocalDate.now();
		int dateDiff = (int) Duration.between(borrowedDate.atStartOfDay(), returnedDated.atStartOfDay()).toDays();
		if(dateDiff <= 30) {
			t.setReturnedDate(returnedDated);
			t.setTransactionType(TransactionType.RETURN);
			t.setAmt(b.getCost());
		}else {
			Penalty p = new Penalty();
			p.setNoOfDaysDiff(dateDiff);
			int penaltyDays = dateDiff - 30;
			float penaltyAmt = penaltyDays * 50;
			p.setAmt(penaltyAmt);
			p.setDescription("Late Fee");
			t.setReturnedDate(returnedDated);
			t.setTransactionType(TransactionType.RETURN);
			t.setAmt(b.getCost() + penaltyAmt);
			t.setPenalty(p);
			penaltyRepo.save(p);
		}
		b.setStock(b.getStock() + 1);
		bookRepo.save(b);
		tranRepo.save(t);
		return t;
	}

}
