package com.srk.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	private int bookId;
	private String bookName;
	private String title;
	private float cost;
	private LocalDate publishedDate;
	private int stock;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	@ManyToOne @JoinColumn(name = "author_id")
	private Author author;
}
