package com.srk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDto {
	private int authorId;
	@NotBlank(message = "Author name cannot be blank")
	private String authorName;
}
