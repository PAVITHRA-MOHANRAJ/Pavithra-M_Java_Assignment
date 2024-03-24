package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.exception.CourseNotFoundException;
import com.exception.DuplicateEnrollmentException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.InvalidPaymentRecordException;
import com.exception.PaymentValidationException;
import com.exception.StudentDataUpdationException;
import com.exception.StudentNotFoundException;
import com.model.Payment;
import com.model.Student;
import com.service.StudentService;

public class StudentController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentService studentService = new StudentService();

		while (true) {
			System.out.println("********STUDENT MODULE OPERATIONS********");
			System.out.println("Press 1. To enroll student in a course");
			System.out.println("Press 2. To update student info");
			System.out.println("Press 3. To record a payment made by the student");
			System.out.println("Press 4. To display detailed information about the student");
			System.out.println("Press 5. To retrieve a list of courses in which the student is enrolled");
			System.out.println("Press 6. To retrieves a list of payment records for the student");
			System.out.println("Press 0. To exit all operations");

			System.out.println();
			System.out.println("Enter your choice:");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting all operations.. Thank you !");
				break;
			}

			switch (input) {
			case 1:
				// To enroll student in a course
				System.out.println("Enter the student id:");
				int studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					studentService.verifyEnrolledStatus(studentId);
					System.out.println("Enter the course id that you want to enroll in: ");
					int courseId = sc.nextInt();
					studentService.verifyCourseId(courseId);
					System.out.println("Provide a unique id for the enrollment record: ");
					int id = sc.nextInt();
					LocalDate enrollmentDate = LocalDate.now();
					studentService.enrollInCourse(id, enrollmentDate, studentId, courseId);
					System.out.println("Student enrolled successfully");
				} catch (SQLException | StudentNotFoundException | CourseNotFoundException
						| DuplicateEnrollmentException | InvalidEnrollmentDataException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				// To update student info
				System.out.println("Enter the student id: ");
				studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					System.out.println("Enter the new phone number:");
					String phoneNumber = sc.next();
					studentService.updateStudentInfo(studentId, phoneNumber);
					System.out.println("Student info updated successfully");
				} catch (SQLException | StudentNotFoundException | StudentDataUpdationException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				// To record a payment made by the student
				System.out.println("Enter the student id: ");
				studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					System.out.println("Provide a unique id for payment record: ");
					int paymentId = sc.nextInt();
					LocalDate paymentDate = LocalDate.now();
					System.out.println("Enter the amount: ");
					double amount = sc.nextDouble();
					studentService.makePayment(paymentId, amount, paymentDate, studentId);
					System.out.println("Payment recorded successfully");
				} catch (SQLException | StudentNotFoundException | PaymentValidationException
						| InvalidPaymentRecordException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				// To display detailed information about the student
				System.out.println("Enter the student id: ");
				studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					Student student = studentService.displayStudentInfo(studentId);
					System.out.println("Student information: ");
					System.out.println(student);
				} catch (SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				// To retrieve a list of courses in which the student is enrolled
				System.out.println("Enter the student id: ");
				studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					List<Integer> courseIdList = studentService.getCourseId(studentId);
					System.out.println("Courses enrolled by the student :");
					int i = 1;
					for (int id : courseIdList) {
						String courseName = studentService.getEnrolledCourses(id);
						System.out.println(i + ". " + courseName);
						i++;
					}

				} catch (SQLException | StudentNotFoundException | InvalidEnrollmentDataException
						| CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				// To retrieves a list of payment records for the student
				System.out.println("Enter the student id: ");
				studentId = sc.nextInt();
				try {
					studentService.verifyStudentId(studentId);
					List<Payment> paymentHistory = studentService.getPaymentHistory(studentId);
					System.out.println("List of payment records :");
					for (Payment p : paymentHistory) {
						System.out.println(p);
					}
				} catch (SQLException | StudentNotFoundException | InvalidPaymentRecordException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}

		} // while ends

		sc.close();
	}// main ends

}// class ends
