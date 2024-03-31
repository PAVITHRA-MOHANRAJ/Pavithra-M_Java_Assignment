package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Teacher;

public interface TeacherDao {

	Teacher getTeacher(int teacherId)throws SQLException, TeacherNotFoundException;

	void verifyTeacherId(int teacherId) throws TeacherNotFoundException, SQLException;

	void updateTeacherInfo(int teacherId, String email) throws InvalidTeacherDataException, SQLException;

	Teacher displayTeacherInfo(int teacherId) throws SQLException;

	List<String> getAssignedCourses(int teacherId) throws InvalidTeacherDataException, SQLException;

	void updateFirstName(int teacherId, String firstName) throws InvalidTeacherDataException, SQLException;

	void updateLastName(int teacherId, String lastName) throws InvalidTeacherDataException, SQLException;

}
