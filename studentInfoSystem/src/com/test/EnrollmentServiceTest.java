package com.test;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.exception.CourseNotFoundException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Student;
import com.service.EnrollmentService;

public class EnrollmentServiceTest {

	EnrollmentService enrollmentService = new EnrollmentService();
	
	@Test
	public void getStudentTest() {
		
		//Use case 1
		int studentId = 2;
		Student expectedStudent = new Student(2, "Hermione", "Granger", LocalDate.parse("1979-09-19"), "hermione.granger@email.com", "9876543210");
		try {
			Assert.assertEquals(expectedStudent, enrollmentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found",e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 2
		studentId = 5;
		expectedStudent = new Student(5, "Luna", "Lovegood", LocalDate.parse("1981-02-13"), "luna.lovegood@email.com", "7774561230");
		try {
			Assert.assertEquals(expectedStudent, enrollmentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found",e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 3
		studentId = 11;
		expectedStudent = new Student(5, "Luna", "Lovegood", LocalDate.parse("1981-02-13"), "luna.lovegood@email.com", "7774561230");
		try {
			Assert.assertEquals(expectedStudent, enrollmentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found",e.getMessage());
		} catch (SQLException e) {
		}
		
	}
	
	@Test
	public void getCourseTest() {
		
		// Use case 1
		int courseId = 1001;
		Course expectedCourse = new Course(1001, "Data Structures", 3, 103);
		try {
			Assert.assertEquals(expectedCourse, enrollmentService.getCourse(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found",e.getMessage());
		}
		
		// Use case 2
		courseId = 1005;
		expectedCourse = new Course(1005, "Programming in python", 3, 103);
		try {
			Assert.assertEquals(expectedCourse, enrollmentService.getCourse(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found",e.getMessage());
		}
		
		// Use case 3
		courseId = 1013;
		expectedCourse = new Course(1005, "Programming in python", 3, 103);
		try {
			Assert.assertEquals(expectedCourse, enrollmentService.getCourse(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found",e.getMessage());
		}
	}
}
