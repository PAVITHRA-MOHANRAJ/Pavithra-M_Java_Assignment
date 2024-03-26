package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;
import com.service.TeacherService;

public class TeacherServiceTest {

	TeacherService teacherService = new TeacherService();

	@Test
	public void updateTeacherInfoTest() {
		// Use case 1
		int teacherId = 104;
		String email = "remus@gmail.com";

		try {
			teacherService.updateTeacherInfo(teacherId, email);
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Error while updating teacher info", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 2
		teacherId = 106;
		email = "gilderoy@gmail.com";

		try {
			teacherService.updateTeacherInfo(teacherId, email);
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Error while updating teacher info", e.getMessage());
		} catch (SQLException e) {
		}

		// Use case 3
		teacherId = 107;
		email = "filius@gmail.com";

		try {
			teacherService.updateTeacherInfo(teacherId, email);
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Error while updating teacher info", e.getMessage());
		} catch (SQLException e) {
		}
	}

	@Test
	public void displayTeacherInfoTest() {
		// Use case 1
		int teacherId = 106;
		Teacher expectedTeacher = new Teacher(106, "Gilderoy", "Lockhart", "gilderoy@gmail.com");
		try {
			Assert.assertEquals(expectedTeacher, teacherService.getTeacher(teacherId));
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
		}

		// Use case 2
		teacherId = 110;
		expectedTeacher = new Teacher(110, "Dolores", "Umbridge", "dolores.umbridge@email.com");
		try {
			Assert.assertEquals(expectedTeacher, teacherService.getTeacher(teacherId));
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
		}
	}
	
	@Test
	public void getAssignedCoursesTest() {
		
		// Use case 1
		List<String> expectedList = new ArrayList<>();
		int teacherId = 103;
		expectedList.add("Data Structures");
		expectedList.add("Programming in python");
		expectedList.add("Cyber security");
		
		try {
			Assert.assertEquals(expectedList, teacherService.getAssignedCourses(teacherId));
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Teacher not assigned to any course", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 2
		List<String> expectedList1 = new ArrayList<>();
		teacherId = 104;
		expectedList1.add("Artificial Intelligence");
		expectedList1.add("Software Engineering");
		
		try {
			Assert.assertEquals(expectedList1, teacherService.getAssignedCourses(teacherId));
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Teacher not assigned to any course", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 3
		List<String> expectedList2 = new ArrayList<>();
		teacherId = 107;
		expectedList2.add("Artificial Intelligence");
		expectedList2.add("Software Engineering");
		
		try {
			Assert.assertEquals(expectedList1, teacherService.getAssignedCourses(teacherId));
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Teacher not assigned to any course", e.getMessage());
		} catch (SQLException e) {
		}
	}
}
