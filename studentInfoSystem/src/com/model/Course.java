package com.model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(courseName, credits, id, teacherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseName, other.courseName) && credits == other.credits && id == other.id
				&& teacherId == other.teacherId;
	}
	
	
	

}
