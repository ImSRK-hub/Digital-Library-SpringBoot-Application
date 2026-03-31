package com.srk.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.srk.dto.AuthorDto;
import com.srk.dto.BookDto;
import com.srk.entity.Author;
import com.srk.entity.Book;
import com.srk.entity.Genre;

public interface BookService {
	
	Author addAuthor(AuthorDto author);
	Book searchBookById(int bookId);
	Book addBook(BookDto bookDto);
	List<Book> searchByGenre2(Genre genre);
	List<Book> allBooks();
	Page<Book> allBooksWithPagination(int pageNo, int size);
	List<Book> searchByAuthor(int authorId);
	List<Book> searchByGenre(Genre genre);
	
	void removeBook(int bookId);
	
	List<Book> searchByBookName(String bookName);
	Book updateCost(int bookId, float cost);
	
	boolean checkAvailability(int id);
	
	

}
