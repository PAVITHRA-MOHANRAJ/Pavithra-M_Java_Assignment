package com.model;

public class Course {
	private int id;
	private String courseName;
	private int credits;
	private int teacherId;
	
	// constructor without arguments
	public Course() {	}
	
	// constructor with all fields
	public Course(int id, String courseName, int credits, int teacherId) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.credits = credits;
		this.teacherId = teacherId;
	}
	
	// constructor without id
	public Course(String courseName, int credits, int teacherId) {
		super();
		this.courseName = courseName;
		this.credits = credits;
		this.teacherId = teacherId;
	}
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	// to string method
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", credits=" + credits + ", teacherId=" + teacherId
				+ "]";
	}
	
	

}
