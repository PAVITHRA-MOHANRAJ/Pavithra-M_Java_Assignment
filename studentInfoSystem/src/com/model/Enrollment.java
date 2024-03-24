package com.model;

import java.time.LocalDate;

public class Enrollment {
	private int id;
	private LocalDate enrollmentDate;
	private int studentId;
	private int courseId;
	
	// constructor without arguments
	public Enrollment() {	}
	
	// constructor with all fields
	public Enrollment(int id, LocalDate enrollmentDate, int studentId, int courseId) {
		super();
		this.id = id;
		this.enrollmentDate = enrollmentDate;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	// constructor without id
	public Enrollment(LocalDate enrollmentDate, int studentId, int courseId) {
		super();
		this.enrollmentDate = enrollmentDate;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	// to string method
	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", enrollmentDate=" + enrollmentDate + ", studentId=" + studentId
				+ ", courseId=" + courseId + "]";
	}
	
	
}
