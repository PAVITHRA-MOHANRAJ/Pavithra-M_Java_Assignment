package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.DuplicateEnrollmentException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.InvalidPaymentRecordException;
import com.exception.PaymentValidationException;
import com.exception.StudentDataUpdationException;
import com.exception.StudentNotFoundException;
import com.model.Payment;
import com.model.Student;

public interface StudentDao {

	Student getEnrollments(int id) throws SQLException;

	void verifyStudentId(int studentId) throws SQLException, StudentNotFoundException;

	void verifyCourseId(int courseId) throws CourseNotFoundException, SQLException;

	void verifyEnrolledStatus(int studentId) throws DuplicateEnrollmentException, SQLException;

	void enrollInCourse(int id, LocalDate enrollmentDate, int studentId, int courseId) throws SQLException, InvalidEnrollmentDataException;

	void updateStudentInfo(int studentId, String phoneNumber) throws StudentDataUpdationException, SQLException;

	void makePayment(int paymentId, double amount, LocalDate paymentDate, int studentId) throws SQLException, PaymentValidationException, InvalidPaymentRecordException;

	Student displayStudentInfo(int studentId) throws SQLException;

	List<Integer> getCourseId(int studentId) throws InvalidEnrollmentDataException, SQLException;

	String getEnrolledCourses(int id) throws SQLException, CourseNotFoundException;

	List<Payment> getPaymentHistory(int studentId) throws InvalidPaymentRecordException, SQLException;

}
