package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
		super();
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public StudentDto saveStudentAlongWithCourse(StudentDto studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);

		List<CourseDto> coursesDto = studentDto.getCourses();

		List<Course> collect = coursesDto.stream().map(c -> {
			Course course = new Course();
			BeanUtils.copyProperties(c, course);
			List<Student> students = course.getStudents();

			course.setStudents(students);
			return course;

		}).collect(Collectors.toList());

		student.setCourses(collect);

		Student save = studentRepository.save(student);
		BeanUtils.copyProperties(save, studentDto);
		return studentDto;
	}

	@Override
	public List<Student> findAllStudent() {

		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(Long studentId) {

		return studentRepository.findById(studentId).get();
	}

	@Override
	public List<Student> findStudentContainingByName(String name) {

		return studentRepository.findByNameContaining(name);
	}

	@Override
	public List<Course> findCourseLessThanPrice(double price) {

		return courseRepository.findByFeeLessThan(price);
	}

	@Override
	public boolean deleteStudentById(Long id) {
		boolean isDeleted = false;
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student is Not present with this id :" + id));

		studentRepository.delete(student);

		isDeleted = true;
		return isDeleted;
	}

	@Override
	public Student updateStudentAlongWithCourse(Student student) {
		Student st = studentRepository.findById(student.getStudentId()).orElseThrow(
				() -> new StudentNotFoundException("Student is Not present with this id :" + student.getStudentId()));

		student.getCourses().forEach(c -> {
			Course course = courseRepository.findById(c.getCourseId())
					.orElseThrow(() -> new ResourceNotFoundException("Course Id not present with this Id"
							+ c.getCourseId() + " so new course will generate with new Id"));
		});

		return studentRepository.save(student);
	}

}
