package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.EnrollmentDao;
import com.dao.EnrollmentDaoImpl;
import com.exception.CourseNotFoundException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Student;

public class EnrollmentService {
	EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

	public List<Integer> fetchEnrollmentsByCourseId(int courseId1) throws SQLException {
		return enrollmentDao.fetchEnrollmentsByCourseId(courseId1);
	}

	public Enrollment verifyEnrollmentId(int enrollmentId) throws InvalidEnrollmentDataException, SQLException {
		return enrollmentDao.verifyEnrollmentId(enrollmentId);
	}

	public int getStudentId(int enrollmentId) throws SQLException {
		return enrollmentDao.getStudentId(enrollmentId);
	}

	public Student getStudent(int studentId) throws StudentNotFoundException, SQLException {
		return enrollmentDao.getStudent(studentId);
	}

	public int getCourseId(int enrollmentId) throws SQLException {
		return enrollmentDao.getCourseId(enrollmentId);
	}

	public Course getCourse(int courseId) throws SQLException, CourseNotFoundException {
		return enrollmentDao.getCourse(courseId);
	}

}
