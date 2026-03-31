package com.srk.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue
	private int transactionId;
	private LocalDate borrowedDate;
	private LocalDate returnedDate;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	private float amt;
	@ManyToOne @JoinColumn(name = "user_id")
	private UserRecord user;
	@ManyToOne @JoinColumn(name = "book_id")
	private Book book;
	@ManyToOne @JoinColumn(name = "penalty_id")
	private Penalty penalty;
	

}
