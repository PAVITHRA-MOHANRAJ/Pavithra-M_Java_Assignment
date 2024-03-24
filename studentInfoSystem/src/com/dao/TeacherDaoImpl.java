package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;

import util.DBUtil;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public Teacher getTeacher(int teacherId) throws SQLException, TeacherNotFoundException {
		Connection conn = DBUtil.getDBConn();
		String sql = "select * from teacher where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int id1 = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");

			// save it in an object
			Teacher teacher = new Teacher(id1, firstName, lastName, email);
			return teacher;
		}
		DBUtil.dbClose();
		throw new TeacherNotFoundException("No Teacher with specified id");

	}

	@Override
	public void verifyTeacherId(int teacherId) throws TeacherNotFoundException, SQLException {
		Connection conn = DBUtil.getDBConn();
		List<Teacher> list = new ArrayList<>();
		String sql = "select * from teacher where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");
			Teacher teacher = new Teacher(id, firstName, lastName, email);
			list.add(teacher);
		}

		if (list.isEmpty()) {
			throw new TeacherNotFoundException("Teacher ID given is Invalid");
		}
		DBUtil.dbClose();
	}

	@Override
	public void updateTeacherInfo(int teacherId, String email) throws InvalidTeacherDataException, SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "update teacher set email=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setInt(2, teacherId);

		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected != 1) {
			throw new InvalidTeacherDataException("Error while updating teacher info");
		}
		DBUtil.dbClose();
	}

	@Override
	public Teacher displayTeacherInfo(int teacherId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql = "select * from teacher where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		if (rst.next()) {
			int id = rst.getInt("id");
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");

			Teacher teacher = new Teacher(id, firstName, lastName, email);

			return teacher;

		}
		DBUtil.dbClose();
		return null;
	}

	@Override
	public List<String> getAssignedCourses(int teacherId) throws InvalidTeacherDataException, SQLException {
		List<String> list = new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql = "select course_name from courses where teacher_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teacherId);
		/* execute the statement */
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			String courseName = rst.getString("course_name");
			list.add(courseName);
		}
		if (list.isEmpty()) {
			throw new InvalidTeacherDataException("Teacher not assigned to any course");
		}
		DBUtil.dbClose();
		return list;
	}

}
