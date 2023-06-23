package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Response;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student/course")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/saveStudent")
	public ResponseEntity<Response> saveStudentAlongWithCourse(@RequestBody StudentDto student) {

		StudentDto student2 = studentService.saveStudentAlongWithCourse(student);

		if (student2 == null) {
			return new ResponseEntity<>(new Response(true, "Not saved", null), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new Response(false, "Saved Successfully", student2), HttpStatus.ACCEPTED);

	}

	@GetMapping("/findAllStudent")
	public ResponseEntity<Response> findAllStudent() {
		return new ResponseEntity<>(new Response(false, "List fetched Successfully", studentService.findAllStudent()),
				HttpStatus.OK);

	}

	@GetMapping("/findById{studentId}")
	public ResponseEntity<Response> findStudentById(@PathVariable Long studentId) {
		return new ResponseEntity<>(new Response(false, "Success", studentService.findStudentById(studentId)),
				HttpStatus.ACCEPTED);

	}

	@GetMapping("findByName/{name}")
	public ResponseEntity<Response> findStudentContainingByName(@PathVariable String name) {
		return new ResponseEntity<>(new Response(false, "Success", studentService.findStudentContainingByName(name)),
				HttpStatus.ACCEPTED);

	}

	@GetMapping("/findCourse/{price}")
	public ResponseEntity<Response> findCourseLessThanPrice(@PathVariable double price) {
		return new ResponseEntity<>(new Response(false, "Success", studentService.findCourseLessThanPrice(price)),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Response> deleteStudentById(@PathVariable Long id) {
		return new ResponseEntity<>(new Response(false, "Deleted Successfully", studentService.deleteStudentById(id)),
				HttpStatus.OK);

	}

	@PutMapping("/updateStudent")
	public ResponseEntity<Response> updateStudentAlongWithCourse(@RequestBody Student student) {

		return new ResponseEntity<>(
				new Response(false, "Updated Successfully", studentService.updateStudentAlongWithCourse(student)),
				HttpStatus.OK);
	}

}
