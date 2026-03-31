package com.srk.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srk.dto.AuthorDto;
import com.srk.dto.BookDto;
import com.srk.entity.Author;
import com.srk.entity.Book;
import com.srk.service.BookService;

import jakarta.validation.Valid;

@RestController
public class AuthorApi {
	@Autowired
	private BookService bookService;
	
	@PostMapping("/author")
	public ResponseEntity<Author> addNewAuthor( @RequestBody @Valid AuthorDto a ){
		Author author = bookService.addAuthor(a);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}

}
