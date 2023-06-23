package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;

public interface StudentService {

	StudentDto saveStudentAlongWithCourse(StudentDto studentDto);

	List<Student> findAllStudent();

	Student findStudentById(Long studentId);

	List<Student> findStudentContainingByName(String name);

	List<Course> findCourseLessThanPrice(double price);

	boolean deleteStudentById(Long id);

	Student updateStudentAlongWithCourse(Student student);

}
