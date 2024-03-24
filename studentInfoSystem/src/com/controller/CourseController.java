package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.CourseNotFoundException;
import com.exception.InvalidCourseDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Student;
import com.model.Teacher;
import com.service.CourseService;
import com.service.EnrollmentService;
import com.service.StudentService;
import com.service.TeacherService;

public class CourseController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CourseService courseService = new CourseService();
		EnrollmentService enrollmentService = new EnrollmentService();
		StudentService studentService = new StudentService();
		TeacherService teacherService = new TeacherService();

		while (true) {
			System.out.println("\n**********COURSE TABLE OPERATIONS**********");
			System.out.println("Press 1. To assign teacher to a course");
			System.out.println("Press 2. To update course information");
			System.out.println("Press 3. To display detailed information about course");
			System.out.println("Press 4. To retrieve list of student enrollments for the course");
			System.out.println("Press 5. To retrieve assigned teacher for a course");
			System.out.println("Press 0. To exit....");
			System.out.println("*******************************************");
			System.out.println("\n");
			System.out.println("Enter the option that you desire to perform:");
			int input = sc.nextInt();

			if (input == 0) {
				System.out.println("Exiting all operations!!...Thank You");
				break;
			}

			switch (input) {
			case 1:
				// to assign teacher to a course
				System.out.println("Enter id of the teacher:");
				int teacherId = sc.nextInt();

				// validate teacher id
				try {
					Teacher teacher = courseService.validateTeacher(teacherId);
					System.out.println("\nDetails of the teacher");
					System.out.println(teacher);
					System.out.println();
					List<Course> list = courseService.fetchAllCourses();
					System.out.println("Id" + "\t" + "Credits" + "\t" + "Teacher Id" + "\t" + "Course Name");
					for (Course c : list) {
						System.out.println(c.getId() + "\t" + c.getCredits() + "\t" + c.getTeacherId() + "\t\t"
								+ c.getCourseName());
					}
					System.out.println();
					System.out.println("Enter course id to be assigned: ");
					int courseId = sc.nextInt();
					Course course = courseService.verifyCourse(courseId);
					System.out.println("Course Details");
					System.out.println(course);

					courseService.insertRecordInCourse(list, courseId, teacherId);
					System.out.println("Successfully assigned teacher to a course..");

				} catch (TeacherNotFoundException | CourseNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
					break;
				}
				System.out.println("Teacher assigned to a course successfully");
				break;

			case 2:
				// to update course information
				System.out.println("Enter the course code of the course to be updated: ");
				int courseCode = sc.nextInt();

				try {
					Course course = courseService.validateCourseId(courseCode);
					System.out.println("Details of the course:");
					System.out.println(course);

					List<Course> list = courseService.fetchAllCourses();
					System.out.println("Id" + "\t" + "Credits" + "\t" + "Teacher Id" + "\t" + "Course Name");
					for (Course c : list) {
						System.out.println(c.getId() + "\t" + c.getCredits() + "\t" + c.getTeacherId() + "\t\t"
								+ c.getCourseName());
					}

					System.out.println("Enter the updated credits: ");
					int credits = sc.nextInt();
					courseService.updateRecordInCourse(list, courseCode, credits);
					System.out.println("Successfully updated the course information..");
					

				} catch (InvalidCourseDataException | SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3:
				// to display detailed information about the course
				System.out.println("Enter the course id for which the details are needed: ");
				int courseId1=sc.nextInt();
				
				try {
					Course course=courseService.DisplayCourseInfo(courseId1);
					System.out.println("Details of the course:");
					System.out.println(course);
					
					
				} catch (InvalidCourseDataException | SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
				
			case 4:
				// to retrieve list of student enrollment for the course
				System.out.println("Enter the course id: ");
				int courseId=sc.nextInt();
				try {
					Course course = courseService.validateCourseId(courseId);
					System.out.println("Details of the course:");
					System.out.println(course);

					List<Integer> studentsId=enrollmentService.fetchEnrollmentsByCourseId(courseId);
					System.out.println(studentsId);
					
					for(int id : studentsId) {
						Student student = studentService.getEnrollments(id);
						System.out.println("Details of student enrolled in course:");
						System.out.println(student);
					}

				} catch (InvalidCourseDataException | SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				break;
				
			case 5:
				// to retrieve assigned teacher for a course
				System.out.println("Enter the course id:");
				int courseId2 = sc.nextInt();
				try {
					Course course = courseService.validateCourseId(courseId2);
					System.out.println("Details of the course:");
					System.out.println(course);
					
					int teacherId1=courseService.getTeacherId(courseId2);
					System.out.println(teacherId1);
					
					Teacher teacher = teacherService.getTeacher(teacherId1);
					System.out.println("Details of the Teacher:");
					System.out.println(teacher);
					
				} catch (TeacherNotFoundException | InvalidCourseDataException | SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				} 
				break;
				
			default:
				System.out.println("Invalid input");
				
			}

		}
		sc.close();
	}

}
