package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.DuplicateEnrollmentException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.InvalidPaymentRecordException;
import com.exception.PaymentValidationException;
import com.exception.StudentDataUpdationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;

import util.DBUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student getEnrollments(int id) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "select * from students where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {

			int id1 = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			java.sql.Date sqlDateOfBirth = rst.getDate("date_of_birth");
			LocalDate dateOfBirth = sqlDateOfBirth.toLocalDate();

			String email = rst.getString("email");
			String phoneNumber = rst.getString("phone_number");

			// save it in an object
			Student student = new Student(id1, firstName, lastName, dateOfBirth, email, phoneNumber);
			return student;
		}
		DBUtil.dbClose();
		return null;

	}

	@Override
	public void verifyStudentId(int studentId) throws SQLException, StudentNotFoundException {
		Connection conn = DBUtil.getDBConn();
		List<Student> list = new ArrayList<>();
		String sql = "select * from students where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			java.sql.Date sqlDateOfBirth = rst.getDate("date_of_birth");
			LocalDate dateOfBirth = sqlDateOfBirth.toLocalDate();

			String email = rst.getString("email");
			String phoneNumber = rst.getString("phone_number");

			// save it in an object
			Student student = new Student(id, firstName, lastName, dateOfBirth, email, phoneNumber);
			list.add(student);
		}
		if (list.isEmpty()) {
			throw new StudentNotFoundException("Student not found");
		}
		DBUtil.dbClose();

	}

	@Override
	public void verifyCourseId(int courseId) throws CourseNotFoundException, SQLException {
		Connection conn = DBUtil.getDBConn();
		List<Course> list = new ArrayList<>();
		String sql = "select * from courses where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, courseId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {

			int id = rst.getInt("id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");
			int teacherId = rst.getInt("teacher_id");
			// save it in an object
			Course course = new Course(id, courseName, credits, teacherId);
			list.add(course);
		}
		if (list.isEmpty()) {
			throw new CourseNotFoundException("Course ID given is Invalid");
		}
		DBUtil.dbClose();
	}

	@Override
	public void verifyEnrolledStatus(int studentId) throws DuplicateEnrollmentException, SQLException {
		Connection conn = DBUtil.getDBConn();
		List<Enrollment> list = new ArrayList<>();
		String sql = "select * from enrollments where students_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int id = rst.getInt("id");
			java.sql.Date sqlenrollmentDate = rst.getDate("enrollment_date");
			LocalDate enrollmentDate = sqlenrollmentDate.toLocalDate();
			int studentsId = rst.getInt("students_id");
			int courseId = rst.getInt("courses_id");
			Enrollment enrollment = new Enrollment(id, enrollmentDate, studentsId, courseId);
			list.add(enrollment);
		}
		if (!list.isEmpty()) {
			throw new DuplicateEnrollmentException("Student already enrolled in a course");
		}
		DBUtil.dbClose();
	}

	@Override
	public void enrollInCourse(int id, LocalDate enrollmentDate, int studentId, int courseId) throws SQLException, InvalidEnrollmentDataException {
		Connection conn = DBUtil.getDBConn();
		String sql="insert into enrollments values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, enrollmentDate.toString());
		pstmt.setInt(3, studentId);
		pstmt.setDouble(4, courseId);
		
		int rowsAffected = pstmt.executeUpdate();
		
		if (rowsAffected != 1) {
			throw new InvalidEnrollmentDataException("Student not enrolled successfully. Check the details given.");
		}
		DBUtil.dbClose();
		
	}

	@Override
	public void updateStudentInfo(int studentId, String phoneNumber) throws StudentDataUpdationException, SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql="update students set phone_number=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, phoneNumber);
		pstmt.setInt(2, studentId);
		
		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected != 1) {
			throw new StudentDataUpdationException("Error while updating student info");
		}
		DBUtil.dbClose();
	}

	@Override
	public void makePayment(int paymentId, double amount, LocalDate paymentDate, int studentId) throws SQLException, PaymentValidationException, InvalidPaymentRecordException {
		Connection conn = DBUtil.getDBConn();
		String sql="insert into payments values (?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, paymentId);
		pstmt.setDouble(2, amount);
		pstmt.setString(3, paymentDate.toString());
		pstmt.setInt(4, studentId);
		// considering min amount for payment as 1000
		if(amount < 1000) {
			throw new PaymentValidationException("Amount less than min. amount required for processing payment");
		}
		
		int rowsAffected = pstmt.executeUpdate();
		
		if (rowsAffected != 1) {
			throw new InvalidPaymentRecordException("Check the payment details. Payment not recorded");
		}
		DBUtil.dbClose();
	}

	@Override
	public Student displayStudentInfo(int studentId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "select * from students where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			java.sql.Date sqlDateOfBirth = rst.getDate("date_of_birth");
			LocalDate dateOfBirth = sqlDateOfBirth.toLocalDate();

			String email = rst.getString("email");
			String phoneNumber = rst.getString("phone_number");
			
			Student student = new Student(id, firstName, lastName, dateOfBirth, email, phoneNumber);
			
			return student;	
			
		}
		DBUtil.dbClose();
		return null;
	}

	@Override
	public List<Integer> getCourseId(int studentId) throws InvalidEnrollmentDataException, SQLException {
		List<Integer> list = new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql = "select courses_id from enrollments where students_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			int courseId = rst.getInt("courses_id");
			list.add(courseId);
		}
		if (list.isEmpty()) {
			throw new InvalidEnrollmentDataException("Student not enrolled in any course");
		}
		DBUtil.dbClose();
		return list;
		
	}

	@Override
	public String getEnrolledCourses(int id) throws SQLException, CourseNotFoundException {
		Connection conn = DBUtil.getDBConn();
		String sql = "select course_name from courses where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			String courseName = rst.getString("course_name");
			return courseName;
		}
		
		DBUtil.dbClose();
		throw new CourseNotFoundException("Course not found");
		
	}

	@Override
	public List<Payment> getPaymentHistory(int studentId) throws InvalidPaymentRecordException, SQLException {
		Connection conn = DBUtil.getDBConn();
		List<Payment> list = new ArrayList<>();
		String sql = "select * from payments where students_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			int id = rst.getInt("id");
			double amount = rst.getDouble("amount");
			java.sql.Date sqlPaymentDate = rst.getDate("payment_date");
			LocalDate paymentDate = sqlPaymentDate.toLocalDate();
			int studentsId = rst.getInt("students_id");

						
			Payment payment = new Payment(id, amount, paymentDate, studentsId);
			list.add(payment);	
			
		}
		
		if(list.isEmpty()) {
			throw new InvalidPaymentRecordException("No payment record found for the given student");
		}
		DBUtil.dbClose();
		return list;
	}

	
	

}
