package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.CourseNotFoundException;
import com.exception.InvalidPaymentRecordException;
import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;
import com.service.StudentService;

public class StudentServiceTest {

	StudentService studentService = new StudentService();

	@Test
	public void makePaymentTest() {
		// Use case 1
		int paymentId = 220;
		double amount = 500;
		LocalDate paymentDate = LocalDate.parse("2024-03-21");
		int studentId = 3;
		try {
			studentService.makePayment(paymentId, amount, paymentDate, studentId);
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Amount less than min. amount required for processing payment", e.getMessage());
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Check the payment details. Payment not recorded", e.getMessage());
		}

		// Use case 2
		paymentId = 221;
		amount = 700;
		paymentDate = LocalDate.parse("2024-03-11");
		studentId = 3;
		try {
			studentService.makePayment(paymentId, amount, paymentDate, studentId);
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Amount less than min. amount required for processing payment", e.getMessage());
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Check the payment details. Payment not recorded", e.getMessage());
		}

	}

	@Test
	public void displayStudentInfoTest() {
		// Use case 1
		int studentId = 6;
		Student expectedStudent = new Student(6, "Neville", "Longbottom", LocalDate.parse("1980-07-30"),
				"neville.longbottom@email.com", "3339876542");
		try {
			Assert.assertEquals(expectedStudent, studentService.displayStudentInfo(studentId));
		} catch (SQLException e) {
		}

		// Use case 2
		studentId = 8;
		expectedStudent = new Student(8, "Lavender", "Brown", LocalDate.parse("1981-01-21"), "lavender.brown@email.com",
				"9990001111");
		try {
			Assert.assertEquals(expectedStudent, studentService.displayStudentInfo(studentId));
		} catch (SQLException e) {
		}

		// Use case 3
		studentId = 2;
		expectedStudent = new Student(2, "Hermione", "Granger", LocalDate.parse("1979-09-19"),
				"hermione.granger@email.com", "9876543210");
		try {
			Assert.assertEquals(expectedStudent, studentService.displayStudentInfo(studentId));
		} catch (SQLException e) {
		}

	}

	@Test
	public void getEnrolledCoursesTest() {
		// Use case 1
		int courseId = 1001;
		String expectedCourse = "Data Structures";
		try {
			Assert.assertEquals(expectedCourse, studentService.getEnrolledCourses(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found", e.getMessage());
		}

		// Use case 2
		courseId = 1002;
		expectedCourse = "Databases";
		try {
			Assert.assertEquals(expectedCourse, studentService.getEnrolledCourses(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found", e.getMessage());
		}

		// Use case 3
		courseId = 1011;
		expectedCourse = "Databases";
		try {
			Assert.assertEquals(expectedCourse, studentService.getEnrolledCourses(courseId));
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found", e.getMessage());
		}

	}

	@Test
	public void getPaymentHistoryTest() {
		// Case 1
		int studentId = 2;
		List<Payment> expectedList = new ArrayList<>();
		Payment pay1 = new Payment(202, 1000, LocalDate.parse("2023-06-20"), 2);
		Payment pay2 = new Payment(211, 5000, LocalDate.parse("2024-03-24"), 2);
		expectedList.add(pay1);
		expectedList.add(pay2);

		try {
			Assert.assertEquals(expectedList, studentService.getPaymentHistory(studentId));
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("No payment record found for the given student", e.getMessage());
		} catch (SQLException e) {
		}

		// Case 2
		studentId = 10;
		List<Payment> expectedList1 = new ArrayList<>();
		pay1 = new Payment(210, 750, LocalDate.parse("2024-03-20"), 10);
		expectedList1.add(pay1);

		try {
			Assert.assertEquals(expectedList1, studentService.getPaymentHistory(studentId));
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("No payment record found for the given student", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Case 3
		studentId = 5;
		List<Payment> expectedList2 = new ArrayList<>();

		try {
			Assert.assertEquals(expectedList2, studentService.getPaymentHistory(studentId));
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("No payment record found for the given student", e.getMessage());
		} catch (SQLException e) {
		}
		

	}

}
