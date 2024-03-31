package com.service;

import com.model.Teacher;

import java.sql.SQLException;
import java.util.List;

import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;

public class TeacherService {

	TeacherDao teacherDao = new TeacherDaoImpl();

	public Teacher getTeacher(int teacherId) throws SQLException, TeacherNotFoundException {
		return teacherDao.getTeacher(teacherId);
	}

	public void verifyTeacherId(int teacherId) throws TeacherNotFoundException, SQLException {
		teacherDao.verifyTeacherId(teacherId);
	}

	public void updateTeacherInfo(int teacherId, String email) throws InvalidTeacherDataException, SQLException {
		teacherDao.updateTeacherInfo(teacherId, email);
	}

	public Teacher displayTeacherInfo(int teacherId) throws SQLException {
		return teacherDao.displayTeacherInfo(teacherId);
	}

	public List<String> getAssignedCourses(int teacherId) throws InvalidTeacherDataException, SQLException {
		return teacherDao.getAssignedCourses(teacherId);
	}

	public void updateFirstName(int teacherId, String firstName) throws InvalidTeacherDataException, SQLException {
		teacherDao.updateFirstName(teacherId, firstName);
	}

	public void updateLastName(int teacherId, String lastName) throws InvalidTeacherDataException, SQLException {
		teacherDao.updateLastName(teacherId, lastName);
	}

}
