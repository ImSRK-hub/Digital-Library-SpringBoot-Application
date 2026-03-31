package com.srk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srk.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
