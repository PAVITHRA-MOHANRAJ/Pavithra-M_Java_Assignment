package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidCourseDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;


import util.DBUtil;

public class CourseDaoImpl implements CourseDao{

	// validating teacher id in teacher table
	@Override
	public Teacher validateTeacher(int teacherId) throws TeacherNotFoundException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from teacher where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			// if id is present in the db, it will give teacher object else it will throw TeacherNoFoundException
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");
			//save it in an object
			Teacher teacher = new Teacher(id, firstName, lastName, email);
			return teacher; 
		}
		DBUtil.dbClose();
		throw new TeacherNotFoundException("Teacher ID given is Invalid");	
	}

	// fetching all courses
	@Override
	public List<Course> fetchAllCourses() throws SQLException{
		List<Course> list = new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		/* Step 1: Prepare the statement */
		String sql="select * from courses";
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		/* iterate over the result set and read DB column*/
		while(rst.next()) {
			int id = rst.getInt("id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");
			int teacherId = rst.getInt("teacher_id");
			
			//save it in an object
			Course course = new Course();
			course.setId(id);
			course.setCourseName(courseName);
			course.setCredits(credits);
			course.setTeacherId(teacherId);
			
			//add this obj to the list. 
			list.add(course);
		}
		DBUtil.dbClose();
		return list;
		
	}

	@Override
	public void insertRecordInCourse(int courseId, int teacherId) throws SQLException{
		Connection conn = DBUtil.getDBConn();
		String sql="update courses set teacher_id=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		pstmt.setInt(2, courseId);
		
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	@Override
	public Course verifyCourse(int courseId) throws CourseNotFoundException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from courses where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, courseId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			
			int id = rst.getInt("id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");
			int teacherId = rst.getInt("teacher_id");
			//save it in an object
			Course course = new Course(id, courseName, credits, teacherId);
			return course; 
		}
		DBUtil.dbClose();
		throw new CourseNotFoundException("Course ID given is Invalid");
	}

	@Override
	public Course validateCourseId(int courseCode) throws InvalidCourseDataException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from courses where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, courseCode);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			
			int id = rst.getInt("id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");
			int teacherId = rst.getInt("teacher_id");
			//save it in an object
			Course course = new Course(id, courseName, credits, teacherId);
			return course; 
		}
		DBUtil.dbClose();
		throw new InvalidCourseDataException("Course Code given is Invalid");
	}

	@Override
	public void updateRecordInCourse(int courseCode, int credits) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql="update courses set credits=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, credits);
		pstmt.setInt(2, courseCode);
		
		pstmt.executeUpdate();
		DBUtil.dbClose();	
	}

	@Override
	public Course DisplayCourseInfo(int courseId) throws InvalidCourseDataException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from courses where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, courseId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			
			int id = rst.getInt("id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");
			int teacherId = rst.getInt("teacher_id");
			//save it in an object
			Course course = new Course(id, courseName, credits, teacherId);
			return course; 
		}
		DBUtil.dbClose();
		throw new InvalidCourseDataException("Course Code given is Invalid");
	}

	@Override
	public int getTeacherId(int courseId) throws SQLException, TeacherNotFoundException{
		Connection conn=DBUtil.getDBConn();
		String sql="select teacher_id from courses where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, courseId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int id = rst.getInt("teacher_id"); 
			return id;
		}
		
		DBUtil.dbClose();
		throw new TeacherNotFoundException("No teacher assigned to course");
				
	}

	


}
