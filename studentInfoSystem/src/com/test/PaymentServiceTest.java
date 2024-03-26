package com.test;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InvalidPaymentRecordException;
import com.exception.StudentNotFoundException;
import com.model.Student;
import com.service.PaymentService;

public class PaymentServiceTest {

	PaymentService paymentService = new PaymentService();

	@Test
	public void getStudentTest() {
		// Use case 1
		int studentId = 2;
		Student expectedStudent = new Student(2, "Hermione", "Granger", LocalDate.parse("1979-09-19"),
				"hermione.granger@email.com", "9876543210");
		try {
			Assert.assertEquals(expectedStudent, paymentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 2
		studentId = 3;
		expectedStudent = new Student(3, "Ronald", "Weasley", LocalDate.parse("1980-03-01"),
				"ron.weasley@email.com", "7418511592");
		try {
			Assert.assertEquals(expectedStudent, paymentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 3
		studentId = 12;
		expectedStudent = new Student(3, "Ronald", "Weasley", LocalDate.parse("1980-03-01"),
				"ron.weasley@email.com", "7418511592");
		try {
			Assert.assertEquals(expectedStudent, paymentService.getStudent(studentId));
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found", e.getMessage());
		} catch (SQLException e) {
		}
	}

	
	@Test
	public void getPaymentAmountTest() {
		// Use case 1
		int paymentId = 202;
		double expectedAmount = 1000;
		
		try {
			Assert.assertEquals(expectedAmount, paymentService.getPaymentAmount(paymentId), expectedAmount);
		} catch (SQLException e) {
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		}
		
		// Use case 2
		paymentId = 203;
		expectedAmount = 450;
		
		try {
			Assert.assertEquals(expectedAmount, paymentService.getPaymentAmount(paymentId), expectedAmount);
		} catch (SQLException e) {
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		}
		
		// Use case 3
		paymentId = 212;
		expectedAmount = 450;
		
		try {
			Assert.assertEquals(expectedAmount, paymentService.getPaymentAmount(paymentId), expectedAmount);
		} catch (SQLException e) {
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		}
	}

	@Test
	public void getPaymentDateTest() {
		// Use case 1
		int paymentId = 203;
		LocalDate expectedDate = LocalDate.parse("2023-09-25");
		
		try {
			Assert.assertEquals(expectedDate.toString(), paymentService.getPaymentDate(paymentId).toString());
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 2
		paymentId = 202;
		expectedDate = LocalDate.parse("2023-06-20");
		
		try {
			Assert.assertEquals(expectedDate.toString(), paymentService.getPaymentDate(paymentId).toString());
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		} catch (SQLException e) {
		}
		
		// Use case 3
		paymentId = 212;
		expectedDate = LocalDate.parse("2023-06-20");
		
		try {
			Assert.assertEquals(expectedDate.toString(), paymentService.getPaymentDate(paymentId).toString());
		} catch (InvalidPaymentRecordException e) {
			Assert.assertEquals("Payment Record not found", e.getMessage());
		} catch (SQLException e) {
		}

	}
}
