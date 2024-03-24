package com.service;

import com.model.Payment;
import com.model.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.StudentDao;
import com.dao.StudentDaoImpl;
import com.exception.CourseNotFoundException;
import com.exception.DuplicateEnrollmentException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.InvalidPaymentRecordException;
import com.exception.PaymentValidationException;
import com.exception.StudentDataUpdationException;
import com.exception.StudentNotFoundException;

public class StudentService {
	StudentDao studentDao = new StudentDaoImpl();

	public Student getEnrollments(int id) throws SQLException{
		return studentDao.getEnrollments(id);
	}

	public void verifyStudentId(int studentId) throws SQLException, StudentNotFoundException {
		studentDao.verifyStudentId(studentId);
	}

	public void verifyCourseId(int courseId) throws CourseNotFoundException, SQLException {
		studentDao.verifyCourseId(courseId);
	}

	public void verifyEnrolledStatus(int studentId) throws DuplicateEnrollmentException, SQLException {
		studentDao.verifyEnrolledStatus(studentId);
	}

	public void enrollInCourse(int id, LocalDate enrollmentDate, int studentId, int courseId) throws SQLException, InvalidEnrollmentDataException {
		studentDao.enrollInCourse(id, enrollmentDate, studentId, courseId);
	}

	public void updateStudentInfo(int studentId, String phoneNumber) throws StudentDataUpdationException, SQLException {
		studentDao.updateStudentInfo(studentId, phoneNumber);
	}

	public void makePayment(int paymentId, double amount, LocalDate paymentDate, int studentId) throws SQLException, PaymentValidationException, InvalidPaymentRecordException {
		studentDao.makePayment(paymentId, amount, paymentDate, studentId);
	}

	public Student displayStudentInfo(int studentId) throws SQLException {
		return studentDao.displayStudentInfo(studentId);
	}

	public List<Integer> getCourseId(int studentId) throws InvalidEnrollmentDataException, SQLException {
		return studentDao.getCourseId(studentId);
	}

	public String getEnrolledCourses(int id) throws SQLException, CourseNotFoundException {
		return studentDao.getEnrolledCourses(id);
	}

	public List<Payment> getPaymentHistory(int studentId) throws InvalidPaymentRecordException, SQLException {
		return studentDao.getPaymentHistory(studentId);
	}

}
