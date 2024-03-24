package com.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.exception.CourseNotFoundException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Student;
import com.service.EnrollmentService;

public class EnrollmentController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EnrollmentService enrollmentService = new EnrollmentService();
		

		while (true) {
			System.out.println("**********ENROLLMENT MODULE OPERATIONS**********");
			System.out.println("Press 1. To retrieve student associated with the enrollment");
			System.out.println("Press 2. To retrieve the course associated with the enrollment");
			System.out.println("Press 0. To exit..");

			System.out.println("Enter the operation that you want to perform: ");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting all operations.. Thank you !!!");
				break;
			}

			switch (input) {
			case 1:
				// To retrieve student associated with the enrollment
				System.out.println("Enter the enrollment id :");
				int enrollmentId = sc.nextInt();
				
				Enrollment enrollment;
				try {
					enrollment = enrollmentService.verifyEnrollmentId(enrollmentId);
					System.out.println("Enrollment details for the given id: ");
					System.out.println(enrollment);
					int studentId = enrollmentService.getStudentId(enrollmentId);	
					Student student = enrollmentService.getStudent(studentId);
					System.out.println("Student associated with the given enrollment :");
					System.out.println(student);
				} catch (InvalidEnrollmentDataException | SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
				
			case 2:
				// To retrieve the course associated with the enrollment
				System.out.println("Enter the enrollment id: ");
				enrollmentId = sc.nextInt();
				try {
					enrollment = enrollmentService.verifyEnrollmentId(enrollmentId);
					System.out.println("Enrollment details for the given id: ");
					System.out.println(enrollment);
					int courseId = enrollmentService.getCourseId(enrollmentId);
					Course course = enrollmentService.getCourse(courseId);
					System.out.println("Course associated with the given enrollment :");
					System.out.println(course);
					
				} catch (InvalidEnrollmentDataException | SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			default:
				System.out.println("Invalid operation");
				break;

			}// switch ends
		} // while ends
		sc.close();

	}// main ends

}// class ends
