package com.srk.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srk.dto.BorowDto;
import com.srk.entity.Book;
import com.srk.entity.Transaction;
import com.srk.entity.UserRecord;
import com.srk.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserRecord> allUsers(){
		return userService.getAllUser();
	}
	
	@PostMapping("")
	public UserRecord addUser(@RequestBody @Valid UserRecord user) {
		return userService.addNewUser(user);
	}
	@PostMapping("/borrowBook")
	public Book BorrowBook(@RequestBody BorowDto borrowDto) {
		return userService.borrowBook(borrowDto);
	}
	
	@PutMapping("/returnBook/{id}")
	public Transaction returnBook(@PathVariable("id") int id) {
		return userService.returnBook(id);
	}
	
	

}
