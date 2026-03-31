package com.srk.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(exception = {Exception.class})
public ResponseEntity<String> handleExceptiom(Exception e) {
	System.out.println("Exception handled ..... ");
	return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
}

@ExceptionHandler(exception = MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException e){
	System.out.println("validation exception occured ..... ");
	Map<String, String> errors = new HashMap<>();
	
	e.getBindingResult().getFieldErrors()
    .forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));
	
	return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
}


}
