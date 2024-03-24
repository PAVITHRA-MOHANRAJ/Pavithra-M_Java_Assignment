package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Student;

public interface EnrollmentDao {

	public List<Integer> fetchEnrollmentsByCourseId(int courseId1) throws SQLException;

	public Enrollment verifyEnrollmentId(int enrollmentId) throws InvalidEnrollmentDataException, SQLException;

	public int getStudentId(int enrollmentId) throws SQLException;

	public Student getStudent(int studentId) throws StudentNotFoundException, SQLException;

	public int getCourseId(int enrollmentId) throws SQLException;

	public Course getCourse(int courseId) throws SQLException, CourseNotFoundException;

	

}
