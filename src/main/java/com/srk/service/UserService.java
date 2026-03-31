package com.srk.service;

import java.util.List;

import com.srk.dto.BorowDto;
import com.srk.entity.Book;
import com.srk.entity.Transaction;
import com.srk.entity.UserRecord;

public interface UserService {
	
	UserRecord addNewUser(UserRecord u);
	List<UserRecord> getAllUser();
	Book borrowBook(BorowDto boorowDto);
	List<Transaction> checkTransactionByUser(int userId);
	Transaction returnBook(int transactionId);

}
