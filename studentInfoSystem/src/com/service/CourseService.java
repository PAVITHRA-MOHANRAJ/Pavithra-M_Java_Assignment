package com.service;

import java.sql.SQLException;
import java.util.List;
import com.model.Course;

import com.dao.CourseDao;
import com.dao.CourseDaoImpl;
import com.exception.CourseNotFoundException;
import com.exception.InvalidCourseDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;

public class CourseService {
	
	CourseDao courseDao = new CourseDaoImpl();

	public Teacher validateTeacher(int teacherId) throws TeacherNotFoundException, SQLException {
		return courseDao.validateTeacher(teacherId);
	}

	public List<Course> fetchAllCourses() throws SQLException{
		return courseDao.fetchAllCourses();
	}

	public void insertRecordInCourse(List<Course> list, int courseId, int teacherId) throws SQLException {
		courseDao.insertRecordInCourse(courseId,teacherId);
		
	}

	public Course verifyCourse(int courseId) throws CourseNotFoundException, SQLException{
		return courseDao.verifyCourse(courseId);
	}

	public Course validateCourseId(int courseCode)throws InvalidCourseDataException, SQLException {
		return courseDao.validateCourseId(courseCode);
	}

	public void updateRecordInCourse(List<Course> list, int courseCode, int credits) throws SQLException{
		courseDao.updateRecordInCourse(courseCode, credits);
	}

	public Course DisplayCourseInfo(int courseId)throws InvalidCourseDataException, SQLException {
		return courseDao.DisplayCourseInfo(courseId);
	}

	public int getTeacherId(int courseId) throws TeacherNotFoundException, SQLException{
		return courseDao.getTeacherId(courseId);
	}

	
}
