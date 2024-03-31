package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.exception.InvalidPaymentRecordException;
import com.exception.StudentNotFoundException;
import com.model.Student;
import com.service.PaymentService;

public class PaymentController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PaymentService paymentService = new PaymentService();

		while (true) {
			System.out.println("\n*********PAYMENT REOCRD OPERATIONS*********");
			System.out.println("Press 1: To retrieve the student associated with the payment");
			System.out.println("Press 2: To retrieve the payment amount");
			System.out.println("Press 3: To retrieve the payment date");
			System.out.println("Press 0: To exit");

			System.out.println("Enter your choice:");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting all operations..Thank You !");
				break;
			}

			switch (input) {
			case 1:
				// To retrieve the student associated with the payment
				System.out.println("Enter the payment id:");
				int paymentId = sc.nextInt();
				try {
					int studentId = paymentService.getStudentId(paymentId);
					Student student = paymentService.getStudent(studentId);
					System.out.println("Student associated with the payment: ");
					System.out.println(student);
				} catch (SQLException | StudentNotFoundException | InvalidPaymentRecordException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 2:
				// To retrieve the payment amount
				System.out.println("Enter the payment id:");
				paymentId = sc.nextInt();
				try {
					double amount = paymentService.getPaymentAmount(paymentId);
					System.out.println("Amount associated with the payment id "+ paymentId +" is "+ amount);
				} catch (SQLException | InvalidPaymentRecordException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 3:
				// To retrieve payment date
				System.out.println("Enter the payment id:");
				paymentId = sc.nextInt();
				try {
					LocalDate paymentDate = paymentService.getPaymentDate(paymentId);
					System.out.println("Date on which the payment is made: " + paymentDate);
				} catch (InvalidPaymentRecordException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			default:
				System.out.println("Invalid choice");
				

			}// switch ends
		} // while ends

		sc.close();

	}// main ends

}// class ends
