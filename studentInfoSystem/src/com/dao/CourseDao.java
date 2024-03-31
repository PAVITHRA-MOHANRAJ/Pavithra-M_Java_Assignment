package com.dao;

import java.sql.SQLException;
import com.model.Course;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidCourseDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;


public interface CourseDao {

	public Teacher validateTeacher(int teacherId) throws TeacherNotFoundException, SQLException ;
	public List<Course> fetchAllCourses() throws SQLException;
	public void insertRecordInCourse(int courseId, int teacherId) throws SQLException;
	public Course verifyCourse(int courseId) throws CourseNotFoundException, SQLException;
	public Course validateCourseId(int courseCode)throws InvalidCourseDataException, SQLException ;
	public void updateRecordInCourse(int courseCode, int credits)throws SQLException;
	public Course DisplayCourseInfo(int courseId)throws InvalidCourseDataException, SQLException;
	public int getTeacherId(int courseId) throws SQLException, TeacherNotFoundException;
	public void updateTeacherId(int courseCode, int teacherId) throws SQLException;
		

}
