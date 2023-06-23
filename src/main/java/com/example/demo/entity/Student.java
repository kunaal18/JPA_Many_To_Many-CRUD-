package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	private String name;
	private int age;
	private String dept;

	// @JoinTable(name = "STUDENT_COURSE_TABLE",
//	joinColumns = {
//			@JoinColumn(name ="student_id",referencedColumnName = "id")
//	},
//	inverseJoinColumns = {
//			@JoinColumn(name ="course_id",referencedColumnName = "id")
//	}
//			)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Course> courses;

}
