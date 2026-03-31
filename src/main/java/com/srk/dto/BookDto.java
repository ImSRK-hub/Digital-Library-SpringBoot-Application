package com.srk.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.srk.entity.Author;
import com.srk.entity.Genre;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class BookDto {
	private int bookId;
	@NotBlank(message = "Please enter Book name")
	private String bookName;
	private String title;
	@Min(value = 0, message = "Please provide cost")
	private float cost;
	@PastOrPresent(message = "Cannot exceed present day")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate publishedDate;
	@PositiveOrZero(message = "Minimum value should be 0")
	private int stock;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private int authorId;

}
