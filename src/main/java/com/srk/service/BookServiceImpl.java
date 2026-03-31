package com.srk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.srk.dto.AuthorDto;
import com.srk.dto.BookDto;
import com.srk.entity.Author;
import com.srk.entity.Book;
import com.srk.entity.Genre;
import com.srk.repository.AuthorRepository;
import com.srk.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private AuthorRepository authRepo;
	@Autowired
	private BookRepository bookRepo;

	@Override
	public Author addAuthor(AuthorDto authorDto) {
		Author author = new Author();
		// copy all properties from DTO to Entity
		BeanUtils.copyProperties(authorDto, author);
		return authRepo.save(author);
	}

	@Override
	public Book searchBookById(int bookId) {
		return bookRepo.findById(bookId).orElseThrow(() -> new ApplicationContextException("Book Id not found"));
	}

	@Override
	public Book addBook(BookDto bookDto ) {
		int authorId = bookDto.getAuthorId();
		Optional<Book> optionalBook = bookRepo.findById(bookDto.getBookId());
		if(optionalBook.isPresent()) {
			throw new ApplicationContextException("Book already present");
		}
		Optional<Author> optionalAuthor = authRepo.findById(authorId);
		if(optionalAuthor.isPresent()) {
			Book book = new Book();
			book.setAuthor(optionalAuthor.get());
			BeanUtils.copyProperties(bookDto, book);
			return bookRepo.save(book);
		}
		return null;
	}

	@Override
	public List<Book> searchByGenre2(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Page<Book> allBooksWithPagination(int pageNo, int size) {
		Pageable pageable = PageRequest.of(pageNo, size, Sort.by("bookName"));
		return bookRepo.findAll(pageable);
	}

	@Override
	public List<Book> searchByAuthor(int authorId) {
		return bookRepo.getByAuthorId(authorId);
	}

	@Override
	public List<Book> searchByGenre(Genre genre) {
		return bookRepo.findByGenre(genre);
	}

	@Override
	public void removeBook(int bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> searchByBookName(String bookName) {
		return bookRepo.findByBookName(bookName);
	}

	@Override
	public Book updateCost(int bookId, float cost) {
		Book b = bookRepo.findById(bookId).orElseThrow(() -> new ApplicationContextException("Book not found"));
		if(cost >= 0) {
			b.setCost(cost);
			bookRepo.save(b);
		}else {
			throw new ApplicationContextException("Price cannot be less that 0");
		}
		return b;
	}

	@Override
	public boolean checkAvailability(int bookId) {
		Book b = bookRepo.findById(bookId).orElseThrow(() -> new ApplicationContextException("Book Id not found"));
		int availability = b.getStock();
		if(availability >= 1) {
			return true;
		}else {
			return false;
		}
	}
}
