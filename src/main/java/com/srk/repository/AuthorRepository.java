package com.srk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srk.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
