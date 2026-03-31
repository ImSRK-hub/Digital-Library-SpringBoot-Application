package com.srk.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srk.dto.BookDto;
import com.srk.entity.Book;
import com.srk.entity.Genre;
import com.srk.service.BookService;

@RestController
@RequestMapping("/books")
public class BookApi {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("")
	public List<Book> getAllBooks() {
		return bookService.allBooks();
	}
	@GetMapping("/{bookId}")
	public Book searchBooksById(@PathVariable("bookId") int id) {
		return bookService.searchBookById(id);
	}
	@GetMapping("/byauthorId/{authorId}")
	public List<Book> searchBooksByAuthorId(@PathVariable("authorId") int id) {
		return bookService.searchByAuthor(id);
	}
	@GetMapping("/bookName")
	public List<Book> searchBooksByBookName(@RequestParam("name") String bookName) {
		return bookService.searchByBookName(bookName);
	}
	@GetMapping("/genre")
	public List<Book> searchBooksByGenre(@RequestParam("genre") Genre genre) {
		return bookService.searchByGenre(genre);
	}
	@GetMapping("/paged")
	public Page<Book> searchBooksWithPagination(@RequestParam("pageNo") int pageNo, @RequestParam("size") int size ) {
		return bookService.allBooksWithPagination(pageNo,size);
	}
	@GetMapping("/availability/{id}")
	public ResponseEntity<String> checkAvailability(@PathVariable("id") int id){
		return bookService.checkAvailability(id) ? new ResponseEntity<String>("Book Avaialble",HttpStatus.OK) : new ResponseEntity<String>("Book Not Avaialble",HttpStatus.NOT_FOUND);
	}
	
	
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody BookDto  bookDto) {
		return bookService.addBook(bookDto);
	}
	@PostMapping("/updateCost/{id}")
	public Book updateCost(@PathVariable("id") int id, @RequestBody Map<String, Integer> payload ) {
		int cost = payload.getOrDefault("cost", 0);
		return bookService.updateCost(id, cost);
	}
	
	

}
