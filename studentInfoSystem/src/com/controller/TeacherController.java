package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;
import com.service.TeacherService;

public class TeacherController {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		TeacherService teacherService = new TeacherService();

		while (true) {
			System.out.println("**********TEACHER MODULE OPERATIONS**********");
			System.out.println("Press 1. To update teacher information");
			System.out.println("Press 2. To display detailed information about teacher");
			System.out.println("Press 3. To retrieve list of courses assigned to a teacher");
			System.out.println("Press 0. To exit..");

			System.out.println("Enter your choice: ");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting all operations..Thank you!");
				break;
			}

			switch (input) {
			case 1:
				// To update teacher information
				System.out.println("Enter teacher id: ");
				int teacherId = sc.nextInt();
				try {
					teacherService.verifyTeacherId(teacherId);
					// updating email of teacher
					System.out.println("Enter the updated email: ");
					String email = sc.next();
					teacherService.updateTeacherInfo(teacherId, email);
					System.out.println("Teacher info updated successfully");
				} catch (TeacherNotFoundException | SQLException | InvalidTeacherDataException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				// To display detailed information about teacher
				System.out.println("Enter teacher id: ");
				teacherId = sc.nextInt();
				try {
					teacherService.verifyTeacherId(teacherId);
					Teacher teacher = teacherService.displayTeacherInfo(teacherId);
					System.out.println("Detailed information about the teacher: ");
					System.out.println(teacher);
				} catch (TeacherNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				// To retrieve list of courses assigned to a teacher
				System.out.println("Enter teacher id: ");
				teacherId = sc.nextInt();

				try {
					teacherService.verifyTeacherId(teacherId);
					List<String> courseAssigned = teacherService.getAssignedCourses(teacherId);
					int i = 1;
					for (String s : courseAssigned) {
						System.out.println(i + ". " + s);
						i++;
					}
				} catch (TeacherNotFoundException | SQLException | InvalidTeacherDataException e) {
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
