package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidEnrollmentDataException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Student;

import util.DBUtil;

public class EnrollmentDaoImpl implements EnrollmentDao{

	@Override
	public List<Integer> fetchEnrollmentsByCourseId(int courseId1) throws SQLException {
		List<Integer> list = new ArrayList<>();
		Connection conn=DBUtil.getDBConn();
		String sql="select students_id from enrollments where courses_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, courseId1);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		while(rst.next()) { 
			int studentsId = rst.getInt("students_id");
			list.add(studentsId);
			
		}
		DBUtil.dbClose();
		return list;
		
	}

	@Override
	public Enrollment verifyEnrollmentId(int enrollmentId) throws InvalidEnrollmentDataException, SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from enrollments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, enrollmentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int id = rst.getInt("id");
			java.sql.Date sqlenrollmentDate = rst.getDate("enrollment_date");
			LocalDate enrollmentDate = sqlenrollmentDate.toLocalDate();
			int studentId = rst.getInt("students_id");
			int courseId = rst.getInt("courses_id");
			Enrollment enrollment = new Enrollment(id, enrollmentDate, studentId, courseId);
			return enrollment; 
		}
		DBUtil.dbClose();
		throw new InvalidEnrollmentDataException("Enrollment not found");	
	}

	@Override
	public int getStudentId(int enrollmentId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select students_id from enrollments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, enrollmentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int studentId = rst.getInt("students_id");
			return studentId; 
		}
		DBUtil.dbClose();
		return 0;
	}

	@Override
	public Student getStudent(int studentId) throws SQLException, StudentNotFoundException {
		Connection conn=DBUtil.getDBConn();
		String sql="select * from students where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, studentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			java.sql.Date sqldob = rst.getDate("date_of_birth");
			LocalDate dateOfBirth = sqldob.toLocalDate();
			String email = rst.getString("email");
			String phoneNumber = rst.getString("phone_number");
			
			
			Student student = new Student(id, firstName, lastName, dateOfBirth, email, phoneNumber);
			return student; 
		}
		DBUtil.dbClose();
		throw new StudentNotFoundException("Student not found");
	}

	@Override
	public int getCourseId(int enrollmentId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="select courses_id from enrollments where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, enrollmentId);
		/* execute the statement */
		ResultSet rst= pstmt.executeQuery();
		if(rst.next()) { 
			int courseId = rst.getInt("courses_id");
			return courseId; 
		}
		DBUtil.dbClose();
		return 0;
	}

	@Override
	public Course getCourse(int courseId) throws SQLException, CourseNotFoundException {
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
			
			Course course = new Course(id, courseName, credits, teacherId);
			return course; 
		}
		DBUtil.dbClose();
		throw new CourseNotFoundException("Course not found");
	}

	

}
