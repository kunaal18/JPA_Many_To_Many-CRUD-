package com.example.demo.exception;

@SuppressWarnings("serial")
public class CourseNotFoundException extends RuntimeException {
	CourseNotFoundException(String message) {
		super(message);
	}
}
