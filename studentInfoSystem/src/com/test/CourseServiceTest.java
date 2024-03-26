package com.test;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InvalidCourseDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.service.CourseService;

public class CourseServiceTest {
	CourseService courseService = new CourseService();

	@Test
	public void displayCourseInfoTest() {

		// Use case 1
		int courseId = 1001;
		Course expectedCourse = new Course(1001, "Data Structures", 4, 103);
		try {
			Assert.assertEquals(expectedCourse, courseService.DisplayCourseInfo(courseId));
		} catch (InvalidCourseDataException e) {
			Assert.assertEquals("Course Code given is Invalid", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 2
		courseId = 1002;
		expectedCourse = new Course(1002, "Databases", 4, 102);
		try {
			Assert.assertEquals(expectedCourse, courseService.DisplayCourseInfo(courseId));
		} catch (InvalidCourseDataException e) {
			Assert.assertEquals("Course Code given is Invalid", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 3
		courseId = 1011;
		expectedCourse = new Course(1002, "Databases", 4, 102);
		try {
			Assert.assertEquals(expectedCourse, courseService.DisplayCourseInfo(courseId));
		} catch (InvalidCourseDataException e) {
			Assert.assertEquals("Course Code given is Invalid", e.getMessage());
		} catch (SQLException e) {
		}
	}

	@Test
	public void getTeacherIdTest() {

		// Use case 1
		int courseId = 1001;
		int expectedId = 103;
		try {
			Assert.assertEquals(expectedId, courseService.getTeacherId(courseId));
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No teacher assigned to course", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 2
		courseId = 1004;
		expectedId = 105;
		try {
			Assert.assertEquals(expectedId, courseService.getTeacherId(courseId));
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No teacher assigned to course", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 3
		courseId = 1011;
		expectedId = 103;
		try {
			Assert.assertEquals(expectedId, courseService.getTeacherId(courseId));
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No teacher assigned to course", e.getMessage());
		} catch (SQLException e) {
		}
	}
}
