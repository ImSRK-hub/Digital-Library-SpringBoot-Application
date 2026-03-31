package com.srk.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.srk.entity.Book;
import com.srk.entity.Genre;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByBookName(String name);
	List<Book> findByGenre(Genre genre);
	
	@Query(value = "select b from Book b where b.author.authorId= :id")
	List<Book> getByAuthorId(@Param("id") int authorId);
}
