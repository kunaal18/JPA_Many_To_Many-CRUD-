package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { StudentNotFoundException.class })
	public ResponseEntity<Response> studentNotFoundException(StudentNotFoundException e) {
		return new ResponseEntity<>(new Response(true, "Student not found", e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>(new Response(true, "Resource not found", e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { CourseNotFoundException.class })
	public ResponseEntity<Response> courseNotFoundException(CourseNotFoundException e) {
		return new ResponseEntity<>(new Response(true, "Resource not found", e.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
